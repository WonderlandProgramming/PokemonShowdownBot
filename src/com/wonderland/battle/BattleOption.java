package com.wonderland.battle;

/**
 * Represents a possible Option to perform during a turn.
 * 
 * @author Lukas Peer, Lukas Kannenberg
 * @since 20-09-2015
 * @version 20-09-2015 15:57
 *
 */
public class BattleOption {
	
	private BattleOption option;
	private String name;
	
	/**
	 * All types of options during a turn.
	 */
	public enum BattleOptionType {
		SWITCH, MOVE;
	}
	
	/**
	 * Constructs a new BattleOption.
	 * 
	 * @param option the battle option
	 * @param name the name
	 */
	public BattleOption(BattleOption option, String name) {
		setOption(option);
		setName(name);
	}

	/**
	 * @return the option
	 */
	public BattleOption getOption() {
		return option;
	}

	/**
	 * @param option the option to set
	 */
	public void setOption(BattleOption option) {
		this.option = option;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
