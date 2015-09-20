package com.wonderland.battle;

import com.wonderland.general.Pokemon;

public class BattlePokemon extends Pokemon {
	
	private double hp;
	private String status;
	private String ability;
	private boolean fainted = false;

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

	@Override
	public String toString() {
		return "BattlePokemon [hp=" + hp + ", status=" + status + ", ability=" + ability + ", boost=" + boost
				+ ", name=" + name + ", item=" + item + "]";
	}

	public void fainted(){
		fainted = true;
	}
	
	public boolean isFainted(){
		return fainted;
	}

}
