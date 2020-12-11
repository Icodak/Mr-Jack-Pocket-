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
	private List<Card> cardDeck;
	private List<ActionToken> actionTokenList = new ArrayList<>();
	InputListener listener = new InputListener();
	@JsonIgnore
	private AlibiName jackName;

	@JsonIgnore
	public AlibiName getJackName() {
		return jackName;
	}

	@JsonIgnore
	public void setJackName(AlibiName jackName) {
		this.jackName = jackName;
	}

	@JsonIgnore
	private boolean beginWithWalls = true;

	@Override
	@JsonIgnore
	public Player getPlayer1() {
		return super.getPlayer1();
	}

	@Override
	@JsonIgnore
	public Player getPlayer2() {
		return super.getPlayer2();
	}

	@Override
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

	public List<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(List<Card> cardDeck) {
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

	public void swap(List<Integer> coord1, List<Integer> coord2) {
		board.swapCells(coord1, coord2);
	}

	public void rotate(Orientation orientation, List<Integer> coords) {
		board.rotate(orientation, coords);
	}

	public String toString() {
		return board.toString();
	}

	public List<ActionToken> getActionTokenList() {
		return actionTokenList;
	}

	public void setActionTokenList(List<ActionToken> actionTokenList) {
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
		System.out.println(jackName.toString());
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
		List<AlibiName> visibleList = getBoard().visibleCharacters();
		boolean isJackVisible = false;
		Player winner = null;
		for (AlibiName visibleAlibi : visibleList) {
			if (visibleAlibi.toString().equals(jackName.toString())) {
				isJackVisible = true;
			}
		}
		// Flips districts & returns the number of unflipped districts
		int districtsLeft = getBoard().flipDistrict(isJackVisible, visibleList);

		// If jack is invisible
		if (!isJackVisible) {
			getPlayer2().setHourglass(getPlayer2().getHourglass() + 1);
		}

		/*
		 * TODO interdit de repivoter un quartier deja pivote
		 */

		// if Jack has 6 or more hourglasses
		System.out.println("Jack has " + getPlayer2().getHourglass() + " hourglasses");
		if (getPlayer2().getHourglass() >= 6) {
			System.out.println("WIN BY 6+ HOURGLASS");
			winner = getPlayer2();
		}

		// if 1 district left & jack visible
		if ((districtsLeft == 1) && isJackVisible) {
			System.out.println("WIN BY 1 DISTRICT LEFT");
			winner = getPlayer1();
		}

		// Turn 8 special win conditions
		if ((getTurnCount() == 8) && (districtsLeft == 1) && !isJackVisible) {
			System.out.println("WIN BY TURN 8");
			winner = getPlayer2();
		}

		return winner;
	}

}