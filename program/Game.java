package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import board.detective.DetectiveName;
import board.district.District;
import board.district.DistrictType;
import board.district.Orientation;
import items.ActionToken;
import items.Actions;
import players.Player;
import saves.SaveLoad;

public class Game {
	@JsonIgnore
	private static Player player1 = null;
	@JsonIgnore
	private static Player player2 = null;
	@JsonIgnore
	private static Player currentPlayer = null;
	private static int turnCount = 0;

	public static void setCurrentPlayer(Player currentPlayer) {
		Game.currentPlayer = currentPlayer;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void switchPlayer() {
		if (currentPlayer == player1) {
			setCurrentPlayer(player2);
		} else if (currentPlayer == player2) {
			setCurrentPlayer(player1);
		}
	}

	// public Game() throws JsonProcessingException { launchGame(); }

	public static void launchGame() throws JsonProcessingException {
		// Load classic game board and card set
		String localFile = System.getProperty("user.dir") + "\\resources\\classicJack.json";
		JackPocketGame jackGame = SaveLoad.Load(localFile);
		player1 = new Player(false, "Detective");
		player2 = new Player(true, "Jack");
		setCurrentPlayer(player1);
		Collections.shuffle(jackGame.getCardDeck());

		int sizex = jackGame.getBoard().getBoard().length;
		int sizey = jackGame.getBoard().getBoard()[0].length;
		// Randomize board
		for (int r = 1; r < 18; r++) {
			jackGame.swap(
					Arrays.asList(ThreadLocalRandom.current().nextInt(1, sizex - 1),
							ThreadLocalRandom.current().nextInt(1, sizey - 1)),
					Arrays.asList(ThreadLocalRandom.current().nextInt(1, sizex - 1),
							ThreadLocalRandom.current().nextInt(1, sizey - 1)));
		}
		// randomize orientation
		for (int y = 1; y < sizey - 1; y++) {
			for (int x = 1; x < sizex - 1; x++) {
				((District) jackGame.getBoard().getCell(Arrays.asList(x, y)))
						.setOrientation(Orientation.randomOrientation());
			}
		}

		// Makes the detectives face a wall at the beginning
		// optionnal disable with :
		// jackGame.setBeginWithWalls(false);
		if (jackGame.getBeginWithWalls()) {
			((District) jackGame.getBoard().getCell(Arrays.asList(1, 1))).setDistrictType(DistrictType.T_SHAPE);
			((District) jackGame.getBoard().getCell(Arrays.asList(1, 3))).setDistrictType(DistrictType.T_SHAPE);
			((District) jackGame.getBoard().getCell(Arrays.asList(3, 2))).setDistrictType(DistrictType.T_SHAPE);
			jackGame.rotate(Orientation.EAST, Arrays.asList(1, 1));
			jackGame.rotate(Orientation.WEST, Arrays.asList(1, 3));
			jackGame.rotate(Orientation.NORTH, Arrays.asList(3, 2));
		}

		System.out.println("Génération de la partie terminé");
		System.out.println(jackGame);
		GameTurn();
	}

	public static void GameTurn() {
		turnCount++;
		System.out.println("It's " + currentPlayer.getName() + "'s turn to play");
		ChooseAction();

		// Make the actions avaliables again

	}

	public boolean[] hasReactedObjectives() {
		return null;
	}

	public void handleEnd() {

	}

	public static void ChooseAction() {

	}

}
