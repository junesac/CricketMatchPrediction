package com.main;

import java.util.List;

import com.data.StaticData;
import com.model.Player;
import com.service.CricketMatch;

public class AppMain {

	public static void main(String[] args) {

		List<Player> players = StaticData.getPlayers();
		System.out.println(players);

		System.out.println("Let's Play");
		int overs = 4;
		int runNeeded = 40;
		CricketMatch match = new CricketMatch(overs, runNeeded);
		match.startGame(players);

		// Print match result
		printMatchResult(match, runNeeded, players);

		// Need to add checks
		printPlayerDetails(players);

		// Print Commentry
		printCommentry(match.getStats());

	}

	private static void printMatchResult(CricketMatch match, int runNeeded,
			List<Player> players) {

		if (match.getTotalRunsScored() >= runNeeded) {

			System.out
					.println("Lenaburu won the game by "
							+ (players.size() - match.getWicketsFallen())
							+ " Wickets.");
		} else {
			System.out.println("Lenaburu lost the match by "
					+ (runNeeded - match.getTotalRunsScored()) + " runs.");
		}
		System.out.println();
	}

	private static void printCommentry(List<String> stats) {

		for (String s : stats) {
			System.out.println(s);
		}

	}

	private static void printPlayerDetails(List<Player> players) {

		for (Player p : players) {
			System.out.print(p.getName() + " - " + p.getRunScored());
			if (!p.isOut()) {
				System.out.print("*");
			}
			System.out.println(" (" + p.getBallsPlayed() + " balls)");

		}
		System.out.println();
	}
}
