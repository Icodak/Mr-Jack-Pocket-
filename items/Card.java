package items;

public class Card {
	private AlibiName character;
	private int hourglass;

	// Getters and Setters
	public AlibiName getCharacter() {
		return character;
	}

	public void setCharacter(AlibiName character) {
		this.character = character;
	}

	public int getHourglass() {
		return hourglass;
	}

	public void setHourglass(int hourglass) {
		this.hourglass = hourglass;
	}

	// Console toString
	public String toString() {
		return character.name();
	}

}