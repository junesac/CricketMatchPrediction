package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.EventType;
import com.model.Player;

public class CricketMatch {

	private final int maximumBallsAllowed;
	private final int runNeeded;

	private Player playeOnStrike;
	private Player nonStriker;
	private int totalRunsScored;
	private int actualBallsBowled;
	private int wicketsFallen;
	private int oversBowled;

	private List<String> stats = new ArrayList<String>();

	public CricketMatch(int overs, int runNeeded) {
		this.maximumBallsAllowed = 6 * overs;
		this.runNeeded = runNeeded;
	}

	private void changeStrike() {
		Player temp = playeOnStrike;
		playeOnStrike = nonStriker;
		nonStriker = temp;
	}

	public int getActualBallsBowled() {
		return actualBallsBowled;
	}

	public int getMaximumBallsAllowed() {
		return maximumBallsAllowed;
	}

	public Player getNonStriker() {
		return nonStriker;
	}

	public int getOversBowled() {
		return oversBowled;
	}

	public Player getPlayeOnStrike() {
		return playeOnStrike;
	}

	public int getRunNeeded() {
		return runNeeded;
	}

	public List<String> getStats() {
		return stats;
	}

	public int getTotalRunsScored() {
		return totalRunsScored;
	}

	public int getWicketsFallen() {
		return wicketsFallen;
	}

	public void setActualBallsBowled(int actualBallsBowled) {
		this.actualBallsBowled = actualBallsBowled;
	}

	public void setNonStriker(Player nonStriker) {
		this.nonStriker = nonStriker;
	}

	public void setOversBowled(int oversBowled) {
		this.oversBowled = oversBowled;
	}

	public void setPlayeOnStrike(Player playeOnStrike) {
		this.playeOnStrike = playeOnStrike;
	}

	public void setStats(List<String> stats) {
		this.stats = stats;
	}

	public void setTotalRunsScored(int totalRunsScored) {
		this.totalRunsScored = totalRunsScored;
	}

	public void setWicketsFallen(int wicketsFallen) {
		this.wicketsFallen = wicketsFallen;
	}

	public void startGame(List<Player> players) {

		if (players == null || players.size() < 2) {
			throw new RuntimeException(
					"We need atleast 2 players to start the game.");
		}

		playeOnStrike = players.get(0);
		nonStriker = players.get(1);

		/**
		 * Index of players. It explains that how many players already played.
		 */
		int position = 1;

		while (position < players.size()
				&& actualBallsBowled < maximumBallsAllowed
				&& totalRunsScored < runNeeded
				&& wicketsFallen < players.size() - 1) {

			boolean out = false;

			String data = "";
			int event = (int) Math.floor(Math.random() * 101);

			actualBallsBowled++;

			Map<EventType, Integer> stats = playeOnStrike.getPlayerStats();

			/**
			 * Now we need to progress as per the happened event.
			 */
			// 1. No run scored
			if (event <= stats.get(EventType.ZERO)) {
				data = "No Run";
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);

			}
			// 2. one run scored
			else if (event <= stats.get(EventType.ONE)) {

				data = "1 run";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 1);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				// change the strike
				changeStrike();
				totalRunsScored++;
			}
			// 3. two run scored
			else if (event <= stats.get(EventType.TWO)) {

				data = "2 runs";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 2);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				totalRunsScored += 2;
			}
			// 4. three run scored
			else if (event <= stats.get(EventType.THREE)) {
				data = "3 runs";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 3);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				// change the strike
				changeStrike();
				totalRunsScored += 3;
			}
			// 5. four run scored
			else if (event < stats.get(EventType.FOUR)) {

				data = "4 runs";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 4);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				totalRunsScored += 4;
			}

			// 6. five run scored
			else if (event < stats.get(EventType.FIVE)) {
				data = "5 runs";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 5);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				// change the strike
				changeStrike();
				totalRunsScored += 5;
			}
			// 7. six run scored
			else if (event < stats.get(EventType.SIX)) {

				data = "6 runs";
				playeOnStrike.setRunScored(playeOnStrike.getRunScored() + 6);
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				totalRunsScored += 6;
			}

			// 8. Player out
			else if (event < stats.get(EventType.OUT)) {
				out = true;

				playeOnStrike.setOut(true);
				// System.out.println(oversBowled + "." + actualBallsBowled +
				// " "
				// + playeOnStrike.getName() + " got out on "
				// + playeOnStrike.getRunScored() + " runs");
				this.stats.add(oversBowled + "." + actualBallsBowled + " "
						+ playeOnStrike.getName() + " got out on "
						+ playeOnStrike.getRunScored() + " runs");
				wicketsFallen++;
				playeOnStrike
						.setBallsPlayed(playeOnStrike.getBallsPlayed() + 1);
				playeOnStrike = players.get(wicketsFallen);
			}

			if (!out) {
				// System.out.println(oversBowled + "." + actualBallsBowled +
				// " "
				// + playeOnStrike.getName() + " scored " + data);

				this.stats.add(oversBowled + "." + actualBallsBowled + " "
						+ playeOnStrike.getName() + " scored " + data);
				// System.out.println("Total runs scored : " + totalRunsScored);

			}
			out = false;
			data = "";

			if (actualBallsBowled % 6 == 0) {
				// System.out.println("Over finished");
				oversBowled++;
				actualBallsBowled = 0;
				changeStrike();
			}
		}

	}
}
