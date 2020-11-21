

public class Card {
	private String character;
	private boolean isFlipped;
	private int hourglass;
	private boolean inDeck;
	
	
	
	
	public Card(String character, boolean isFlipped, int hourglass, boolean inDeck) {
		super();
		this.character = character;
		this.isFlipped = isFlipped;
		this.hourglass = hourglass;
		this.inDeck = inDeck;
	}
	
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public boolean isFlipped() {
		return isFlipped;
	}
	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}
	public int getHourglass() {
		return hourglass;
	}
	public void setHourglass(int hourglass) {
		this.hourglass = hourglass;
	}
	public boolean isInDeck() {
		return inDeck;
	}
	public void setInDeck(boolean inDeck) {
		this.inDeck = inDeck;
	}
	
	
	
}