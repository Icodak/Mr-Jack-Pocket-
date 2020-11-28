package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import board.detective.DetectiveName;
import board.district.District;
import board.district.Orientation;
import items.ActionToken;
import items.Actions;
import items.Card;
import players.Player;
import saves.ItemDeserializer;

@JsonDeserialize(using = ItemDeserializer.class)
public class JackPocketGame {
	private Board board;
	private ArrayList<Card> cardDeck;
	private Player currentPlayer;
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	@SuppressWarnings("unused")
	private Player player1;
	@SuppressWarnings("unused")
	private Player player2;


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

	//Actions
	public void playAction(ActionToken actionToken){
		Actions actionToBePlayed;
		DetectiveName actionDetective;
		int moveCount;
		if (actionToken.isRecto()) {actionToBePlayed = actionToken.getAction1();
		actionDetective = actionToken.getAction1Detective();
		} else {actionToBePlayed = actionToken.getAction2();
		actionDetective = actionToken.getAction2Detective();
		}
		switch(actionToBePlayed) {
		case MOVE_DETECTIVE:
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Déplacer de combien de cases ?");
			moveCount = sc1.nextInt();
			moveDetectiveToken(actionDetective, moveCount,Actions.MOVE_DETECTIVE.getLimitMove());
			break;
		case MOVE_JOCKER: 
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Déplacer de combien de cases ?");
			moveCount = sc2.nextInt();
			System.out.println("Quel détective à déplacer ?");
			String stringDetective = sc2.next();
			  for (DetectiveName c : DetectiveName.values()) {
			        if (c.name().equals(stringDetective.toUpperCase())) {
			        	DetectiveName detectiveName = DetectiveName.valueOf((stringDetective.toUpperCase()));
			        	moveDetectiveToken(detectiveName, moveCount,Actions.MOVE_JOCKER.getLimitMove());
			        	System.out.println("Moved " + stringDetective.toUpperCase());
			        }
			    }
			
			break;
		case DRAW_CARD:
			drawCard(this.currentPlayer);
			break;
		case ROTATE_DISTRICT:
			Scanner sc3 = new Scanner(System.in);
			System.out.println("Case à tourner?\nx:");
			int coordr1 = sc3.nextInt();
			System.out.println("y:");
			int coordr2 = sc3.nextInt();
			System.out.println("Nouvelle Orientation:");
			String stringOrientation = sc3.next();
			  for (Orientation c : Orientation.values()) {
			        if (c.name().equals(stringOrientation.toUpperCase())) {
						Orientation orientation = Orientation.valueOf((stringOrientation.toUpperCase()));
						rotate(orientation,Arrays.asList(coordr1,coordr2));
			        }
			    }
		  
			  if (!((District) board.getCell(Arrays.asList(coordr1,coordr2))).getOrientation().toString().equals(stringOrientation.toUpperCase())) {
				 
				  System.out.println("Invalid rotation");
				  break;
			  }
			  
			break;
		case SWAP_DISTRICT:
			Scanner sc4 = new Scanner(System.in);
			System.out.println("Case d'origine?\nx:");
			int coord11 = sc4.nextInt();
			System.out.println("\ny:");
			int coord12 = sc4.nextInt();
			System.out.println("Case d'arrivée?");
			int coord21 = sc4.nextInt();
			System.out.println("\ny:");
			int coord22 = sc4.nextInt();
			swap(Arrays.asList(coord11,coord12),Arrays.asList(coord21,coord22));
			break;
		
		}
		actionToken.setRecto(!actionToken.isRecto());
			
	}
	
	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount, int limit) {
		cellCount = Math.min(cellCount, limit);
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