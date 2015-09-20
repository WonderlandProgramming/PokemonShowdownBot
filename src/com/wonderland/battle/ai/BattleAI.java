package com.wonderland.battle.ai;

import java.util.Arrays;
import java.util.Random;

import com.wonderland.battle.BattlePokemon;
import com.wonderland.battle.Battlefield;

public class BattleAI {
	
	Battlefield battlefield = new Battlefield();
	
	/**
	 * Constructs a new BattleAI.
	 * 
	 * @param battlefield
	 */
	public BattleAI(Battlefield battlefield) {
		this.battlefield = new Battlefield();
	}
	
	/**
	 * @return a number representing a random starter Pokemon
	 */
	public int pickRandomStarter() {
		BattlePokemon[] pokemons = battlefield.getMyTeam().toArray(new BattlePokemon[0]);
		if(pokemons.length != 0) {
			int random = new Random().nextInt(pokemons.length);
			return random++;
		}
		return -1;
	}
	
	/**
	 * @return a number representing an intelligent starter Pokemon
	 */
	public int pickIntelligentStarter() {
		BattlePokemon[] pokemons = battlefield.getMyTeam().toArray(new BattlePokemon[0]);
		int[] values = new int[pokemons.length];
		if (pokemons.length != 0) {
			return -1;
		}
		// Calculate and add best Pokemon for any matchup (max. 100)
		// TODO
		
		// Calculate if Hazard/FakeOut can be applied (max. 25)
		// TODO
		
		// Calculate if enemy has Hazard/FakeOut that can be countered (max. 25)
		// TODO
		
		// Calculate if weather start has impact on own/enemy team if available
		// TODO
		int sum = 0;
		for (int i = 0; i < values.length; i++) {
			int value = values[i];
			sum += value;
			values[i] = sum + value;
		}
		Arrays.sort(values);
		int random = new Random().nextInt(sum);
		for (int i = 0; i < values.length; i++) {
			if (values[i] <= random) {
				return i++;
			}
		}
		return -1;
	}
}
