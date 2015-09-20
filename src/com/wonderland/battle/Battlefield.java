package com.wonderland.battle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wonderland.general.Hazard;
import com.wonderland.general.HazardType;
import com.wonderland.general.Pokemon;

public class Battlefield {
	
	private BattlePokemon myActivePokemon;
	private BattlePokemon enemyActivePokemon;

	private ArrayList<BattlePokemon> myTeam = new ArrayList<>();
	private ArrayList<BattlePokemon> oppTeam = new ArrayList<>();

	private HashMap<HazardType, Hazard> myHazards = new HashMap<>();
	private HashMap<HazardType, Hazard> oppHazards = new HashMap<>();
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
	public void initializeTeams(BattlePokemon[] myTeam, BattlePokemon[] oppTeam) {
		for (BattlePokemon pokemon : myTeam) {
			this.myTeam.add(pokemon);
		}
		for (BattlePokemon pokemon : oppTeam) {
			this.oppTeam.add(pokemon);
		}
	}
	
	/**
	 * @return my active Pokemon
	 */
	public BattlePokemon getMyActivePokemon() {
		return myActivePokemon;
	}

	/**
	 * @param myActivePokemon my active Pokemon to set
	 */
	public void setMyActivePokemon(BattlePokemon myActivePokemon) {
		this.myActivePokemon = myActivePokemon;
	}

	/**
	 * @return the enemy active Pokemon
	 */
	public BattlePokemon getEnemyActivePokemon() {
		return enemyActivePokemon;
	}

	/**
	 * @param enemyActivePokemon the enemy active Pokemon to set
	 */
	public void setEnemyActivePokemon(BattlePokemon enemyActivePokemon) {
		this.enemyActivePokemon = enemyActivePokemon;
	}

	/**
	 * @return your Team including all Pokemon
	 */
	public List<BattlePokemon> getMyTeam() {
		return myTeam;
	}

	/**
	 * @param position
	 *            the position
	 * @return the Pokemon at the specified position
	 */
	public BattlePokemon getMyTeamPokemon(BattlePokemon pokemon) {
		if (myTeam.contains(pokemon)) {
			return myTeam.get(myTeam.indexOf(pokemon));
		}
		return null;
	}

	/**
	 * @return the opponents Team including all Pokemon
	 */
	public List<BattlePokemon> getOppTeam() {
		return oppTeam;
	}
	
	/**
	 * @param position
	 *            the position
	 * @return the Pokemon at the specified position
	 */
	public BattlePokemon getOppTeamPokemon(BattlePokemon pokemon) {
		if (oppTeam.contains(pokemon)) {
			return oppTeam.get(oppTeam.indexOf(pokemon));
		}
		return null;
	}

	/**
	 * @return the current hazards
	 */
	public Hazard[] getMyHazards() {
		return myHazards.values().toArray(new Hazard[0]);
	}

	/**
	 * @param type
	 *            the hazard type to return
	 * @return the hazard for the specified hazard type
	 */
	public Hazard getMyHazard(HazardType type) {
		return myHazards.get(type);
	}

	/**
	 * @param hazard
	 *            the hazard to add
	 */
	public void addMyHazard(Hazard hazard) {
		myHazards.put(hazard.getType(), hazard);
	}
	
	/**
	 * @return the current hazards
	 */
	public Hazard[] getOppHazards() {
		return oppHazards.values().toArray(new Hazard[0]);
	}

	/**
	 * @param type
	 *            the hazard type to return
	 * @return the hazard for the specified hazard type
	 */
	public Hazard getOppHazard(HazardType type) {
		return oppHazards.get(type);
	}

	/**
	 * @param hazard
	 *            the hazard to add
	 */
	public void addOppHazard(Hazard hazard) {
		oppHazards.put(hazard.getType(), hazard);
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
