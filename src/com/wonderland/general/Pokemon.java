package com.wonderland.general;

import java.util.List;

public class Pokemon {
	protected List<Boost> boost;	
	protected String name;
	protected String item;
	protected double hp;
	
	public Pokemon(List<Boost> boost, String name, String item) {
		this.boost = boost;
		this.name = name;
		this.item = item;
	}
	
	public Pokemon(String name) {
		this.name = name;
	}

	public List<Boost> getBoost() {
		return boost;
	}

	public void setBoost(List<Boost> boost) {
		this.boost = boost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * @return the health
	 */
	public double getCurHp() {
		return hp;
	}

	/**
	 * @param hp the health value to set
	 */
	public void setCurHp(double hp) {
		this.hp = hp;
	}


	public boolean equals(Pokemon pokemon){
		if(pokemon == null){
			return false;
		}
		if(!pokemon.getName().equalsIgnoreCase(this.getName())){
			return false;
		}
		if(pokemon.getBoost() != pokemon.getBoost()){
			return false;
		}
		if(pokemon.getCurHp() != pokemon.getCurHp()){
			return false;
		}
		return true;
	}
	
}
