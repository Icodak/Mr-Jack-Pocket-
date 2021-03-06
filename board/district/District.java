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
	private boolean isRotate= false;

	public boolean isRotate() {
		return isRotate;
	}

	public void setRotate(boolean isRotate) {
		this.isRotate = isRotate;
	}

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

	public boolean[] getWalls() {
		// Gets walls depending on the orientation type of the district
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
		return new boolean[0];

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

	// Console toString
	public String toString() {
		String cellString;
		if (isRecto) {
			cellString = character.toString().substring(0, 6);
		} else {
			cellString = "000000";
		}
		cellString += "." + orientation.toString().substring(0, 1);
		return cellString;

	}

}
