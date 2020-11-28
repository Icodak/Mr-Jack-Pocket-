package program;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import board.detective.DetectiveName;
import board.district.Orientation;
import items.ActionToken;
import items.Actions;
import items.Card;
import players.Player;
import saves.ItemDeserializer;

@JsonDeserialize(using = ItemDeserializer.class)
public class JackPocketGame extends Game {
	private Board board;
	private ArrayList<Card> cardDeck;
InputListener listener = new InputListener();


	public Player getPlayer1() {
		return getPlayer1();
	}

	public Player getPlayer2() {
		return getPlayer2();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public ArrayList<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}
	
	public void close() {
		listener.close();
	}

	// Action methods
	public void playAction(ActionToken actionToken) {
		Actions actionToBePlayed;
		DetectiveName actionDetective;
		// Get the current action of the token
		if (actionToken.isRecto()) {
			actionToBePlayed = actionToken.getAction1();
			actionDetective = actionToken.getAction1Detective();
		} else {
			actionToBePlayed = actionToken.getAction2();
			actionDetective = actionToken.getAction2Detective();
		}
		// Methods
		switch (actionToBePlayed) {
		case MOVE_DETECTIVE:
			System.out.println("Number of steps to move");
			moveDetectiveToken(actionDetective, Math.min(Math.max(1, listener.getInputInt()), 2));
			break;

		case MOVE_JOKER:
			// Alike MOVE_DETECTIVE but let the player have the choice of who to move
			System.out.println("Number of steps to move");
			int moveCount = 1;
			if (getCurrentPlayer().isJack()) {
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
		if (cardDeck.size() > 0) {
			player.getAlibiDeck().add(cardDeck.get(0));
			cardDeck.remove(0);
		}

	}

	public void swap(List<Integer> coord1, List<Integer> coord2) {
		board.swapCells(coord1, coord2);
	}

	public void rotate(Orientation orientation, List<Integer> coords) {
		board.rotate(orientation, coords);
	}

	public String toString() {
		return board + "\n" + cardDeck;
	}
}