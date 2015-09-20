package com.wonderland.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a possible moveset for a specific Pokemon.
 * 
 * @author Lukas Peer, Lukas Kannenberg
 * @since 20-09-2015
 * @version 20-09-2015 15:54
 *
 */
public class Moveset {

	private double movesetUsagePercent;
	private String holdItem;
	private int level;
	private String ability;
	
	private List<Statistic> statistics = new ArrayList<>();
	
	private String[] moves = new String[4];
	
	/**
	 * Constructs a new Moveset.
	 * 
	 * @param usagePercent the usage percent
	 * @param item the item
	 * @param level the level
	 * @param ability the ability
	 * @param statistics the statistics
	 * @param moves the moves
	 */
	public Moveset(double usagePercent, String item, int level, String ability, Statistic[] statistics, String[] moves) {
		setMovesetUsagePercent(usagePercent);
		setHoldItem(item);
		setLevel(level);
		setAbility(ability);
		for (Statistic stat : statistics) {
			addStatistic(stat);
		}
		setMoves(moves);
	}
	
	/**
	 * Constructs a new Moveset.
	 * 
	 * @param usagePercent the usage percent
	 * @param item the item
	 * @param level the level
	 * @param ability the ability
	 * @param moves the moves
	 */
	public Moveset(double usagePercent, String item, int level, String ability, String[] moves) {
		setMovesetUsagePercent(usagePercent);
		setHoldItem(item);
		setLevel(level);
		setAbility(ability);
		setMoves(moves);
	}

	/**
	 * @return the movesetUsagePercent
	 */
	public double getMovesetUsagePercent() {
		return movesetUsagePercent;
	}

	/**
	 * @param movesetUsagePercent the movesetUsagePercent to set
	 */
	public void setMovesetUsagePercent(double movesetUsagePercent) {
		this.movesetUsagePercent = movesetUsagePercent;
	}

	/**
	 * @return the holdItem
	 */
	public String getHoldItem() {
		return holdItem;
	}

	/**
	 * @param holdItem the holdItem to set
	 */
	public void setHoldItem(String holdItem) {
		this.holdItem = holdItem;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the ability
	 */
	public String getAbility() {
		return ability;
	}

	/**
	 * @param ability the ability to set
	 */
	public void setAbility(String ability) {
		this.ability = ability;
	}

	/**
	 * @return the moves
	 */
	public String[] getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(String[] moves) {
		if(moves.length != 4) return;
		this.moves = moves;
	}
	
	/**
	 * @param stat the statistic to add
	 */
	public void addStatistic(Statistic stat) {
		statistics.add(stat);
	}

	/**
	 * @return the statistics
	 */
	public List<Statistic> getStatistics() {
		return statistics;
	}
	
}
