package com.wonderland.connector;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium {
	
	WebDriver driver;
	
	public Selenium(){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		
		//HtmlUnitDriver unitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		//unitDriver.setJavascriptEnabled(true);
		//driver = unitDriver;
	}
	
	public void closeConnection(){
		driver.close();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void get(String URL){
		driver.get(URL);
		waitForLoad();
	}

	private void waitForLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		try {
			wait.until(expectation);
		} catch (Throwable error) {
			throw new TimeoutException("Timeout waiting for Page Load Request to complete.");
		}
	}
}
