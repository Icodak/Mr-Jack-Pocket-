package program;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import board.Cell;
import board.detective.DetectiveName;
import board.district.Orientation;
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
	private List<ActionToken> actionTokenList = new ArrayList<>();
	private AlibiName jackName;
	private static Cell rotatedDistrict = null;
	private boolean beginWithWalls = true;

	public void playAction(ActionToken actionToken) {
		// Action methods
		// Get the current action of the token
		Actions actionToBePlayed;
		DetectiveName actionDetective;
		if (actionToken.isRecto()) {
			actionToBePlayed = actionToken.getAction1();
			actionDetective = actionToken.getAction1Detective();
		} else {
			actionToBePlayed = actionToken.getAction2();
			actionDetective = actionToken.getAction2Detective();
		}
		actionToken.setHasBeenPlayed(true);

		// Methods
		switch (actionToBePlayed) {
		case MOVE_DETECTIVE:
			System.out.println("Number of steps to move");
			moveDetectiveToken(actionDetective, Math.min(Math.max(1, listener.getInputInt()), 2));
			break;

		case MOVE_JOKER:
			int moveCount = 1;
			if (getCurrentPlayer().isJack()) {
				System.out.println("Number of steps to move");
				moveCount = Math.min(Math.max(0, listener.getInputInt()), 1);
			}
			System.out.println("Detective to move");
			DetectiveName detectiveName = listener.getInputDetective();
			moveDetectiveToken(detectiveName, moveCount);
			System.out.println("Moved " + detectiveName);
			break;

		case DRAW_CARD:
			drawCard(this.getCurrentPlayer());
			break;

		case ROTATE_DISTRICT:
			System.out.println("District to rotate");
			List<Integer> coords = listener.getInputCoord();
			System.out.println("New orientation");
			Orientation orientation = listener.getInputOrientation();
			rotate(orientation, coords);
			break;

		case SWAP_DISTRICT:
			System.out.println("First district");
			List<Integer> coord1 = listener.getInputCoord();
			System.out.println("Second district");
			List<Integer> coord2 = listener.getInputCoord();
			swap(coord1, coord2);
			break;

		}
		actionToken.setRecto(!actionToken.isRecto());
	}

	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		board.moveDetectiveToken(detectiveName, cellCount);
	}

	public void drawCard(Player player) {
		//Adds a card from the jackGame cardDeck to the player's cardDack
		if (!cardDeck.isEmpty()) {
			player.addAlibiCard(cardDeck.get(0));
			// Flip if detective
			if (player == getPlayer1()) {
				board.flipDistrict(cardDeck.get(0).getCharacter());
			} else if (player == getPlayer2()) {
				player.setHourglass(player.getHourglass() + cardDeck.get(0).getHourglass());
			}
			cardDeck.remove(0);
		}

	}

	public void rotate(Orientation orientation, List<Integer> coords) {
		//Rotates the cell
		if ((board.getCell(coords) != JackPocketGame.getRotatedDistrict())) {
			board.rotate(orientation, coords);
		} else {
			System.out.println("Cannot rotate already rotated cell in same turn");
			System.out.println("District to rotate");
			coords = listener.getInputCoord();
			System.out.println("New orientation");
			orientation = listener.getInputOrientation();
			rotate(orientation, coords);
		}
	}

	public void swap(List<Integer> coord1, List<Integer> coord2) {
		// Swaps cells
		board.swapCells(coord1, coord2);
	}

	public void displayJack() {
		listener.showJack();
		System.out.println(jackName.toString());
		listener.hideJack();
	}

	public ActionToken actionGetFromList() {
		// Picks an action from the remaining list
		System.out.println(getCurrentPlayer().getName() + " it's your time to pick an action");
		System.out.println(actionTokenList);

		Actions actionName = listener.getAction();
		for (ActionToken actionToken : actionTokenList) {
			if ((!actionToken.hasBeenPlayed())
					&& ((actionToken.isRecto() && (actionToken.getAction1().toString().equals(actionName.toString())))
							|| (!actionToken.isRecto()
									&& (actionToken.getAction2().toString().equals(actionName.toString()))))) {
				return actionToken;
			} else {
				System.out.println("Invalid action name");
				actionGetFromList();
			}
		}
		return null;
	}

	public Player hasReactedObjectives() {
		// Returns the player if any has reached an objective, else returns null
		List<AlibiName> visibleList = getBoard().visibleCharacters();
		boolean isJackVisible = false;
		Player winner = null;
		for (AlibiName visibleAlibi : visibleList) {
			if (visibleAlibi.toString().equals(jackName.toString())) {
				isJackVisible = true;
			}
		}

		int districtsLeft = getBoard().flipDistrict(isJackVisible, visibleList); // Flips districts & returns the number
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

		if ((districtsLeft == 1) && isJackVisible) { // if 1 district left & jack visible
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