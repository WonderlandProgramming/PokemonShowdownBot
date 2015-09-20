package com.wonderland.main;

import com.wonderland.battle.Battlefield;
import com.wonderland.battle.WebCalculator;
import com.wonderland.connector.BattleConnector;
import com.wonderland.connector.ChatConnector;
import com.wonderland.connector.PrepageConnector;
import com.wonderland.connector.Selenium;

public class PokemonShowdown {
	public PokemonShowdown(){
		Config config = new Config("config.json");

		WebCalculator calculator = new WebCalculator(config);

		Selenium webEngine = new Selenium();
		PrepageConnector prePage = new PrepageConnector(webEngine);

		prePage.login(config);
		prePage.loadTeam(config.getTeam());
		prePage.disableMusic();

		if (prePage.findABattle()) {
			// Active Battle
			sleep(500);
			
			System.out.println("Found Game!");
			

			Battlefield battlefield = new Battlefield();

			BattleConnector battlePage = new BattleConnector(calculator, webEngine, battlefield);

			
			// setup battlefield active pokemon
			battlePage.startBattle(config.getBattleTeam());
			
			sleep(500);

			ChatConnector chat = new ChatConnector(webEngine);

			boolean ingame = true;

			while (ingame) {
				
				chat.print("Das wird nicht abgeschickt ... hoffe ich :D");
				
				sleep(500);
				
				// check Win Loose enemy surrender etc!
				if (battlePage.hasEnded()) {
					
					System.out.println("Game has ended");
					ingame = false;
					break;
				}
				
				sleep(500);
				
				// Check if pokemon fainted
				if(battlePage.currentPokemonFainted()){
					battlefield.getMyActivePokemon().fainted();
					
					sleep(500);
					
					int pokemonID = 0; // = AI.pickPokemon();
					battlePage.swapIn(pokemonID);
				}

				sleep(500);
				
				// Update enemy
				// update own
				battlePage.updatePokemon();
				
				sleep(500);
		
				// wenn mega evo dann anklicken
				//auﬂerdem pokemon updaten das es mega ist!		
				battlePage.tryMegaEvolve();
				
				
				sleep(500);
				//Let the ai move the best turn, it will return Enum MOVE, CHANGE and a string for the move
				//setup the possible moves list
				
				sleep(20000);
				
				//AI.move(battlefield)

				sleep(500);
				// Wait for enemy reaction
				while (battlePage.waitingForOpponent()) {
					sleep(1000);
				}
				
				
				// Wait for turn to end
				sleep(15000);
			}
			
			prePage.gotToMainMenue();
			
		}

		webEngine.closeConnection();
		calculator.close();
	}
	
	
	public static void main(String[] args) {
		new PokemonShowdown();
	}


	public static void sleep(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
