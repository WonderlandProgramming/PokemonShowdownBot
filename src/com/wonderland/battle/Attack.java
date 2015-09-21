package com.wonderland.battle;

import com.wonderland.general.Type;

public class Attack {
	private String name;
	private double damage;
	private Type pokemonType;

	public Attack(String name, int damage, Type pokemonType) {
		this.name = name;
		this.damage = damage;
		this.pokemonType = pokemonType;
	}

	public Attack(String name, String damageAsDouble) {
		this.name = name;
		this.damage = calcDamage(damageAsDouble);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public Type getPokemonType() {
		return pokemonType;
	}

	public double calcDamage(String raw) {
		String[] numbers = raw.substring(0, raw.length() -1).replace(",", ".").split(" - ");
		return Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
	}

	public void setPokemonType(Type pokemonType) {
		this.pokemonType = pokemonType;
	}
}
