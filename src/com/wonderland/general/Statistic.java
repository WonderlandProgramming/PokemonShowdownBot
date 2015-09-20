package com.wonderland.general;

/**
 * Represents a statistic for a specific moveset.
 * 
 * @author Lukas Peer, Lukas Kannenberg
 * @since 20-09-2015
 * @version 20-09-2015 16:22
 *
 */
public class Statistic {

	private double statisticUsagePercent;
	private String nature;
	private int[] evs = new int[6];

	/**
	 * Constructs a new Statistic.
	 * 
	 * @param usagePercent the usage percent
	 * @param nature the nature
	 * @param evs the evs 
	 */
	public Statistic(double usagePercent, String nature, int[] evs) {
		setStatisticUsagePercent(usagePercent);
		setNature(nature);
		setEvs(evs);
	}

	/**
	 * Constructs a new Statistic.
	 * 
	 * @param usagePercent the usage percent
	 * @param nature the nature
	 */
	public Statistic(double usagePercent, String nature) {
		setStatisticUsagePercent(usagePercent);
		setNature(nature);
	}

	/**
	 * @return the statistic usage percent
	 */
	public double getStatisticUsagePercent() {
		return statisticUsagePercent;
	}

	/**
	 * @param statisticUsagePercent
	 *            the statistic usage percent to set
	 */
	public void setStatisticUsagePercent(double statisticUsagePercent) {
		this.statisticUsagePercent = statisticUsagePercent;
	}

	/**
	 * @return the nature
	 */
	public String getNature() {
		return nature;
	}

	/**
	 * @param nature
	 *            the nature to set
	 */
	public void setNature(String nature) {
		this.nature = nature;
	}

	/**
	 * @return the evs
	 */
	public int[] getEvs() {
		return evs;
	}

	/**
	 * @param evs
	 *            the evs to set
	 */
	public void setEvs(int[] evs) {
		if (evs.length != 6)
			return;
		boolean valid = true;
		int evsum = 0;
		for (int i : evs) {
			evsum += i;
			if (i > 255 || evsum > 510) {
				valid = false;
				return;
			}
		}
		if (valid) {
			this.evs = evs;
		}
	}

	/**
	 * @param value
	 *            the health value to set
	 */
	public void setHPEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @param value
	 *            the attack value to set
	 */
	public void setAtkEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @param value
	 *            the defense value to set
	 */
	public void setDefEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @param value
	 *            the special attack value to set
	 */
	public void setSAtkEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @param value
	 *            the special defense value to set
	 */
	public void setSDefEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @param value
	 *            the speed value to set
	 */
	public void setSpeEV(int value) {
		boolean valid = true;
		if (value > 255 || getCurrentEVSum() + value > 510) {
			valid = false;
		}
		if (valid) {
			evs[0] = value;
		}
	}

	/**
	 * @return the sum of all current evs
	 */
	private int getCurrentEVSum() {
		int evsum = 0;
		for (int i : evs) {
			evsum += i;
		}
		return evsum;
	}

}
