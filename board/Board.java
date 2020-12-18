package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.detective.DetectiveName;
import board.detective.DetectiveToken;
import board.district.District;
import board.district.Orientation;
import items.AlibiName;
import program.JackPocketGame;

public class Board {

	Cell[][] cellBoard;

	//Constructor
	public Board(Cell[][] cellBoard) {
		this.cellBoard = cellBoard;
	}

	public void swapCells(List<Integer> coord1, List<Integer> coord2) {
		// Swaps two district cells at the given coordinates
		Cell cellTemp = getCell(coord1);
		setCell(getCell(coord2), coord1);
		setCell(cellTemp, coord2);
	}

	public void rotate(Orientation orientation, List<Integer> coords) {
		// Rotates the district cell to a new orientation at the given coordinates
		Cell cell = getCell(coords);
		if ((cell instanceof District)) {
			JackPocketGame.setRotatedDistrict(getCell(coords));
			((District) getCell(coords)).setOrientation(orientation);
		}
		System.out.println("Cannot rotate already rotated cell in same turn.");
	}

	public List<AlibiName> visibleCharacters() {
		// Returns an array of alibinames of onboard characters from the detective view
		// while no wall is faced a detective can see every character on its row &
		// columns
		ArrayList<AlibiName> characterList = new ArrayList<>();
		boolean wallNext;

		// Check for detectives
		for (int h = 0; h < cellBoard.length; h++) {
			for (int l = 0; l < cellBoard[0].length; l++) {
				List<Integer> coords = Arrays.asList(h, l);
				if ((cellBoard[h][l] instanceof DetectiveToken)
						&& (!((DetectiveToken) cellBoard[h][l]).getDetectiveList().isEmpty())) {

					// Foreach detective, do an analysis
					// Horizontal analysis
					// left to right
					if (coords.get(1) == 0) {
						wallNext = ((District) getCell(Arrays.asList(h, 1))).getWalls()[2];
						for (int i = 1; i < cellBoard[h].length - 1; i++) {
							if (!wallNext) {
								if (((District) getCell(Arrays.asList(h, i))).isRecto()) {
									characterList.add(((District) getCell(Arrays.asList(h, i))).getCharacter());
								}
							} else {
								break;
							}
							if (getCell(Arrays.asList(h, i + 1)) instanceof District) {
								wallNext = (((District) getCell(Arrays.asList(h, i))).getWalls()[0]
										|| ((District) getCell(Arrays.asList(h, i + 1))).getWalls()[2]);
							}
						}
					}

					// right to left
					if (coords.get(1) == cellBoard[h].length - 1) {
						wallNext = ((District) getCell(Arrays.asList(h, cellBoard[h].length - 2))).getWalls()[0];
						for (int i = 1; i < cellBoard[h].length - 1; i++) {
							if (!wallNext) {
								if (((District) getCell(Arrays.asList(h, cellBoard[h].length - i - 1))).isRecto()) {
									characterList
											.add(((District) getCell(Arrays.asList(h, cellBoard[h].length - i - 1)))
													.getCharacter());
								}
							} else {
								break;
							}
							if (getCell(Arrays.asList(h, cellBoard[h].length - i - 2)) instanceof District) {
								wallNext = (((District) getCell(Arrays.asList(h, cellBoard[h].length - i - 1)))
										.getWalls()[2]
										|| ((District) getCell(Arrays.asList(h, cellBoard[h].length - i - 2)))
												.getWalls()[0]);
							}
						}
					}

					// up to down
					if (coords.get(0) == 0) {
						wallNext = ((District) getCell(Arrays.asList(1, l))).getWalls()[1];
						for (int i = 1; i < cellBoard.length - 1; i++) {
							if (!wallNext) {
								if (((District) getCell(Arrays.asList(i, l))).isRecto()) {
									characterList.add(((District) getCell(Arrays.asList(i, l))).getCharacter());
								}
							} else {
								break;
							}
							if (getCell(Arrays.asList(i + 1, l)) instanceof District) {
								wallNext = (((District) getCell(Arrays.asList(i, l))).getWalls()[3]
										|| ((District) getCell(Arrays.asList(i + 1, l))).getWalls()[1]);
							}
						}
					}

					// down to up
					if (coords.get(0) == cellBoard.length - 1) {
						wallNext = ((District) getCell(Arrays.asList(cellBoard.length - 2, l))).getWalls()[3];
						for (int i = 1; i < cellBoard.length - 1; i++) {
							if (!wallNext) {
								if (((District) getCell(Arrays.asList(cellBoard.length - i - 1, l))).isRecto()) {
									characterList.add(((District) getCell(Arrays.asList(cellBoard.length - i - 1, l)))
											.getCharacter());
								}
							} else {
								break;
							}
							if (getCell(Arrays.asList(cellBoard.length - i - 2, l)) instanceof District) {
								wallNext = (((District) getCell(Arrays.asList(cellBoard.length - i - 1, l)))
										.getWalls()[1]
										|| ((District) getCell(Arrays.asList(cellBoard.length - i - 2, l)))
												.getWalls()[3]);
							}
						}
					}
				}
			}
		}
		return characterList;
	}

	public int flipDistrict(boolean isJackVisible, List<AlibiName> visibleList) {
		// Flips Districts depending on the visibility of Jack:
		// If Jack is visible, flip the non visible districts
		// Else flip the visible Districts
		int districtsLeft = 0;
		boolean isVisibleDistrict = false;
		for (int h = 1; h < cellBoard.length - 1; h++) {
			for (int l = 1; l < cellBoard[h].length - 1; l++) {
				for (AlibiName visibleAlibi : visibleList) {
					if (visibleAlibi == ((District) cellBoard[h][l]).getCharacter()) {

						isVisibleDistrict = true;
					}
				}
				if (isJackVisible && !isVisibleDistrict) {
					((District) cellBoard[h][l]).setRecto(false);
				}
				if (!isJackVisible && isVisibleDistrict) {
					((District) cellBoard[h][l]).setRecto(false);
				}
				isVisibleDistrict = false;
				if (((District) cellBoard[h][l]).isRecto()) {
					districtsLeft++;
				}
			}
		}
		return districtsLeft;
	}

	public void flipDistrict(AlibiName alibiName) {
		for (int h = 1; h < cellBoard.length - 1; h++) {
			for (int l = 1; l < cellBoard[h].length - 1; l++) {
				if (alibiName == ((District) cellBoard[h][l]).getCharacter()) {
					((District) cellBoard[h][l]).setRecto(false);
				}
			}
		}
	}

	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		// Moves a given detective of a number of cells
		boolean found = false;
		for (int move = 0; move < cellCount; move++) {
			found = false;
			for (int h = 0; h < cellBoard.length; h++) {
				for (int l = 0; l < cellBoard[h].length; l++) {
					List<Integer> coords = Arrays.asList(h, l);
					if ((cellBoard[h][l] instanceof DetectiveToken && !found)
							&& (((DetectiveToken) cellBoard[h][l]).getDetectiveList().contains(detectiveName))) {
						// removes token from current position
						found = true;
						((DetectiveToken) cellBoard[h][l]).removeDetective(detectiveName);
						// finds new detectiveToken position
						coords = slideAround(coords, Arrays.asList(cellBoard.length - 1, cellBoard[h].length - 1));
						((DetectiveToken) getCell(coords)).addDetective(detectiveName);
					}
				}
			}
		}
	}

	public static List<Integer> slideAround(List<Integer> coords, List<Integer> maxCoord) {
		// Method used in moveDetectiveToken to determine how to move the detective
		// depending on its current position
		// left to right
		if (coords.get(0).equals(maxCoord.get(0)) && coords.get(1) < maxCoord.get(1)) {
			coords = Arrays.asList(coords.get(0), coords.get(1) + 1);
		}
		// right to left
		else if (coords.get(0) == 0 && coords.get(1) > 0) {
			coords = Arrays.asList(coords.get(0), coords.get(1) - 1);
		}
		// up to down
		else if (coords.get(1) == 0 && coords.get(0) < maxCoord.get(0)) {
			coords = Arrays.asList(coords.get(0) + 1, coords.get(1));
		}
		// down to up
		else if (coords.get(1).equals(maxCoord.get(1)) && coords.get(0) > 0) {
			coords = Arrays.asList(coords.get(0) - 1, coords.get(1));
		}

		// if corner do it again
		if ((Math.floorMod(coords.get(0), maxCoord.get(0)) == 0)
				&& (Math.floorMod(coords.get(1), maxCoord.get(1)) == 0)) {
			coords = slideAround(coords, maxCoord);
		}
		return coords;
	}

	// Getters and Setters
	public Cell[][] getBoard() {
		return cellBoard;
	}

	public void setBoard(Cell[][] cellBoard) {
		this.cellBoard = cellBoard;
	}

	public Cell getCell(List<Integer> coord) {
		return cellBoard[coord.get(0)][coord.get(1)];
	}

	public void setCell(Cell cell, List<Integer> coord) {
		cellBoard[coord.get(0)][coord.get(1)] = cell;
	}

	// Console toString
	public String toString() {
		StringBuilder sBoard = new StringBuilder();
		for (int y = 0; y < cellBoard.length; y++) {
			for (int x = 0; x < cellBoard[y].length; x++) {
				sBoard.append(cellBoard[y][x].toString() + ",");
			}
			sBoard.append("\n");
		}
		return sBoard.toString();

	}

}
