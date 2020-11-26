package program;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import board.Board;
import items.Card;
import saves.ItemDeserializer;

@JsonDeserialize(using = ItemDeserializer.class)
public class JackPocketGame {
	private Board board;
	private ArrayList<Card> cardDeck;

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

	public String toString() {
		return board + "\n" + cardDeck;
	}
}