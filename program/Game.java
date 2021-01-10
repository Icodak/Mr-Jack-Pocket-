package program;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.UnsupportedLookAndFeelException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import board.district.District;
import board.district.DistrictType;
import board.district.Orientation;
import graphics.NewGraphicalWindow;
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

	
	// public Game() throws JsonProcessingException { launchGame(); }

	public static void launchGame(String path,NewGraphicalWindow window) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {

		// Load classic game board and card set
		String localFile = System.getProperty("user.dir") + path; //"\\resources\\saved_games\\classicJack.json"
		JackPocketGame jackGame = SaveLoad.load(localFile);
		player1 = new Player(false, "Detective");
		player2 = new Player(true, "Jack");
		setCurrentPlayer(player2);
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

		// Randomize orientation
		for (int y = 1; y < sizey - 1; y++) {
			for (int x = 1; x < sizex - 1; x++) {
				((District) jackGame.getBoard().getCell(Arrays.asList(x, y)))
						.setOrientation(Orientation.randomOrientation());
			}
		}

		// Makes the detectives face a wall at the beginning
		// optionnal disable with : method setBeginWithWalls to false
		if (jackGame.getBeginWithWalls()) {
			((District) jackGame.getBoard().getCell(Arrays.asList(1, 1))).setDistrictType(DistrictType.T_SHAPE);
			((District) jackGame.getBoard().getCell(Arrays.asList(1, 3))).setDistrictType(DistrictType.T_SHAPE);
			((District) jackGame.getBoard().getCell(Arrays.asList(3, 2))).setDistrictType(DistrictType.T_SHAPE);
			jackGame.getBoard().rotate(Orientation.EAST, Arrays.asList(1, 1),window);
			jackGame.getBoard().rotate(Orientation.WEST, Arrays.asList(1, 3),window );
			jackGame.getBoard().rotate(Orientation.NORTH, Arrays.asList(3, 2),window );

		}

		// Set JackCharacter
		jackGame.setJackName(jackGame.getCardDeck()
				.get(ThreadLocalRandom.current().nextInt(0, jackGame.getCardDeck().size())).getCharacter());

		// Pop Jack from deck
		for (Card card : jackGame.getCardDeck()) {
			if (card.getCharacter() == jackGame.getJackName()) {
				jackGame.getCardDeck().remove(card);
				break;
			}
		}

		System.out.println("Game creation finished !");
		window.initialize(window,jackGame);
	
		
		//Prompt to show jack
		jackGame.displayJack(window);
		window.information.setText("appuyez sur la pile de carte pour dévoilez votre carte Mr.Jack");
		
		
	}

	public static void gameTurn(JackPocketGame jackGame,NewGraphicalWindow window) {
		// Handles game turns, either repeats or displays who won

		turnCount++; // New turn
		window.listegauche[turnCount-2].setVisible(false);
		for (ActionToken actionToken : jackGame.getActionTokenList()) { // Make the actiontokens reusables
			actionToken.setHasBeenPlayed(false);
		}
		JackPocketGame.setRotatedDistrict(null); // Enable rotation
		
		for (ActionToken actionToken : jackGame.getActionTokenList()) { // Flip actiontokens (even turn) or randomize
			// them (odd turn)
			if (Math.floorMod(turnCount, 2) != 0) { // even
				actionToken.setRecto(ThreadLocalRandom.current().nextBoolean());
			}
		}
		window.randomizeAction(jackGame);
	}
	
	
	public static void gameTurnInitialize(JackPocketGame jackGame,NewGraphicalWindow window) {
		turnCount++; // New turn
		for (ActionToken actionToken : jackGame.getActionTokenList()) { // Make the actiontokens reusables
			actionToken.setHasBeenPlayed(false);
		}
		JackPocketGame.setRotatedDistrict(null); // Enable rotation
		
		for (ActionToken actionToken : jackGame.getActionTokenList()) { // Flip actiontokens (even turn) or randomize
			// them (odd turn)
			if (Math.floorMod(1, 2) != 0) { // even
				actionToken.setRecto(ThreadLocalRandom.current().nextBoolean());
			}
		}
		window.randomizeAction(jackGame);
	}
	
	public void switchPlayer() {
		//Switches the player order
		if (currentPlayer.toString().equals(player1.toString())) {
			setCurrentPlayer(player2);
		} else if (currentPlayer.toString().equals(player2.toString())) {
			setCurrentPlayer(player1);
		}
	}

	// Getters and Setters
	@JsonIgnore
	public Player getPlayer1() {
		return player1;
	}

	@JsonIgnore
	public Player getPlayer2() {
		return player2;
	}

	public static void setCurrentPlayer(Player currentPlayer) {
		Game.currentPlayer = currentPlayer;
	}

	@JsonIgnore
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@JsonIgnore
	public static int getTurnCount() {
		return turnCount;
	}

}
