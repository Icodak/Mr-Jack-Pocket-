package players;
import java.util.ArrayList;

import items.Card;
import items.TimeToken;

public class Player {
	private ArrayList<Card> alibiDeck = new ArrayList<>();
	private ArrayList<TimeToken> turnToken = new ArrayList<>();
	private boolean isJack = false;
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJack(boolean isJack) {
		this.isJack = isJack;
	}

	public Player(boolean isJack, String name) {
		setJack(isJack);
		setName(name);
	}
	
	public boolean isJack() {
		return isJack;
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

	public ArrayList<TimeToken> getTurnToken() {
		return turnToken;
	}

	public void setTurnToken(ArrayList<TimeToken> turnToken) {
		this.turnToken = turnToken;
	}

}