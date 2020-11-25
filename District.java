

public class District extends Cell{
	private AlibiName character;
	private Orientation orientation = Orientation.EAST;
	private boolean[] walls;
	public Orientation getOrientation() {
		return orientation;
	}


	public void setWalls(boolean[] walls) {
		this.walls = walls;
	}

	private boolean isRecto = true;
	private DistrictType type;
	
	public District(AlibiName character,DistrictType type) {
		this.setCharacter(character);
		this.type = type;		
	}
	
	
	public boolean[] getWalls() {
		
		walls = type.getCellWalls();
		
		switch (this.orientation) {
		case EAST:
			return new boolean[]{walls[0],walls[1],walls[2],walls[3]};
		case NORTH:
			return new boolean[]{walls[3],walls[0],walls[1],walls[2]};
		case WEST:
			return new boolean[]{walls[2],walls[3],walls[0],walls[1]};
		case SOUTH:
			return new boolean[]{walls[1],walls[2],walls[3],walls[0]};
		}
		return null;
				
	}
	
	public DistrictType getType() {
		return type;
	}

	public void setType(DistrictType type) {
		this.type = type;
	}


	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean isRecto() {
		return isRecto;
	}

	public void setRecto(boolean isRecto) {
		this.isRecto = isRecto;
	}

	public AlibiName getCharacter() {
		return character;
	}

	public void setCharacter(AlibiName character) {
		this.character = character;
	}
	
	public String toString() {

		String cellString = "Character : " + character.toString() +  ", DistricyType : "  + type.toString();
		return cellString;
		
	}

	
}
