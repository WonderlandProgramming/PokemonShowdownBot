package com.wonderland.connector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bcel.classfile.PMGClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.wonderland.battle.BattlePokemon;
import com.wonderland.battle.Battlefield;
import com.wonderland.battle.WebCalculator;
import com.wonderland.battle.ai.BattleAI;
import com.wonderland.general.Boost;
import com.wonderland.general.SpecialPokemon;

public class BattleConnector {

	private WebCalculator calculator;
	private Selenium selenium;

	private Battlefield battlefield;

	public BattleConnector(WebCalculator calculator, Selenium pkmnShowdown, Battlefield battlefield) {
		this.calculator = calculator;
		this.selenium = pkmnShowdown;
		this.battlefield = battlefield;
	}

	private void pickStarter(WebDriver page, BattleAI ai) {
		try {
			WebElement starter = page
					.findElement(By.cssSelector(".switchmenu > button:nth-child(" + ai.pickRandomStarter() + ")"));
			System.out.println("Choosen Starter is: " + starter.getText());
			starter.click();
		} catch (Exception e) {
			System.out.println("Error picking pick manual pls");
			sleep(10000);
		}

	}

	public void startBattle(BattlePokemon[] myTeam, BattleAI ai) {
		WebDriver pageDirect = selenium.getDriver();

		sleep(2500);

		String oppTeam = pageDirect
				.findElement(By.cssSelector(
						"body > div.ps-room.ps-room-opaque > div.battle-log > div.inner > div:nth-child(15) > em"))
				.getText();

		String[] oppTeamArray = oppTeam.split(" \\/ ");

		BattlePokemon[] oppPkmnTeam = { new BattlePokemon(oppTeamArray[0]), new BattlePokemon(oppTeamArray[1]),
				new BattlePokemon(oppTeamArray[2]), new BattlePokemon(oppTeamArray[3]),
				new BattlePokemon(oppTeamArray[4]), new BattlePokemon(oppTeamArray[5]) };

		System.out.println("My Team: " + Arrays.toString(myTeam));
		System.out.println("Opp Team: " + Arrays.toString(oppTeamArray));

		battlefield.initializeTeams(myTeam, oppPkmnTeam);

		sleep(2500);

		pickStarter(pageDirect, ai);

		while (waitingForOpponent() && !hasEnded()) {
			sleep(5000);
		}
	}

	public List<WebElement> allMoves() {
		WebDriver pageDirect = selenium.getDriver();
		List<WebElement> possibleMoves = new ArrayList<>();
		possibleMoves.addAll(pageDirect.findElements(By.cssSelector(
				"body > div.ps-room.ps-room-opaque > div.battle-controls > div > div.movecontrols > div.movemenu")));
		possibleMoves.addAll(pageDirect.findElements(By.cssSelector(
				"body > div.ps-room.ps-room-opaque > div.battle-controls > div > div.switchcontrols > div.switchmenu")));
		return possibleMoves;
	}

	public boolean waitingForOpponent() {
		WebDriver pageDirect = selenium.getDriver();
		try {
			WebElement waiting = pageDirect.findElement(By.cssSelector(".controls > p:nth-child(1) > em:nth-child(1)"));
			if (waiting.getAttribute("innerHTML").contains("Waiting")) {
				WebElement timer = pageDirect.findElement(By.cssSelector(".timer > button:nth-child(1)"));
				if (timer.getAttribute("innerHTML").contains("Start timer")) {
					timer.click();
				}
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	private void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean hasEnded() {
		WebDriver webDriver = selenium.getDriver();
		try {
			WebElement messageBar = webDriver.findElement(By.cssSelector(".messagebar"));
			if (messageBar.getText().contains("won the battle!")
					|| messageBar.getTagName().contains("lost the battle!")) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public boolean currentPokemonFainted() {
		WebDriver webDriver = selenium.getDriver();
		try {
			WebElement whatDo = webDriver.findElement(By.cssSelector(".whatdo"));
			if (whatDo.getText().contains("Switch")) {
				System.out.println("pickPokemonifDefeated");
				return true;
			}
		} catch (Exception e) {

		}
		return false;
	}

	public void updatePokemon() {

		String myId = getMySearchText();
		String oppId = getOpponentsSearchText();

		battlefield.setMyActivePokemon(SpecialPokemon.changeName(getName(myId)));
		battlefield.setOppActivePokemon(SpecialPokemon.changeName(getName(oppId)));

		BattlePokemon myPokemon = battlefield.getMyActivePokemon();
		BattlePokemon oppPokemon = battlefield.getOppActivePokemon();

		if (myPokemon != null) {
			myPokemon.setCurHp(getHealth(myId));
			myPokemon.setItem(getItem(myId));
			myPokemon.setBoost(getMyBoosts());
		}

		if (oppPokemon != null) {
			oppPokemon.setCurHp(getHealth(oppId));
			oppPokemon.setItem(getItem(oppId));
			oppPokemon.setBoost(getOppBoosts());
		}

		System.out.println(battlefield.toString());
	}

	public void tryMegaEvolve() {
		WebDriver pageDirect = selenium.getDriver();
		try {
			pageDirect.findElement(By.cssSelector(".movemenu > label:nth-child(6) > input:nth-child(1)")).click();
		} catch (Exception e) {
		}
	}

	public void swapIn(int PokemonSlotID) {
		WebDriver webDriver = selenium.getDriver();
		WebElement pokemon = webDriver
				.findElement(By.cssSelector(".switchmenu > button:nth-child(" + PokemonSlotID + ")"));
		if (pokemon.getAttribute("class").equals("disabled")) {
			System.err.println("Can not swap in disabled Pokemon!");
		} else {
			pokemon.click();
		}
	}

	private String getMySearchText() {
		WebDriver webDriver = selenium.getDriver();

		List<WebElement> test = webDriver
				.findElements(By.cssSelector("body > div.ps-room.ps-room-opaque > div.foehint *"));
		WebElement me = test.get(1);

		Actions action = new Actions(webDriver);

		action.moveToElement(me).perform();

		sleep(150);

		WebElement tooltip = webDriver.findElement(By.cssSelector(".tooltip"));
		String searchtext = tooltip.getAttribute("innerHTML");
		return searchtext;
	}

	private String getOpponentsSearchText() {
		WebDriver webDriver = selenium.getDriver();
		WebElement opponent;
		try {
			opponent = webDriver
					.findElement(By.cssSelector("div.ps-room:nth-child(47) > div:nth-child(2) > div:nth-child(1)"));
		} catch (Exception e) {
			opponent = webDriver.findElement(By.cssSelector(".foehint > div:nth-child(1)"));
		}
		Actions action = new Actions(webDriver);
		action.moveToElement(opponent).perform();
		WebElement tooltip = webDriver.findElement(By.cssSelector(".tooltip"));

		String searchtext = tooltip.getAttribute("innerHTML");
		return searchtext;
	}

	private String getItem(String searchtext) {
		if (searchtext.contains("Item:")) {
			searchtext = searchtext.substring(searchtext.indexOf("Item:") + 5);
			return searchtext.substring(0, searchtext.indexOf("</p"));
		} else {
			return null;
		}
	}

	private double getHealth(String searchtext) {
		searchtext = searchtext.substring(searchtext.indexOf("HP:") + 3);
		return Double.valueOf((searchtext.substring(0, searchtext.indexOf("%"))).replace(",", "."));
	}

	private String getName(String searchtext) {
		String name;
		try {
			name = searchtext.substring(searchtext.indexOf("<h2>") + 4,
					searchtext.indexOf("<small style") - (searchtext.indexOf("<h2>")));
		} catch (Exception e) {
			name = searchtext.substring(searchtext.indexOf("<h2>") + 4,
					searchtext.indexOf("<br") - (searchtext.indexOf("<h2>")));
		}
		if (name.contains("(")) {
			name = name.substring(0, name.indexOf("(") - 2);
		}
		return name.trim();
	}

	private List<Boost> getOppBoosts() {
		WebDriver webDriver = selenium.getDriver();
		WebElement status = webDriver.findElement(By.cssSelector("div.statbar:nth-child(1)"))
				.findElement(By.className("status"));
		return getBoosts(status);
	}

	private List<Boost> getMyBoosts() {
		WebDriver webDriver = selenium.getDriver();
		WebElement status = webDriver.findElement(By.cssSelector("div.statbar:nth-child(2)"))
				.findElement(By.className("status"));
		return getBoosts(status);
	}

	private static List<Boost> getBoosts(WebElement status) {
		List<WebElement> good = status.findElements(By.className("good"));
		List<WebElement> bad = status.findElements(By.className("bad"));
		List<Boost> boosts = new ArrayList<Boost>();
		for (WebElement goods : good) {
			Boost boost = new Boost();
			boost.type = Boost.Type.GOOD;
			boost.text = goods.getText();
			boosts.add(boost);
		}
		for (WebElement bads : bad) {
			Boost boost = new Boost();
			boost.type = Boost.Type.BAD;
			boost.text = bads.getText();
			boosts.add(boost);
		}
		return boosts;
	}

	public void waitForTurn(int thisturn, boolean ingame, BattleAI ai) {
		WebDriver webDriver = selenium.getDriver();
		while (true) {
			try {
				if(this.hasEnded()){
					ingame = false;
					break;
				}
				
				if(currentPokemonFainted()){
					this.swapIn(ai.pickRandomStarter());
				}
				
				WebElement turn = webDriver.findElement(By
						.cssSelector("body > div.ps-room.ps-room-opaque > div.battle > div > div:nth-child(9) > div"));
				String turnNumber = turn.getText().substring(5);
				int thisturnRead = Integer.parseInt(turnNumber);

				if (thisturn == thisturnRead) {
					return;
				}

				sleep(50);
			} catch (Exception e) {
				sleep(50);
			}

		}
	}
	
	public void setFaintedPokemon(List<String> chatLog){
		for (String chatLine : chatLog) {
			if(chatLine.contains("fainted")){
				if(chatLine.startsWith("The opposing")){
					String pkmn = chatLine.substring(12, chatLine.indexOf("fainted") - 1).trim();
					BattlePokemon fainted = battlefield.getOppTeamPokemon(SpecialPokemon.changeName(pkmn));
					fainted.fainted();
				}else{
					String pkmn = chatLine.substring(0, chatLine.indexOf("fainted") - 1).trim();
					BattlePokemon fainted = battlefield.getMyTeamPokemon(SpecialPokemon.changeName(pkmn));
					fainted.fainted();
				}
			}
		}
	}
}
