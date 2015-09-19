package com.wonderworld.battle;

import java.util.List;

public class Pokemon {
	private List<Boost> boost;	
	private String name;
	private String item;
	
	public Pokemon(List<Boost> boost, String name, String item) {
		this.boost = boost;
		this.name = name;
		this.item = item;
	}
	
	public Pokemon(String name) {
		this.name = name;
	}

	public List<Boost> getBoost() {
		return boost;
	}

	public void setBoost(List<Boost> boost) {
		this.boost = boost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pokemon)) {
			return false;
		}
		Pokemon other = (Pokemon) obj;
		if (boost == null) {
			if (other.boost != null) {
				return false;
			}
		} else if (!boost.equals(other.boost)) {
			return false;
		}
		if (item == null) {
			if (other.item != null) {
				return false;
			}
		} else if (!item.equals(other.item)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	
	
}
