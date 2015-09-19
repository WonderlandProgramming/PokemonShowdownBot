package com.wonderworld.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Config {
	
	private String team;
	private String user;
	private String password;
	
	
	public Config(String path){
		JsonParser p = new JsonParser();
		
		String configFile = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String readline;
			while((readline = br.readLine()) != null) 
				configFile += (readline + "\n");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonObject configuration = (JsonObject)p.parse(configFile);
		
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
}
