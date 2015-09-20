package com.wonderland.battle;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wonderland.general.Hazard;
import com.wonderland.general.HazardType;

public class Battlefield {
	
	private String myActivePokemon;
	private String oppActivePokemon;

	private List<BattlePokemon> myTeam = new ArrayList<>();
	private List<BattlePokemon> oppTeam = new ArrayList<>();

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
	 * @return my active Pokemon name
	 */
	public String getMyActivePokemonName() {
		return myActivePokemon;
	}
	
	/**
	 * @return my active Pokemon
	 */
	public BattlePokemon getMyActivePokemon() {
		for (BattlePokemon battlePokemon : myTeam) {
			if(battlePokemon.getName().equals(myActivePokemon)) {
				return battlePokemon;
			}
		}
		return null;
	}

	/**
	 * @param myActivePokemon my active Pokemon to set
	 */
	public void setMyActivePokemon(String name) {
		for (BattlePokemon battlePokemon : myTeam) {
			if(battlePokemon.getName().equalsIgnoreCase(name)) {
				this.myActivePokemon = name;
				return;
			}
		}
	}

	/**
	 * @return the enemy active Pokemon name
	 */
	public String getOppActivePokemonName() {
		return oppActivePokemon;
	}
	
	/**
	 * @return the enemy active Pokemon
	 */
	public BattlePokemon getOppActivePokemon() {
		for (BattlePokemon battlePokemon : oppTeam) {
			if(battlePokemon.getName().equalsIgnoreCase(oppActivePokemon)) {
				return battlePokemon;
			}
		}
		return null;
	}

	/**
	 * @param oppActivePokemon the enemy active Pokemon to set
	 */
	public void setOppActivePokemon(String name) {
		for (BattlePokemon battlePokemon : oppTeam) {
			if(battlePokemon.getName().equals(name)) {
				this.oppActivePokemon = name;
				return;
			}
		}
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
	 * @param name the name
	 * @return the BattlePokemon with specified name if existing
	 */
	public BattlePokemon getMyTeamPokemon(String name) {
		for (BattlePokemon battlePokemon : myTeam) {
			if (battlePokemon.getName().equalsIgnoreCase(name)) {
				return battlePokemon;
			}
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
	 * @param name the name
	 * @return the BattlePokemon with specified name if existing
	 */
	public BattlePokemon getOppTeamPokemon(String name) {
		for (BattlePokemon battlePokemon : oppTeam) {
			if (battlePokemon.getName().equalsIgnoreCase(name)) {
				return battlePokemon;
			}
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

	@Override
	public String toString() {
		return MessageFormat.format("Battlefield [myActivePokemon={0}, enemyActivePokemon={1} myTeam={2}, enemyTeam={3}, myHazards={4}, enemyHazards={5}, weather={6}]", 
				myActivePokemon, oppActivePokemon, myTeam.toString(), oppTeam.toString(), myHazards.toString(), oppHazards.toString(), weather.toString());
	}
}
