package com.wonderland.battle;

import java.util.ArrayList;
import java.util.List;

import com.wonderland.general.Moveset;
import com.wonderland.general.Pokemon;

/**
 * Represents a Pokemon in battle format.
 * 
 * @author Lukas Peer, Lukas Kannenberg
 * @since 20-09-2015
 * @version 20-09-2015 15:57
 *
 */
public class BattlePokemon extends Pokemon {
	
	private double hp;
	private String status;
	private String ability;
	
	private boolean fainted = false;
	
	private List<Moveset> movesets = new ArrayList<>();

	/**
	 * Constructs a new BattlePokemon.
	 * 
	 * @param name the name
	 */
	public BattlePokemon(String name) {
		super(null, name, null);
	}
	
	/**
	 * @return the health
	 */
	public double getHp() {
		return hp;
	}

	/**
	 * @param hp the health value to set
	 */
	public void setHp(double hp) {
		this.hp = hp;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Updates this BattlePokemon as fainted.
	 */
	public void fainted(){
		fainted = true;
	}
	
	/**
	 * @return true if the Pokemon has fainted, otherwise false
	 */
	public boolean isFainted(){
		return fainted;
	}
	
	/**
	 * @return all known movesets of this Pokemon
	 */
	public Moveset[] getMovesets() {
		return movesets.toArray(new Moveset[0]);
	}
	
	/**
	 * @param moveset the moveset to add
	 */
	public void addMoveset(Moveset moveset) {
		movesets.add(moveset);
	}
	
	@Override
	public String toString() {
		return "BattlePokemon [hp=" + hp + ", status=" + status + ", ability=" + ability + ", boost=" + boost
				+ ", name=" + name + ", item=" + item + "]";
	}

}
