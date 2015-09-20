package com.wonderland.battle;

import com.wonderland.general.Pokemon;

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

}
