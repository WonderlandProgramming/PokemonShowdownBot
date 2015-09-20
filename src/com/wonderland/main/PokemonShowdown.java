package com.wonderland.main;

import java.util.Arrays;

import com.wonderland.battle.Battlefield;
import com.wonderland.battle.WebCalculator;
import com.wonderland.connector.BattleConnector;
import com.wonderland.connector.ChatConnector;
import com.wonderland.connector.PrepageConnector;
import com.wonderland.connector.Selenium;

public class PokemonShowdown {

	public static void main(String[] args) {
		Config config = new Config("config.json");

		WebCalculator calculator = new WebCalculator(config);

		Selenium webEngine = new Selenium();
		PrepageConnector prePage = new PrepageConnector(webEngine);

		prePage.login(config);
		prePage.loadTeam(config.getTeam());

		if (prePage.findABattle()) {
			// Active Battle
			System.out.println("Found Game!");
			

			Battlefield battlefield = new Battlefield();

			ChatConnector chat = new ChatConnector(webEngine);
			BattleConnector battlePage = new BattleConnector(calculator, webEngine, chat, battlefield);

			
			// setup battlefield active pokemon
			battlePage.startBattle(config.getBattleTeam());


			boolean ingame = true;

			while (ingame) {

				// check Win Loose enemy surrender etc!
				if (battlePage.hasEnded()) {
					ingame = false;
					break;
				}
				
				// Check if pokemon fainted
				if(battlePage.currentPokemonFainted()){
					//battlefield.updatePokemon(fainted = true);
					//AI.pickPokemon();
				}

				// Update enemy
				// update own

				battlePage.updatePokemon();
				
				// wenn mega evo dann anklicken
				//auﬂerdem pokemon updaten das es mega ist!
				battlePage.tryMegaEvolve();
				
				
				//Let the ai move the best turn, it will return Enum MOVE, CHANGE and a string for the move
				//setup the possible moves list
				//AI.move(battlefield)

				// Wait for enemy reaction
				while (battlePage.waitingForOpponent()) {
					sleep(1000);
				}
				
				
				// Wait for turn to end
				sleep(20000);
			}
		}

		webEngine.closeConnection();
		calculator.close();
	}

	public static void main1(String[] args) {
		Config config = new Config("config.json");
		System.out.println(Arrays.toString(config.getBattleTeam()));
	}

	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
