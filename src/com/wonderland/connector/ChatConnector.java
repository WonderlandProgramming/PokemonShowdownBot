package com.wonderland.connector;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChatConnector {
	private WebDriver webInterface;
	private WebElement chatBox;

	public ChatConnector(Selenium fightWindow) {
		this.webInterface = fightWindow.getDriver();

		try {
			chatBox = webInterface.findElement(By.cssSelector(
					"body > div.ps-room.ps-room-opaque > div.battle-log-add > form > textarea:nth-child(3)"));
		} catch (Exception e) {
			stop();
			System.out.println("Can not connect to chat!");
		}
	}

	public void println(String command) {
		print(command);
		nextLine();
	}

	public void print(String command) {
		if (chatBox != null)
			chatBox.sendKeys(command);
	}

	public void nextLine() {
		if (chatBox != null)
			chatBox.sendKeys(Keys.RETURN);
	}

	public void stop() {
		chatBox = null;
	}
	
	public List<String> getChatContext(){
		WebElement chatElements = webInterface.findElement(By.cssSelector("body > div.ps-room.ps-room-opaque > div.battle-log > div.inner"));
		List<WebElement> singleLines = chatElements.findElements(By.tagName("div"));
		
		List<String> lines = new ArrayList<>();
		for (WebElement line : singleLines) {
			String[] totalSingleLine = line.getText().split(",");
			for (int i = 0; i < totalSingleLine.length; i++) {
				if(!totalSingleLine[i].isEmpty()) lines.add(totalSingleLine[i]);
			}
		}
		return lines;
	}
}
