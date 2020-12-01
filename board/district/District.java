package board.district;

import board.Cell;
import items.AlibiName;

public class District extends Cell {
	private AlibiName character = AlibiName.INSPECTOR_LESTRADE;
	private Orientation orientation = Orientation.EAST;
	private boolean[] walls;
	private DistrictType districtType = DistrictType.T_SHAPE;
	private DistrictType districtBType = DistrictType.T_SHAPE;
	private boolean isRecto = true;
	
	public DistrictType getDistrictBType() {
		return districtBType;
	}

	public void setDistrictBType(DistrictType districtBType) {
		this.districtBType = districtBType;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setWalls(boolean[] walls) {
		this.walls = walls;
	}

	public District() {
	}

	public boolean[] getWalls() {
		//gets walls depending on the orientation dans type of the district
		walls = districtType.getCellWalls();

		switch (this.orientation) {
		case EAST:
			return new boolean[] { walls[0], walls[1], walls[2], walls[3] };
		case NORTH:
			return new boolean[] { walls[3], walls[0], walls[1], walls[2] };
		case WEST:
			return new boolean[] { walls[2], walls[3], walls[0], walls[1] };
		case SOUTH:
			return new boolean[] { walls[1], walls[2], walls[3], walls[0] };
		}
		return null;

	}

	public DistrictType getDistrictType() {
		return districtType;
	}

	public void setDistrictType(DistrictType districtType) {
		this.districtType = districtType;
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

		String cellString = character.toString().substring(0,6) + "." + orientation.toString().substring(0,1);
		return cellString;

	}

}
