

public class Card {
	private AlibiName character;
	private int hourglass;

	
	public Card(AlibiName character, int hourglass) {
		this.character = character;
		this.hourglass = hourglass;
	}
	
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
	
	public String toString() {
		return character.name();
		}
	
	
}