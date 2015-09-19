package com.wonderworld.battle;

public class BattlePokemon extends Pokemon {
	
	private double hp;
	private String status;
	private String ability;
	
	public BattlePokemon(String name) {
		super(null, name, null);
	}
	
	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public enum Status{
		burn, freeze, paralysis, posion, badposion, sleep;
	}

}
