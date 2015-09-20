package com.wonderland.battle.ai;

import java.util.Random;

import com.wonderland.battle.BattlePokemon;
import com.wonderland.battle.Battlefield;

public class BattleAI {
	
	Battlefield battlefield = new Battlefield();
	
	public BattleAI(Battlefield battlefield) {
		this.battlefield = new Battlefield();
	}
	
	public int pickRandomStarter() {
		BattlePokemon[] pokemon = battlefield.getMyTeam().toArray(new BattlePokemon[0]);
		int random = new Random().nextInt(pokemon.length);
		return random++;
	}
}
