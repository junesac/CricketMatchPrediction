package com.model;

import java.util.HashMap;
import java.util.Map;

public class Player {

	private final String name;
	private final Map<EventType, Integer> playerStats;

	private boolean isOut;
	private int runScored;
	private int ballsPlayed;

	public Player(String name, HashMap<EventType, Integer> stats) {
		this.name = name;
		playerStats = stats;
		isOut = false;
	}

	public boolean isOut() {
		return isOut;
	}

	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

	public int getRunScored() {
		return runScored;
	}

	public void setRunScored(int runScored) {
		this.runScored = runScored;
	}

	public int getBallsPlayed() {
		return ballsPlayed;
	}

	public void setBallsPlayed(int ballsPlayed) {
		this.ballsPlayed = ballsPlayed;
	}

	public Map<EventType, Integer> getPlayerStats() {
		return new HashMap<EventType, Integer>(playerStats);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", playerStats=" + playerStats
				+ ", isOut=" + isOut + ", runScored=" + runScored
				+ ", ballsPlayed=" + ballsPlayed + "]";
	}

}
