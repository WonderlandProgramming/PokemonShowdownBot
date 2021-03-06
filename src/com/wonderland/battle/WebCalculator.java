package com.wonderland.battle;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.wonderland.connector.Selenium;
import com.wonderland.general.Boost;
import com.wonderland.general.Pokemon;
import com.wonderland.general.SpecialPokemon;
import com.wonderland.main.Config;

public class WebCalculator {
	private Selenium webEngine = new Selenium();
	private WebDriver calculator = webEngine.getDriver();

	private Pokemon lastTurnOpponent = null;
	private Pokemon lastTurnMyPokemon = null;

	public WebCalculator(Config config) {
		webEngine.get("http://fsibapt.github.io/");

		WebElement teamimport = calculator.findElement(By.cssSelector(".import-team-text"));
		teamimport.sendKeys(config.getTeam());

		WebElement teamsave = calculator.findElement(By.cssSelector("#import-1_wrapper > button"));
		sleep(150);
		teamsave.click();

		sleep(1500);

		Alert alert = calculator.switchTo().alert();
		sleep(150);
		alert.accept();
	}

	public Attack[][] calculate(Battlefield battlefied) {
		setPokemon(battlefied);

		Attack[][] rt = new Attack[2][4];

		sleep(250);
		
		WebElement[] myMoves = {
				calculator.findElement(By.cssSelector("#resultDamageL1")),
				calculator.findElement(By.cssSelector("#resultDamageL2")),
				calculator.findElement(By.cssSelector("#resultDamageL3")),
				calculator.findElement(By.cssSelector("#resultDamageL4")), };

		WebElement[] myMoveNames = {
				calculator.findElement(
						By.id("div.move-result-subgroup:nth-child(1) > div:nth-child(2) > label:nth-child(2)")),
				calculator.findElement(
						By.id("div.move-result-subgroup:nth-child(1) > div:nth-child(3) > label:nth-child(3)")),
				calculator.findElement(
						By.id("div.move-result-subgroup:nth-child(1) > div:nth-child(4) > label:nth-child(2)")),
				calculator.findElement(
						By.id("div.move-result-subgroup:nth-child(1) > div:nth-child(5) > label:nth-child(2)")) };

		Attack[] myDamage = new Attack[4];

		for (int i = 0; i < 4; i++) {
			String myMove = myMoves[i].getText();
			String myMoveName = myMoveNames[i].getText();

			if (!myMoveName.equalsIgnoreCase("(No Move)")) {
				myDamage[i] = new Attack(myMoveName, myMove.replace(".", ","));
			}
		}

		WebElement[] oppMoves = { calculator.findElement(By.cssSelector("#resultDamageR1")),
				calculator.findElement(By.cssSelector("#resultDamageR2")),
				calculator.findElement(By.cssSelector("#resultDamageR3")),
				calculator.findElement(By.cssSelector("#resultDamageR4")), };

		WebElement[] oppMoveNames = {
			calculator.findElement(
					By.id("div.move-result-subgroup:nth-child(2) > div:nth-child(2) > label:nth-child(2)")),
			calculator.findElement(
					By.id("div.move-result-subgroup:nth-child(2) > div:nth-child(3) > label:nth-child(2)")),
			calculator.findElement(
					By.id("div.move-result-subgroup:nth-child(2) > div:nth-child(4) > label:nth-child(2)")),
			calculator.findElement(
					By.id("div.move-result-subgroup:nth-child(2) > div:nth-child(5) > label:nth-child(2)")) };

		Attack[] oppDamage = new Attack[4];

		for (int i = 0; i < 4; i++) {
			String oppMove = oppMoves[i].getText();
			String oppMoveName = oppMoveNames[i].getText();

			if (!oppMoveName.equalsIgnoreCase("(No Move)")) {
				oppDamage[i] = new Attack(oppMoveName, oppMove.replace(".", ","));
			}
		}

		excludeIntimidate();

		rt[0] = myDamage;
		rt[1] = oppDamage;

		return rt;
	}

	/**
	 * Sets the webcalculator for your {@link Pokemon} and the enemy
	 * {@link Pokemon}.
	 * 
	 * @param myPokemonRaw
	 *            Your Pokemon
	 * @param enemyPokemonRaw
	 *            Opponents Pokemon
	 */
	public void setPokemon(Battlefield battlefield) {
		String myPokemon = SpecialPokemon.changeName(battlefield.getMyActivePokemonName());
		String opponentsPokemon = SpecialPokemon.changeName(battlefield.getOppActivePokemonName());

		Pokemon myPokemonRaw = battlefield.getMyActivePokemon();
		Pokemon enemyPokemonRaw = battlefield.getOppActivePokemon();
		
		if (!myPokemonRaw.equals(lastTurnMyPokemon)) {
			WebElement myControl = calculator.findElement(By.cssSelector("#s2id_autogen1 > a:nth-child(1)"));
			myControl.click();

			WebElement mytext = calculator
					.findElement(By.cssSelector("#select2-drop > div:nth-child(1) > input:nth-child(1)"));
			mytext.sendKeys(myPokemon);

			WebElement myResults = calculator.findElement(By.cssSelector("#select2-drop > ul:nth-child(2)"));
			List<WebElement> results = myResults.findElements(By.tagName("li"));

			for (WebElement element : results) {
				if (element.getAttribute("innerHTML").contains("Custom Set")) {
					element.click();
					break;
				}
			}

			WebElement myHP = calculator
					.findElement(By.cssSelector("#p1 > div:nth-child(7) > input.percent-hp.calc-trigger"));
			myHP.clear();
			myHP.sendKeys(Double.toString(myPokemonRaw.getCurHp()));

			setBoosts(1, myPokemonRaw.getBoost());
			lastTurnMyPokemon = new Pokemon(myPokemonRaw.getBoost(), myPokemonRaw.getName(), myPokemonRaw.getItem());
		}

		if (!enemyPokemonRaw.equals(lastTurnOpponent)) {
			WebElement opponentControl = calculator.findElement(By.cssSelector("#s2id_autogen3 > a:nth-child(1)"));
			opponentControl.click();
			WebElement opponentText = calculator
					.findElement(By.cssSelector("#select2-drop > div:nth-child(1) > input:nth-child(1)"));
			opponentText.sendKeys(opponentsPokemon);

			WebElement opponentResults = calculator.findElement(By.cssSelector("#select2-drop > ul:nth-child(2)"));
			List<WebElement> oppresults = opponentResults.findElements(By.tagName("li"));
			boolean hasOuSet = false;
			WebElement tempresult = null;
			boolean skipfirst = true;

			for (WebElement result : oppresults) {
				if (skipfirst) {
					skipfirst = false;
					continue;
				}
				if (result.getText().contains("OU")) {
					tempresult = result;
					hasOuSet = true;
					break;
				} else {
					if (!hasOuSet && tempresult == null) {
						tempresult = result;
					}
				}
			}

			if (tempresult != null) {
				tempresult.click();
			}
			setBoosts(2, enemyPokemonRaw.getBoost());
			lastTurnOpponent = enemyPokemonRaw;

			excludeIntimidate();

			if (enemyPokemonRaw.getItem() != null) {
				Select select = new Select(calculator.findElement(
						By.cssSelector("#p2 > div:nth-child(6) > div:nth-child(3) > select:nth-child(2)")));
				select.selectByValue(enemyPokemonRaw.getItem().trim());
			}

			WebElement oppHP = calculator
					.findElement(By.cssSelector("#p2 > div:nth-child(7) > input.percent-hp.calc-trigger"));
			oppHP.clear();
			oppHP.sendKeys(Double.toString(enemyPokemonRaw.getCurHp()));
		}
	}

	private void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		webEngine.closeConnection();
	}

	private void setBoosts(int p, List<Boost> boost) {
		if (boost == null) {
			return;
		}
		String[] selects = { "#p" + p + " > div:nth-child(5) > table > tbody > tr.at > td:nth-child(7) > select",
				"#p" + p + " > div:nth-child(5) > table > tbody > tr.df > td:nth-child(7) > select",
				"#p" + p + " > div:nth-child(5) > table > tbody > tr.sa.gen-specific.g2.g3.g4.g5.g6 > td:nth-child(7) > select",
				"#p" + p + " > div:nth-child(5) > table > tbody > tr.sd.gen-specific.g2.g3.g4.g5.g6 > td:nth-child(7) > select",
				"#p" + p + " > div:nth-child(5) > table > tbody > tr.sp > td:nth-child(7) > select" };

		for (Boost boo : boost) {
			int[] formated = boo.formatBoost();
			if (formated != null && formated[2] != -1) {
				Select select = new Select(calculator.findElement(By.cssSelector(selects[formated[2]])));

				switch (formated[0]) {
				case 0: // ID for good buff
					select.selectByValue("+" + (formated[1] + 1));
					break;
				case 1: // ID for bad buff
					select.selectByValue("-" + (formated[1] + 1));
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * This will setup your and the enemy {@link Pokemon} in the
	 * {@link WebCalculator}. Furthermore it will give you information on the
	 * type of attacks and the enemy {@link Pokemon}.
	 * 
	 * @param me
	 *            Your Pokemon
	 * @param opp
	 *            Enemy Pokemon
	 * @return Return an array of {@link String} in the following order: <br>
	 *         <b>
	 *         <code>  type1, type2, type3, type4, opptype1, opptype2 </code>
	 */
	public String[] getMoveTypeAndOpponentsType(Battlefield battlefield) {
		setPokemon(battlefield);

		String type1 = getSelectedOption(
				new Select(calculator.findElement(By.cssSelector("#p1 > div:nth-child(8) > select:nth-child(4)"))))
						.getText();
		String type2 = getSelectedOption(
				new Select(calculator.findElement(By.cssSelector("#p1 > div:nth-child(9) > select:nth-child(4)"))))
						.getText();
		String type3 = getSelectedOption(
				new Select(calculator.findElement(By.cssSelector("#p1 > div:nth-child(10) > select:nth-child(4)"))))
						.getText();
		String type4 = getSelectedOption(
				new Select(calculator.findElement(By.cssSelector("#p1 > div:nth-child(11) > select:nth-child(4)"))))
						.getText();

		String opptype1 = getSelectedOption(new Select(calculator
				.findElement(By.cssSelector("#p2 > div:nth-child(4) > div:nth-child(1) > select:nth-child(2)"))))
						.getText();
		String opptype2 = getSelectedOption(new Select(calculator
				.findElement(By.cssSelector("#p2 > div:nth-child(4) > div:nth-child(1) > select:nth-child(3)"))))
						.getText();

		return new String[] { type1, type2, type3, type4, opptype1, opptype2 };
	}

	/**
	 * This method will give you two ints which represent the Speed of you and
	 * the enemy {@link Pokemon}
	 * 
	 * @param me
	 *            My Pokemon
	 * @param opp
	 *            Opponents Pokemon
	 * @return Return an array of {@link Integer} in the following order: <br>
	 *         <b> <code> myPokemonSpeed, oppPokemonSpeed </code>
	 */
	public int[] getSpeedStats(Battlefield battlefield) {
		setPokemon(battlefield);

		int mySpeed = Integer.parseInt(calculator
				.findElement(By.cssSelector(
						"#p1 > div:nth-child(5) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(6) > span:nth-child(1)"))
				.getText());
		int oppSpeed = Integer.parseInt(calculator
				.findElement(By.cssSelector(
						"#p2 > div:nth-child(5) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(6) > span:nth-child(1)"))
				.getText());

		if (battlefield.getMyActivePokemon().getItem() == "Choice Scarf") {
			mySpeed = (int) (mySpeed * 1.5);
		}

		Select myspeedselect = new Select(calculator.findElement(By.cssSelector(
				"#p1 > div:nth-child(5) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(7) > select:nth-child(1)")));
		String myboost = Boost.getBoost(myspeedselect.getFirstSelectedOption().getText());

		Select oppspeedselect = new Select(calculator.findElement(By.cssSelector(
				"#p2 > div:nth-child(5) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(8) > td:nth-child(7) > select:nth-child(1)")));
		String oppboost = Boost.getBoost(oppspeedselect.getFirstSelectedOption().getText());

		mySpeed = (int) (mySpeed * Double.parseDouble(myboost));
		oppSpeed = (int) (oppSpeed * Double.parseDouble(oppboost));

		return new int[] { mySpeed, oppSpeed };
	}

	public boolean canStealthRock(Battlefield battlefield) {
		setPokemon(battlefield);

		Select select = new Select(calculator
				.findElement(By.cssSelector("#p1 > div:nth-child(6) > div:nth-child(2) > select:nth-child(2)")));
		String ability = getSelectedOption(select).getText();
		boolean oppHasMagicBounce = Arrays.asList(SpecialPokemon.magicBounce).contains(battlefield.getOppActivePokemonName());
		if (oppHasMagicBounce && !ability.contains("Mold Breaker")) {
			return false;
		}
		return true;
	}

	private void excludeIntimidate() {
		Select select = new Select(calculator
				.findElement(By.cssSelector("#p2 > div:nth-child(6) > div:nth-child(2) > select:nth-child(2)")));
		String ability = getSelectedOption(select).getText();
		if (ability == "Intimidate") {
			select.selectByValue("");
		}
	}

	public void exit() {
		calculator.close();
	}

	private WebElement getSelectedOption(Select select) {
		return select.getFirstSelectedOption();
	}
}
