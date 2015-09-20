package com.wonderland.connector;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.wonderland.main.Config;

public class PrepageConnector {

	private Selenium webClient;

	public PrepageConnector(Selenium webEngine) {
		webClient = webEngine;
		webClient.get("http://play.pokemonshowdown.com/");
	}

	public void loadTeam(String team) {
		WebDriver browserdirect = webClient.getDriver();

		WebElement teamBuilder = browserdirect.findElement(By.cssSelector(
				"#mainmenu > div > div.leftmenu > div.mainmenu > div:nth-child(2) > p:nth-child(1) > button"));
		teamBuilder.click();

		WebElement newTeam = browserdirect.findElement(By.name("new"));
		newTeam.click();

		WebElement importTeam = browserdirect.findElement(By.name("import"));
		importTeam.click();

		List<WebElement> textBoxes = browserdirect.findElements(By.className("textbox"));
		for (WebElement webElement : textBoxes) {
			String value = webElement.getAttribute("value");
			if (value == null || value.equals("")) {
				webElement.sendKeys(team);
				sleep(800);
				break;
			}
		}

		WebElement save = browserdirect.findElement(By.name("saveImport"));
		save.click();

		WebElement format = browserdirect.findElement(
				By.cssSelector("body > div:nth-child(44) > div > div.teamchartbox > ol > li.format-select > select"));
		format.click();
		format.sendKeys("o");
		format.click();

		List<WebElement> home = browserdirect.findElements(By.className("button"));
		for (WebElement webElement : home) {
			if (webElement.getText().contains("Home")) {
				webElement.click();
				break;
			}
		}

		WebElement fightCategory = browserdirect.findElement(By.cssSelector(".formatselect"));
		fightCategory.click();
		WebElement ou = browserdirect
				.findElement(By.cssSelector("ul.popupmenu:nth-child(1) > li:nth-child(4) > button:nth-child(1)"));
		ou.click();
	}

	public void login(Config config) {
		login(config.getUser(), config.getPassword());
	}

	public void login(String username) {
		username = username.substring(0, 18);

		WebDriver browserdirect = webClient.getDriver();
		WebElement login = browserdirect.findElement(By.name("login"));
		login.click();

		WebElement user = browserdirect.findElement(By.cssSelector("input.textbox:nth-child(1)"));
		user.sendKeys(username);

		WebElement submit = browserdirect.findElement(By.cssSelector(".buttonbar > button:nth-child(1)"));

		sleep(500);
		submit.click();
		sleep(500);
		submit.click();
	}

	public void login(String username, String password) {
		WebDriver browserdirect = webClient.getDriver();

		sleep(800);

		WebElement login = browserdirect.findElement(By.name("login"));
		login.click();

		sleep(500);

		WebElement user = browserdirect
				.findElement(By.cssSelector("body > div.ps-overlay > div > form > p:nth-child(1) > label > input"));
		user.sendKeys(username);

		WebElement submit = browserdirect.findElement(By.cssSelector(".buttonbar > button:nth-child(1)"));
		sleep(500);
		submit.click();

		if (!password.isEmpty()) {
			sleep(500);
			WebElement pass = browserdirect.findElement(By.cssSelector("input.textbox:nth-child(1)"));
			pass.sendKeys(password);

			login = browserdirect.findElement(By.cssSelector("p.buttonbar:nth-child(5) > button:nth-child(1)"));
			sleep(500);
			login.click();
		}
	}

	/**
	 * Looks for a battel in the OU class
	 * 
	 * @return returns true if it finds a battle
	 */
	public boolean findABattle() {
		WebDriver browserdirect = webClient.getDriver();
		try {
			browserdirect.findElement(By.cssSelector(".formatselect"));
		} catch (Exception e) {
			return false;
		}

		try {
			WebElement lookForABattle = browserdirect.findElement(By.cssSelector(".big"));
			WebElement format = browserdirect.findElement(By.cssSelector(".formatselect"));
			format.click();
			WebElement ou = browserdirect
					.findElement(By.cssSelector("ul.popupmenu:nth-child(1) > li:nth-child(4) > button:nth-child(1)"));
			ou.click();
			lookForABattle.click();

			while (true) {
				try {
					webClient.getDriver().findElement(By.cssSelector(
							"body > div.ps-room.ps-room-opaque > div.battle-log > div.inner > div:nth-child(15) > em"));
					return true;
				} catch (Exception e) {
					sleep(1000);
				}
			}

		} catch (Exception e) {
			return false;
		}
	}

	private void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void disableMusic() {
		WebDriver webDriver = webClient.getDriver();

		WebElement musik = webDriver.findElement(By.cssSelector("button.icon:nth-child(2)"));
		musik.click();
		webDriver.findElement(By.cssSelector(".ps-popup > p:nth-child(3) > label:nth-child(1) > input:nth-child(1)"))
				.click();
		musik = webDriver.findElement(By.cssSelector("button.icon:nth-child(2)"));
		musik.click();
	}

	public void gotToMainMenue() {
		WebDriver webDriver = webClient.getDriver();

		sleep(500);

		WebElement mainmenue = webDriver.findElement(By.cssSelector(
				"body > div.ps-room.ps-room-opaque.tiny-layout > div.battle-controls > div > p:nth-child(2) > em > button:nth-child(1)"));
		mainmenue.click();
	}
}
