package com.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model.EventType;
import com.model.Player;

/**
 * This class contains the static data. We could have take the input from user
 * or from a file as well.
 *
 * @author user
 *
 */

public class StaticData {

	public static List<Player> getPlayers() {

		List<Player> players = new ArrayList<Player>();

		// Creating player : kirat boli
		players.add(new Player("Kirat Boli", createStats(5, 30, 25, 10, 15, 1,
				9, 5)));

		// Creating N.S.Nodhi
		players.add(new Player("N.S.Nodhi", createStats(10, 40, 20, 5, 10, 1,
				4, 10)));

		// Creating R Rumrah
		players.add(new Player("R.Rumrah", createStats(20, 30, 15, 5, 5, 1, 4,
				20)));

		// Creating Shashi Henra
		players.add(new Player("Shashi Henra", createStats(30, 25, 5, 0, 5, 1,
				4, 30)));

		return players;
	}

	private static HashMap<EventType, Integer> createStats(int dotBall,
			int one, int two, int three, int four, int five, int six, int out) {

		if (dotBall + one + two + three + four + five + six + out != 100) {
			throw new RuntimeException(
					"probability of all possible events should be 100");
		}

		/**
		 * I store the cummulative probability so random number generation would
		 * be fair.
		 */

		HashMap<EventType, Integer> stats = new HashMap<EventType, Integer>();
		stats.put(EventType.ZERO, dotBall);
		stats.put(EventType.ONE, stats.get(EventType.ZERO) + one);
		stats.put(EventType.TWO, stats.get(EventType.ONE) + two);
		stats.put(EventType.THREE, stats.get(EventType.TWO) + three);
		stats.put(EventType.FOUR, stats.get(EventType.THREE) + four);
		stats.put(EventType.FIVE, stats.get(EventType.FOUR) + five);
		stats.put(EventType.SIX, stats.get(EventType.FIVE) + six);
		stats.put(EventType.OUT, 100);
		return stats;
	}

	public static List<Player> getPlayerForLengaBuruForTieBreaker() {

		List<Player> players = new ArrayList<Player>();

		players.add(new Player("Kirat Boli", createStats(5, 10, 25, 10, 25, 1,
				14, 10)));

		players.add(new Player("N.S.Nodhi", createStats(5, 15, 15, 10, 20, 1,
				19, 15)));

		return players;
	}

	public static List<Player> getPlayerForQueensForTieBreaker() {

		List<Player> players = new ArrayList<Player>();

		players.add(new Player("D B Velleyers ", createStats(5, 10, 25, 10, 25,
				1, 14, 10)));

		players.add(new Player("H Mamla", createStats(10, 15, 15, 10, 20, 1,
				19, 10)));

		return players;

	}

}
