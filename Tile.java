
public class Tile {
	private boolean[] roads= new boolean[4];
	private boolean isFlipped;
	private boolean isRotated;
	private String Character;
	
	
	public Tile(boolean[] roads, boolean isFlipped, boolean isRotated, String character) {
		super();
		this.roads = roads;
		this.isFlipped = isFlipped;
		this.isRotated = isRotated;
		Character = character;
	}
	
	public boolean[] getRoads() {
		return roads;
	}
	public void setRoads(boolean[] roads) {
		this.roads = roads;
	}
	public boolean isFlipped() {
		return isFlipped;
	}
	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}
	public boolean isRotated() {
		return isRotated;
	}
	public void setRotated(boolean isRotated) {
		this.isRotated = isRotated;
	}
	public String getCharacter() {
		return Character;
	}
	public void setCharacter(String character) {
		Character = character;
	}
	
	
}