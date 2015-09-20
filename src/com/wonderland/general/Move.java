package com.wonderland.general;

import org.openqa.selenium.WebElement;

public class Move {
	private int minDamage;
	private int maxDamage;
	private WebElement moveClick;
	private String moveName;
	
	
	public Move(int minDamage, int maxDamage, WebElement moveClick, String moveName) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.moveClick = moveClick;
		this.moveName = moveName;
	}


	public int getMinDamage() {
		return minDamage;
	}


	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}


	public int getMaxDamage() {
		return maxDamage;
	}


	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}


	public WebElement getMoveClick() {
		return moveClick;
	}


	public void setMoveClick(WebElement moveClick) {
		this.moveClick = moveClick;
	}


	public String getMoveName() {
		return moveName;
	}


	public void setMoveName(String moveName) {
		this.moveName = moveName;
	
	}
	
}
