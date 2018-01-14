package com.common;

import java.util.List;

import com.model.Player;
import com.service.CricketMatch;

public class PrintResult {
	public static void getMatchResult(CricketMatch match, int runNeeded,
			int overs, List<Player> players) {

		System.out.println();
		if (match.getTotalRunsScored() >= runNeeded) {

			System.out.println("Lenaburu won the game by "
					+ (players.size() - match.getWicketsFallen()) + " Wickets "
					+ " and " + (6 * overs - match.getActualBallsBowled())
					+ " balls to spare.");
		} else {
			System.out.println("Lenaburu lost the match by "
					+ (runNeeded - match.getTotalRunsScored()) + " runs.");
		}
		System.out.println();
	}

	public static void getCommentry(CricketMatch match) {
		System.out.println();
		System.out.println(match.getTeamName() + " innings: ");
		for (String s : match.getStats()) {
			System.out.println(s);
		}

	}

	public static void getPlayerDetails(String teamName, List<Player> players) {

		System.out.println();
		System.out.println(teamName + " innings: ");
		for (Player p : players) {
			System.out.print(p.getName() + " - " + p.getRunScored());
			if (!p.isOut()) {
				System.out.print("*");
			}
			System.out.println(" (" + p.getBallsPlayed() + " balls)");

		}
		System.out.println();
	}

	public static void calculateResult(CricketMatch firstInning,
			CricketMatch secondInning) {

		if (firstInning.getTotalRunsScored() > secondInning
				.getTotalRunsScored()) {
			System.out.println(firstInning.getTeamName()
					+ " won the match by "
					+ (firstInning.getTotalRunsScored() - secondInning
							.getTotalRunsScored()) + " runs.");
		} else {
			System.out.println(secondInning.getTeamName()
					+ " won the match "
					+ " with "
					+ (secondInning.getMaximumBallsAllowed() - secondInning
							.getActualBallsBowled()) + " Balls remainings");
		}

	}
}
