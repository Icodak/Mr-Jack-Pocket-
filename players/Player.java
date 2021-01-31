package players;

import java.util.ArrayList;
import java.util.List;

import items.Card;

public class Player {
	private List<Card> alibiDeck = new ArrayList<>();
	private int hourglass;
	private boolean isJack = false;
	private String name;

	// Constructor
	public Player(boolean isJack, String name) {
		setJack(isJack);
		setName(name);
	}

	public void addAlibiCard(Card card) {
		System.out.println("Card added : " + card);
		alibiDeck.add(card);
	}

	public void removeAlibiCard(Card card) {
		alibiDeck.remove(card);
	}

	// Getters and Setters
	public List<Card> getAlibiDeck() {
		return alibiDeck;
	}

	public void setAlibiDeck(List<Card> alibiDeck) {
		this.alibiDeck = alibiDeck;
	}

	public int getHourglass() {
		return hourglass;
	}

	public void setHourglass(int hourglass) {
		this.hourglass = hourglass;
	}

	public boolean isJack() {
		return isJack;
	}

	public void setJack(boolean isJack) {
		this.isJack = isJack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Console toString
	public String toString() {
		return getName();
	}

}