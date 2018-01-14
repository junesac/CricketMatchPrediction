package com.main;

import java.util.List;

import com.common.PrintResult;
import com.data.StaticData;
import com.model.Player;
import com.service.CricketMatch;

public class Set4Problem1AppMain {

	public static void main(String[] args) {

		List<Player> players = StaticData.getPlayers();
		int overs = 4;
		int runNeeded = 40;
		CricketMatch match = new CricketMatch("Lengaburu", overs, runNeeded);
		match.startGame(players);

		// Print match result
		PrintResult.getMatchResult(match, runNeeded, overs, players);

		// Need to add checks
		PrintResult.getPlayerDetails(match.getTeamName(), players);

		// Print Commentry
		PrintResult.getCommentry(match);

	}

}
