package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;

import board.detective.DetectiveName;
import board.district.District;
import board.district.Orientation;
import items.ActionToken;
import items.Actions;
import players.Player;
import saves.SaveLoad;

public class Game {
	@JsonIgnore
	private Player player1;
	@JsonIgnore
	private Player player2;
	@JsonIgnore
	private static Player currentPlayer;

	public static void setCurrentPlayer(Player currentPlayer) {
		Game.currentPlayer = currentPlayer;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/*
	 * public Game() { launchGame(); }
	 */
	public static void launchGame() {
		// Load classic game board and card set
		String localFile = System.getProperty("user.dir") + "\\resources\\classicJack.json";
		JackPocketGame jackGame = SaveLoad.Load(localFile);
		Player playerDetective = new Player(false);
		Player playerJack = new Player(true);
		setCurrentPlayer(playerDetective);
		Collections.shuffle(jackGame.getCardDeck());

		ActionToken actionToken1 = new ActionToken(Actions.MOVE_DETECTIVE, Actions.DRAW_CARD);
		ActionToken actionToken2 = new ActionToken(Actions.MOVE_DETECTIVE, Actions.MOVE_DETECTIVE);
		ActionToken actionToken3 = new ActionToken(Actions.SWAP_DISTRICT, Actions.ROTATE_DISTRICT);
		ActionToken actionToken4 = new ActionToken(Actions.MOVE_JOKER, Actions.ROTATE_DISTRICT);
		actionToken1.setAction1Detective(DetectiveName.SHERLOCK);
		actionToken2.setAction1Detective(DetectiveName.WATSON);
		actionToken2.setAction2Detective(DetectiveName.TOBBY);
		ArrayList<ActionToken> actionTokenList = new ArrayList<>(
				Arrays.asList(actionToken1, actionToken2, actionToken3, actionToken4));

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
		jackGame.rotate(Orientation.EAST, Arrays.asList(1, 1));
		jackGame.rotate(Orientation.WEST, Arrays.asList(1, 3));
		jackGame.rotate(Orientation.NORTH, Arrays.asList(3, 2));
		
		System.out.println("Génération de la partie terminé");
		System.out.println(jackGame);

	}

}
