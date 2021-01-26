package program;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import board.Cell;
import board.detective.DetectiveName;
import board.district.Orientation;
import graphics.NewGraphicalWindow;
import graphics.NewJLabel;
import items.ActionToken;
import items.Actions;
import items.AlibiName;
import items.Card;
import players.Player;
import saves.ItemDeserializer;

@JsonIgnoreProperties(value = { "jackName", "beginWithWalls", "board" })
@JsonDeserialize(using = ItemDeserializer.class)
public class JackPocketGame extends Game {
	InputListener listener = new InputListener();
	private Board board;
	private List<Card> cardDeck;
	public List<ActionToken> actionTokenList = new ArrayList<>();
	private AlibiName jackName;
	private static Cell rotatedDistrict = null;
	private boolean beginWithWalls = true;


	public void playAction(ActionToken actionToken,NewGraphicalWindow window,JackPocketGame jackGame) throws InterruptedException {
		// Action methods
		// Get the current action of the token
		Actions actionToBePlayed;
		DetectiveName actionDetective;
		System.out.println(actionToken.toString());
		
		if (actionToken.isRecto()) {
			actionToBePlayed = actionToken.getAction1();
			actionDetective = actionToken.getAction1Detective();
		} else {
			actionToBePlayed = actionToken.getAction2();
			actionDetective = actionToken.getAction2Detective();
		}
		actionToken.setHasBeenPlayed(true);
		
		// Methods
		window.actionPlaying=true;
		switch (actionToBePlayed) {
		case MOVE_DETECTIVE:
			window.moveDetective=true;
			window.detectiveToMove=actionDetective;
			break;

		case MOVE_JOKER:
			window.moveJoker=true;
			break;

		case DRAW_CARD:
			window.drawCard=true;
			drawCard(this.getCurrentPlayer(),window,jackGame);
			break;

		case ROTATE_DISTRICT:
			window.rotateDistrict=true;
			break;

		case SWAP_DISTRICT:
			window.swapDistrict=true;
			break;

		}
		actionToken.setRecto(!actionToken.isRecto());
	}

	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount,NewGraphicalWindow window,JackPocketGame jackGame) {
		board.moveDetectiveToken( detectiveName,  cellCount, window, jackGame);
	}

	public void drawCard(Player player,NewGraphicalWindow window,JackPocketGame jackGame) throws InterruptedException {
		//Adds a card from the jackGame cardDeck to the player's cardDack
		if (!cardDeck.isEmpty()) {
			player.addAlibiCard(cardDeck.get(0));
			// Flip if detective
			if (player.getName().toString().equals( getPlayer1().getName().toString())) {
				
				board.flipDistrict(cardDeck.get(0).getCharacter(),window,jackGame);
				window.showCard(cardDeck.get(0).getCharacter().toString(),window,jackGame);
				
			} else if (player.getName().toString().equals(getPlayer2().getName().toString())) {
				player.setHourglass(player.getHourglass() + cardDeck.get(0).getHourglass());
				window.showCard(cardDeck.get(0).getCharacter().toString(),window,jackGame);
				
			}
			cardDeck.remove(0);
		}

	}

	public void rotate(Orientation orientation, List<Integer> coords,NewGraphicalWindow window ) {
			board.rotate(orientation,coords);						
	}

	public void swap(List<Integer> coord1, List<Integer> coord2) {
		// Swaps cells
		board.swapCells(coord1, coord2);
	}

	public void displayJack(NewGraphicalWindow  window) throws InterruptedException {	
	}

	public ActionToken actionGetFromList(NewGraphicalWindow window,String actionUsed,int actionPosition,JackPocketGame jackGame) {
		// Picks an action from the remaining list
		System.out.println(actionTokenList);
		window.information.setText(getCurrentPlayer().getName() + " it's your time to pick an action");
		Actions actionName = listener.getAction(actionUsed);
		ActionToken actionToken =jackGame.actionTokenList.get(actionPosition);
			if ((!actionToken.hasBeenPlayed())
					&& ((actionToken.isRecto() && (actionToken.getAction1().toString().equals(actionName.toString())))
							|| (!actionToken.isRecto()
									&& (actionToken.getAction2().toString().equals(actionName.toString()))))) {
				return actionToken;
				
			} else {
				window.information.setText("this action have been already played");
			}
		
		return null;
	}

	public Player hasReactedObjectives(NewGraphicalWindow window,JackPocketGame jackGame) {
		// Returns the player if any has reached an objective, else returns null
		List<AlibiName> visibleList = getBoard().visibleCharacters();
		boolean isJackVisible = false;
		Player winner = null;
		for (AlibiName visibleAlibi : visibleList) {
			if (visibleAlibi.toString().equals(jackName.toString())) {
				isJackVisible = true;
			}
		}

		int districtsLeft = getBoard().flipDistrict(isJackVisible, visibleList,window,jackGame); // Flips districts & returns the number
																					// of unflipped districts
		if (!isJackVisible) { // If jack is invisible
			getPlayer2().setHourglass(getPlayer2().getHourglass() + 1);
		}

		System.out.println("Jack has " + getPlayer2().getHourglass() + " hourglasses"); // if Jack has 6 or more
																						// hourglasses
		if (getPlayer2().getHourglass() >= 6) {
			System.out.println("WIN BY 6+ HOURGLASS");
			winner = getPlayer2();
		}

		if ((districtsLeft == 1) && getPlayer2().getHourglass()<6) { // if 1 district left
			System.out.println("WIN BY 1 DISTRICT LEFT");
			winner = getPlayer1();
		}

		if ((getTurnCount() == 8) && (districtsLeft == 1) && !isJackVisible) { // Turn 8 special win conditions
			System.out.println("WIN BY TURN 8");
			winner = getPlayer2();
		}
		return winner;
	}

	public void close() {
		// Close the listener
		listener.close();
	}

	// Getters and Setters
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public List<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(List<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public List<ActionToken> getActionTokenList() {
		return actionTokenList;
	}
	
	
	

	public void setActionTokenList(List<ActionToken> actionTokenList) {
		this.actionTokenList = actionTokenList;
	}

	public AlibiName getJackName() {
		return jackName;
	}

	public void setJackName(AlibiName jackName) {
		this.jackName = jackName;
	}

	public static Cell getRotatedDistrict() {
		return rotatedDistrict;
	}

	public static void setRotatedDistrict(Cell cell) {
		JackPocketGame.rotatedDistrict = cell;
	}

	public boolean getBeginWithWalls() {
		return beginWithWalls;
	}

	public void setBeginWithWalls(boolean beginWithWalls) {
		this.beginWithWalls = beginWithWalls;
	}

	// Console toString
	public String toString() {
		return board.toString();
	}

}