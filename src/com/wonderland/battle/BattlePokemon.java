package com.wonderland.battle;

import com.wonderland.general.Pokemon;

public class BattlePokemon extends Pokemon {
	
	private double currentHP;
	private String status;
	private String ability;
	private boolean fainted = false;

	public BattlePokemon(String name) {
		super(null, name, null);
	}
	
	public void setCurHP(double currentPercent){
		currentHP = currentPercent;
	}
	
	public double getCurrentHP() {
		return currentHP;
	}
	
	public String getAbility() {
		return ability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BattlePokemon [name=" + name + ", item=" + item + "]";
	}

	public void fainted(){
		fainted = true;
	}
	
	public boolean isFainted(){
		return fainted;
	}

}
