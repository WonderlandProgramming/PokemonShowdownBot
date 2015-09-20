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

		battle(prePage, calculator, webEngine, config);

		webEngine.closeConnection();
		calculator.close();
	}
	
	public void battle(PrepageConnector prePage, WebCalculator calculator, Selenium webEngine, Config config){
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
			int turn = 1;
			
			sleep(10000);
			
			while (ingame) {
				
				//realy wait for the new turn to start
				//with turn numbers ex
				
				battlePage.waitForTurn(turn);
				
				System.out.println("New Turn! Now turn " + turn);

				turn++;

				
				// check Win Loose enemy surrender etc!
				if (battlePage.hasEnded()) {
					
					System.out.println("Game has ended");
					ingame = false;
					break;
				}
				
				
				// Check if pokemon fainted
				if(battlePage.currentPokemonFainted()){
					battlefield.getMyActivePokemon().fainted();
								
					int pokemonID = 6; // = AI.pickPokemon();
					battlePage.swapIn(pokemonID);
				}
				
				// Update enemy
				// update own
				battlePage.updatePokemon();
				
				sleep(100);
		
				// wenn mega evo dann anklicken
				//auﬂerdem pokemon updaten das es mega ist!		
				battlePage.tryMegaEvolve();
				
				
				sleep(100);
				//Let the ai move the best turn, it will return Enum MOVE, CHANGE and a string for the move
				//setup the possible moves list
				
				//AI.move(battlefield)


				// Wait for enemy reaction
				while (battlePage.waitingForOpponent()) {
					sleep(1000);
				}
				
				// Wait for turn to end
				sleep(15000);
				
				battlePage.setFaintedPokemon(chat.getChatContext());
			}
			
			prePage.gotToMainMenue();
			
		}
		
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
