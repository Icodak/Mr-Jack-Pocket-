package program;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import board.detective.DetectiveName;
import board.district.Orientation;
import items.ActionToken;
import items.Actions;
import items.AlibiName;
import items.Card;
import players.Player;
import saves.ItemDeserializer;

@JsonDeserialize(using = ItemDeserializer.class)
public class JackPocketGame extends Game {
	private Board board;
	private ArrayList<Card> cardDeck;
	private ArrayList<ActionToken> actionTokenList = new ArrayList<>();
	InputListener listener = new InputListener();
	@JsonIgnore
	private AlibiName JackName;
	
	@JsonIgnore
	public AlibiName getJackName() {
		return JackName;
	}
	
	@JsonIgnore
	public void setJackName(AlibiName jackName) {
		JackName = jackName;
	}

	@JsonIgnore
	private boolean beginWithWalls = true;

	@JsonIgnore
	public Player getPlayer1() {
		return super.getPlayer1();
	}

	@JsonIgnore
	public Player getPlayer2() {
		return super.getPlayer2();
	}

	@JsonIgnore
	public Player getCurrentPlayer() {
		return super.getCurrentPlayer();
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
		// Declare it as played
		actionToken.setHasBeenPlayed(true);
		// Methods
		switch (actionToBePlayed) {
		case MOVE_DETECTIVE:
			System.out.println("Number of steps to move");
			moveDetectiveToken(actionDetective, Math.min(Math.max(1, listener.getInputInt()), 2));
			break;

		case MOVE_JOKER:
			// Alike MOVE_DETECTIVE but let the player have the choice of who to move
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
		return board.toString();
	}

	public ArrayList<ActionToken> getActionTokenList() {
		return actionTokenList;
	}

	public void setActionTokenList(ArrayList<ActionToken> actionTokenList) {
		this.actionTokenList = actionTokenList;
	}

	@JsonIgnore
	public boolean getBeginWithWalls() {
		return beginWithWalls;
	}

	@JsonIgnore
	public void setBeginWithWalls(boolean beginWithWalls) {
		this.beginWithWalls = beginWithWalls;
	}
	
	public void displayJack() {
		listener.showJack();
		System.out.println(JackName.toString());
		listener.hideJack();
	}
	

	public ActionToken actionGetFromList() {
		System.out.println(getCurrentPlayer().getName() + " it's your time to pick an action");
		System.out.println(actionTokenList);
		Actions actionName = null;
		boolean isValid = false;
		while (!isValid) {
			actionName = listener.getAction();
			for (ActionToken actionToken : actionTokenList) {
				if (actionName != null) {
					if ((!actionToken.hasBeenPlayed()) && ((actionToken.isRecto()
							&& (actionToken.getAction1().toString().equals(actionName.toString())))
							|| (!actionToken.isRecto()
									&& (actionToken.getAction2().toString().equals(actionName.toString()))))) {
						isValid = true;
						return actionToken;
					}
				}
			}
		}
		return null;
	}
	
	public Player hasReactedObjectives() {
		/*TODO
		*Flip tiles depending on the visibility of jack
		*Calculate  jack's hourglass count
		*calculate the number of unflipped tiles
		*check if turn 8
		*return the correct value
		*????
		*profit
		*/
		getBoard().visibleCharacters();
		
		return null;
	}
	
	
	
}