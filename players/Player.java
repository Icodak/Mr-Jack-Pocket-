package players;

import java.util.ArrayList;

import items.Card;

public class Player {
	private ArrayList<Card> alibiDeck = new ArrayList<>();
	private int hourglass;

	public int getHourglass() {
		return hourglass;
	}

	public void setHourglass(int hourglass) {
		this.hourglass = hourglass;
	}

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
		System.out.println("Card added : " + card);
		alibiDeck.add(card);
	}

	public void removeAlibiCard(Card card) {
		alibiDeck.remove(card);
	}

	public String toString() {
		return getName();
	}

}