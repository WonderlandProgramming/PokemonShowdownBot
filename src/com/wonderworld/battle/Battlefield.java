package com.wonderworld.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Battlefield {

	private ArrayList<Pokemon> myTeam = new ArrayList<>();
	private ArrayList<Pokemon> oppTeam = new ArrayList<>();

	private HashMap<HazardType, Hazard> hazards = new HashMap<>();
	private Weather weather;

	public Battlefield() {
		weather = Weather.NONE;
	}

	/**
	 * @param myTeam
	 *            your team
	 * @param oppTeam
	 *            the opponents team
	 */
	public void initializeTeams(Pokemon[] myTeam, Pokemon[] oppTeam) {
		for (Pokemon pokemon : myTeam) {
			this.myTeam.add(pokemon);
		}
		for (Pokemon pokemon : oppTeam) {
			this.oppTeam.add(pokemon);
		}
	}

	/**
	 * @return your Team including all Pokemon
	 */
	public List<Pokemon> getMyTeam() {
		return myTeam;
	}

	/**
	 * @param position
	 *            the position
	 * @return the Pokemon at the specified position
	 */
	public Pokemon getMyTeamPokemon(Pokemon pokemon) {
		if (myTeam.contains(pokemon)) {
			return myTeam.get(myTeam.indexOf(pokemon));
		}
		return null;
	}

	/**
	 * @param position
	 *            the position
	 * @return the Pokemon at the specified position
	 */
	public Pokemon getOppTeamPokemon(Pokemon pokemon) {
		if (oppTeam.contains(pokemon)) {
			return oppTeam.get(oppTeam.indexOf(pokemon));
		}
		return null;
	}

	/**
	 * @return the opponents Team including all Pokemon
	 */
	public List<Pokemon> getOppTeam() {
		return oppTeam;
	}

	/**
	 * @return the current hazards
	 */
	public Hazard[] getHazards() {
		return hazards.values().toArray(new Hazard[0]);
	}

	/**
	 * @param type
	 *            the hazard type to return
	 * @return the hazard for the specified hazard type
	 */
	public Hazard getHazard(HazardType type) {
		return hazards.get(type);
	}

	/**
	 * @param hazard
	 *            the hazard to add
	 */
	public void addHazard(Hazard hazard) {
		hazards.put(hazard.getType(), hazard);
	}

	/**
	 * @return the current weather
	 */
	public Weather getWeather() {
		return weather;
	}

	/**
	 * @param weather
	 *            the weather to set
	 */
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
