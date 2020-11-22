package fr.arsenesoulie.jackpocket;


public class District {
	private AlibiName character;
	private Orientation orientation = Orientation.EAST;
	private boolean[] walls;
	private boolean isRecto = true;
	private CellType type;
	
	
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
	
	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public District(AlibiName character,CellType type) {
		this.setCharacter(character);
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
		boolean[] cellStyle = getWalls();
		String cellString = "0    " + cellStyle[1] + "    0" + "\n" +
				            cellStyle[2] +  " " +  character.toString().substring(0,3) +  " "  + cellStyle[0] + "\n" +
				            "0    " + cellStyle[3] + "    0";
		return cellString;
		
	}

	
}
