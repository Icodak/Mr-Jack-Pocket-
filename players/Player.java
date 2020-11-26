package players;
import java.util.ArrayList;

import items.Card;
import items.TimeToken;

public class Player {
	private ArrayList<Card> alibiDeck;
	private TimeToken[] turnToken;

	public Player(ArrayList<Card> alibiDeck, TimeToken[] turnToken) {
		super();
		this.alibiDeck = alibiDeck;
		this.turnToken = turnToken;
	}

	public ArrayList<Card> getAlibiDeck() {
		return alibiDeck;
	}

	public void setAlibiDeck(ArrayList<Card> alibiDeck) {
		this.alibiDeck = alibiDeck;
	}

	public void addAlibiCard(Card card) {
		alibiDeck.add(card);
	}

	public void removeAlibiCard(Card card) {
		alibiDeck.remove(card);
	}

	public TimeToken[] getTurnToken() {
		return turnToken;
	}

	public void setTurnToken(TimeToken[] turnToken) {
		this.turnToken = turnToken;
	}

}