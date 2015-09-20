package com.wonderland.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wonderland.battle.BattlePokemon;

public class Config {

	private String team;
	private String user;
	private String password;

	public Config(String path) {
		JsonParser p = new JsonParser();

		String configFile = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String readline;
			while ((readline = br.readLine()) != null)
				configFile += (readline + "\n");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonObject configuration = (JsonObject) p.parse(configFile);

		team = configuration.get("team").getAsString();
		user = configuration.get("user").getAsString();
		password = configuration.get("password").getAsString();
	}

	public String getTeam() {
		return team;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public BattlePokemon[] getBattleTeam() {
		List<BattlePokemon> activeTeam = new ArrayList<>();

		String[] allLines = team.split("\n");
		for (int i = 0; i < allLines.length; i++) {
			if (allLines[i].contains("@")) {
				int index = allLines[i].indexOf("(") - 1;
				if (index < 0)
					index = allLines[i].indexOf("@") - 1;
				
				BattlePokemon current = new BattlePokemon(allLines[i].substring(0, index));
				current.setItem(allLines[i].substring(allLines[i].indexOf("@") + 2));
				activeTeam.add(current);
			}
		}
		return activeTeam.toArray(new BattlePokemon[activeTeam.size()]);
	}
}
