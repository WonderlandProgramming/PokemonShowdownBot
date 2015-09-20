package com.wonderland.main;

import java.util.Arrays;

import com.wonderland.battle.WebCalculator;
import com.wonderland.connector.BattleConnector;
import com.wonderland.connector.PrepageConnector;
import com.wonderland.connector.Selenium;

public class PokemonShowdown {

	public static void main(String[] args) {
		Config config = new Config("config.json");

		WebCalculator calculator = new WebCalculator(config);

		Selenium webEngine = new Selenium();
		PrepageConnector prePage = new PrepageConnector(webEngine);
		BattleConnector battlePage = new BattleConnector(calculator, webEngine);

		prePage.login(config);
		prePage.loadTeam(config.getTeam());

		if (prePage.findABattle()) {
			// Active Battle
			System.out.println("Found Game!");

			battlePage.startBattle(config.getBattleTeam());
			
			//setup battlefield active pokemon
			
			//Loop
			//  check Win Loose#
			//	Check if pokemon fainted
			//	Update enemy
			// 	update own
			//	ai movements
			// 	wenn mega evo dann anklicken
			//	wait for reaction
			//	wait for turn to end
		}
		
		webEngine.closeConnection();
		calculator.close();
	}

	public static void main1(String[] args) {
		Config config = new Config("config.json");
		System.out.println(Arrays.toString(config.getBattleTeam()));
	}
}
