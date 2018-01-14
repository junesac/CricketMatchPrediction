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

		while (actualBallsBowled < maximumBallsAllowed
				&& totalRunsScored < runNeeded
				&& wicketsFallen < players.size() - 1) {

			// If player got out then bring the new Player.
			getPlayerInCasePlayerOnStrikeGotOut(players);

			int event = (int) Math.floor(Math.random() * 101);
			Map<EventType, Integer> stats = playeOnStrike.getPlayerStats();
			// player played the balls
			playTheBall();

			/**
			 * Now we need to progress as per the happened event.
			 */
			// 1. No run scored & No wicket fallen
			if (event <= stats.get(EventType.ZERO)) {
				// do nothing
				addRun(0);
			}
			// 2. one run scored
			else if (event <= stats.get(EventType.ONE)) {
				addRun(1);
				changeStrike();
			}
			// 3. two run scored
			else if (event <= stats.get(EventType.TWO)) {
				addRun(2);
			}
			// 4. three run scored
			else if (event <= stats.get(EventType.THREE)) {
				addRun(3);
				changeStrike();
			}
			// 5. four run scored
			else if (event < stats.get(EventType.FOUR)) {
				addRun(4);
			}

			// 6. five run scored
			else if (event < stats.get(EventType.FIVE)) {
				addRun(5);
				changeStrike();
			}
			// 7. six run scored
			else if (event < stats.get(EventType.SIX)) {
				addRun(6);
			}

			// 8. Player out
			else if (event < stats.get(EventType.OUT)) {
				setOut();
				wicketsFallen++;
			}

			if (actualBallsBowled % 6 == 0) {
				changeStrike();
			}
		}

	}

	private void getPlayerInCasePlayerOnStrikeGotOut(List<Player> players) {
		if (playeOnStrike.isOut()) {
			playeOnStrike = players.get(wicketsFallen + 1);
		}
	}

	private void setOut() {
		playeOnStrike.setOut(true);
		stats.add("" + actualBallsBowled / 6 + "." + actualBallsBowled % 6
				+ " " + playeOnStrike.getName() + " got out on "
				+ playeOnStrike.getRunScored() + " runs.");
	}

	private void addRun(int i) {
		totalRunsScored += i;
		playeOnStrike.madeRun(i);
		stats.add("" + actualBallsBowled / 6 + "." + actualBallsBowled % 6
				+ " " + playeOnStrike.getName() + " scores " + i
				+ (i > 1 ? " runs." : " run."));
	}

	private void playTheBall() {
		actualBallsBowled++;
		playeOnStrike.bowlPlayed();
	}
}
