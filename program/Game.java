package program;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;

import board.district.District;
import board.district.DistrictType;
import board.district.Orientation;
import items.ActionToken;
import items.Card;
import players.Player;
import saves.SaveLoad;

public class Game {
	@JsonIgnore
	private static Player player1 = null;
	@JsonIgnore
	private static Player player2 = null;
	@JsonIgnore
	private static Player currentPlayer = null;
	@JsonIgnore
	private static int turnCount = 0;

	@JsonIgnore
	public static int getTurnCount() {
		return turnCount;
	}

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

		System.out.println("Game creation finished !");
		// set JackCharacter
		jackGame.setJackName(jackGame.getCardDeck()
				.get(ThreadLocalRandom.current().nextInt(0, jackGame.getCardDeck().size())).getCharacter());
		// pop Jack from deck
		for (Card card : jackGame.getCardDeck()) {
			if (card.getCharacter() == jackGame.getJackName()) {
				jackGame.getCardDeck().remove(card);
				break;
			}
		}
		// Prompt to show jack
		jackGame.displayJack();
		GameTurn(jackGame);
	}

	// Handles game turns, either repeats or displays who won
	public static void GameTurn(JackPocketGame jackGame) {
		turnCount++;
		// Flip actiontokens (even turn) or randomize them (odd turn)
		for (ActionToken actionToken : jackGame.getActionTokenList()) {
			// even
			if (Math.floorMod(turnCount, 2) != 0) {
				actionToken.setRecto(ThreadLocalRandom.current().nextBoolean());
			}
		}

		System.out.println("It's " + currentPlayer.getName() + "'s turn to play");
		System.out.println(jackGame);
		jackGame.playAction(jackGame.actionGetFromList());
		jackGame.switchPlayer();
		System.out.println(jackGame);
		jackGame.playAction(jackGame.actionGetFromList());
		System.out.println(jackGame);
		jackGame.playAction(jackGame.actionGetFromList());
		jackGame.switchPlayer();
		System.out.println(jackGame);
		jackGame.playAction(jackGame.actionGetFromList());

		// Make the actiontolens reusables
		for (ActionToken actionToken : jackGame.getActionTokenList()) {
			actionToken.setHasBeenPlayed(false);
		}

		Player winningPlayer = jackGame.hasReactedObjectives();
		// If end goal has been reached
		if (winningPlayer != null) {
			System.out.println(winningPlayer + " wins, congratulations !!");
		}
		// Else continue the game
		else {
			GameTurn(jackGame);
		}
	}

	@JsonIgnore
	public Player getPlayer2() {
		return player2;
	}

	@JsonIgnore
	public Player getPlayer1() {
		return player1;
	}

}
