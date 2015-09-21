package com.wonderland.main;

import com.wonderland.battle.Battlefield;
import com.wonderland.battle.WebCalculator;
import com.wonderland.battle.ai.BattleAI;
import com.wonderland.connector.BattleConnector;
import com.wonderland.connector.ChatConnector;
import com.wonderland.connector.PrepageConnector;
import com.wonderland.connector.Selenium;

public class PokemonShowdown {
	
	public PokemonShowdown() {
		Config config = new Config("config.json");

		WebCalculator calculator = new WebCalculator(config);

		Selenium webEngine = new Selenium();
		PrepageConnector prePage = new PrepageConnector(webEngine);

		prePage.login(config);
		prePage.loadTeam(config.getTeam());
		prePage.disableMusic();

		for (int i = 0; i < 1; i++) {
			battle(prePage, calculator, webEngine, config);
		}

		webEngine.closeConnection();
		calculator.close();
	}

	public void battle(PrepageConnector prePage, WebCalculator calculator, Selenium webEngine, Config config) {
		if (prePage.findABattle()) {
			// Active Battle
			sleep(500);

			System.out.println("Found Game!");

			Battlefield battlefield = new Battlefield();

			BattleConnector battlePage = new BattleConnector(calculator, webEngine, battlefield);

			BattleAI ai = new BattleAI(battlefield);
			// setup battlefield active pokemon
			battlePage.startBattle(config.getBattleTeam(), ai);

			sleep(500);

			ChatConnector chat = new ChatConnector(webEngine);

			boolean ingame = true;
			int turn = 1;

			sleep(10000);

			while (ingame) {

				// realy wait for the new turn to start
				// with turn numbers ex

				battlePage.waitForTurn(turn, ingame, ai);

				System.out.println("Turn " + turn);

				turn++;
				if (battlePage.hasEnded()) {
					System.out.println("Game has ended");
					ingame = false;
					break;
				}
				
				battlePage.updatePokemon();
				
				System.out.println("My Pokemon: " + battlefield.getMyActivePokemonName() + ", Opp Pokemon: " + battlefield.getOppActivePokemonName());

				battlePage.tryMegaEvolve();

				sleep(100);

				battlePage.move(ai);

				while (battlePage.waitingForOpponent()) {
					sleep(1000);
				}

				sleep(3000);

				battlePage.setFaintedPokemon(chat.getChatContext());
			}

			prePage.gotToMainMenue();
			
			sleep(5000);
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
