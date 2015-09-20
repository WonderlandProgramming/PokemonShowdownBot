package com.wonderland.connector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wonderland.battle.BattlePokemon;
import com.wonderland.battle.Battlefield;
import com.wonderland.battle.WebCalculator;

public class BattleConnector {

	@SuppressWarnings("unused")
	private WebCalculator calculator;
	private Selenium selenium;

	private ChatConnector chat;
	
	private Battlefield battlefield;

	public BattleConnector(WebCalculator calculator, Selenium pkmnShowdown) {
		this.calculator = calculator;
		this.selenium = pkmnShowdown;
	}

	private void pickStarter(WebDriver page) {
		try {
			WebElement starter = page
					.findElement(By.cssSelector(".switchmenu > button:nth-child(" + pickLeadPokemon() + ")"));
			System.out.println(starter.getText());
			starter.click();
		} catch (Exception e) {
			System.out.println("Error picking pick manual pls");
			sleep(10000);
		}

	}

	public void startBattle(BattlePokemon[] myTeam) {
		WebDriver pageDirect = selenium.getDriver();

		sleep(10000);

		battlefield = new Battlefield();

		String oppTeam = pageDirect
				.findElement(By.cssSelector(
						"body > div.ps-room.ps-room-opaque > div.battle-log > div.inner > div:nth-child(15) > em"))
				.getText();
		
		String[] oppTeamArray = oppTeam.split(" \\/ ");

		BattlePokemon[] oppPkmnTeam = { new BattlePokemon(oppTeamArray[0]), new BattlePokemon(oppTeamArray[1]), new BattlePokemon(oppTeamArray[2]),
				new BattlePokemon(oppTeamArray[3]), new BattlePokemon(oppTeamArray[4]), new BattlePokemon(oppTeamArray[5]) };

		System.out.println("My Team: " + Arrays.toString(myTeam));
		System.out.println("Opp Team: " + Arrays.toString(oppTeamArray));
		
		battlefield.initializeTeams(myTeam, oppPkmnTeam);
		
		sleep(5000);
		
		pickStarter(pageDirect);

		while (waitingForOpponent()) {
			sleep(5000);
		}

		this.chat = new ChatConnector(selenium);

		// chat.println("TestText");

		sleep(15000);
		
		forfeit();

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

	private boolean waitingForOpponent() {
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

	// TODO
	public int pickLeadPokemon() {
		//
		// gegnerisches Team
		return 0;
		//return new Random().nextInt(6);
	}

	public void forfeit() {
		chat.println("/forfeit");
	}
}
