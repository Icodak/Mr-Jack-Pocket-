package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.detective.DetectiveName;
import board.detective.DetectiveToken;
import board.district.District;
import board.district.Orientation;
import items.AlibiName;

public class Board {

	Cell[][] cellBoard;

	public Board(Cell[][] cellBoard) {
		this.cellBoard = cellBoard;
	}

	public Cell[][] getBoard() {
		return cellBoard;
	}

	public void setBoard(Cell[][] cellBoard) {
		this.cellBoard = cellBoard;
	}

	public Cell getCell(List<Integer> coord) {
		return cellBoard[coord.get(1)][coord.get(0)];
	}

	public void setCell(Cell cell, List<Integer> coord) {
		cellBoard[coord.get(1)][coord.get(0)] = cell;
	}

	//Swaps two district cells at the given coordinates
	public void swapCells(List<Integer> coord1, List<Integer> coord2) {
		if (cellBoard[coord1.get(0)][coord1.get(1)] instanceof District
				&& cellBoard[coord2.get(0)][coord2.get(1)] instanceof District) {
			Cell cellTemp = cellBoard[coord1.get(0)][coord1.get(1)];
			setCell(cellBoard[coord2.get(0)][coord2.get(1)], Arrays.asList(coord1.get(0), coord1.get(1)));
			setCell(cellTemp, Arrays.asList(coord2.get(0), coord2.get(1)));
		}
	}

	//Rotates the district cell to a new orientation at the given coordinates
	public void rotate(Orientation orientation, List<Integer> coords) {
		Cell cell = getCell(coords);
		if (cell instanceof District) {
			((District) getCell(coords)).setOrientation(orientation);
		}

	}

	//Returns an array of alibinames of onboard characters from the detective view
	//while no wall is faced a detective can see every character on its row & column
	public ArrayList<AlibiName> visibleCharacters() {
		ArrayList<AlibiName> characterList = new ArrayList<>();
		boolean wallNext;
		int xsize = cellBoard[0].length;
		int ysize = cellBoard.length;

		//Check for detectives
		for (int y = 0; y < ysize; y++) {
			for (int x = 0; x < xsize; x++) {
				List<Integer> coords = new ArrayList<>();
				coords = Arrays.asList(x, y);
				if (cellBoard[x][y] instanceof DetectiveToken) {
					
					//Foreach detective, do an analysis
					if (((DetectiveToken) cellBoard[x][y]).getDetectiveList().size() > 0) {

						// Horizontal analysis
						// left to right
						if (coords.get(0) == 0) {
							wallNext = ((District) getCell(Arrays.asList(y, 1))).getWalls()[2];
							for (int i = 1; i < xsize - 1; i++) {
								if (!wallNext) {
									characterList.add(((District) getCell(Arrays.asList(y, i))).getCharacter());
									if (getCell(Arrays.asList(i + 1, y)) instanceof District) {
										wallNext = (((District) getCell(Arrays.asList(y, i))).getWalls()[0]
												|| ((District) getCell(Arrays.asList(y, i + 1))).getWalls()[2]);
									}
								}
							}
						}

						// right tp left
						if (coords.get(0) == xsize - 1) {
							wallNext = ((District) getCell(Arrays.asList(y, xsize - 2))).getWalls()[0];
							for (int i = 1; i < xsize - 1; i++) {
								if (!wallNext) {
									characterList
											.add(((District) getCell(Arrays.asList(y, xsize - i - 1))).getCharacter());
									if (getCell(Arrays.asList(y, xsize - i - 2)) instanceof District) {
										wallNext = (((District) getCell(Arrays.asList(y, xsize - i - 1))).getWalls()[2]
												|| ((District) getCell(Arrays.asList(y, xsize - i - 2))).getWalls()[0]);
									}
								}
							}
						}

						// Vertical analysis
						// top bottom
						if (coords.get(1) == 0) {
							wallNext = ((District) getCell(Arrays.asList(1, x))).getWalls()[1];
							for (int i = 1; i < ysize - 1; i++) {
								if (!wallNext) {
									characterList.add(((District) getCell(Arrays.asList(i, x))).getCharacter());
									if (getCell(Arrays.asList(i + 1, x)) instanceof District) {
										wallNext = (((District) getCell(Arrays.asList(i, x))).getWalls()[3]
												|| ((District) getCell(Arrays.asList(i + 1, x))).getWalls()[1]);
									}
								}
							}
						}

						// bottom top
						if (coords.get(1) == ysize - 1) {
							wallNext = ((District) getCell(Arrays.asList(ysize - 2, x))).getWalls()[3];
							for (int i = 1; i < ysize - 1; i++) {
								if (!wallNext) {
									characterList
											.add(((District) getCell(Arrays.asList(ysize - i - 1, x))).getCharacter());
									if (getCell(Arrays.asList(ysize - i - 2, x)) instanceof District) {
										wallNext = (((District) getCell(Arrays.asList(ysize - i - 1, x))).getWalls()[1]
												|| ((District) getCell(Arrays.asList(ysize - i - 2, x))).getWalls()[3]);
									}
								}
							}
						}

					}
				}
			}
		}
		return characterList;
	}

	//Moves a given detective of a number of cells
	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		List<Integer> coords = new ArrayList<>();
		for (int y = 0; y < cellBoard.length; y++) {
			for (int x = 0; x < cellBoard[y].length; x++) {
				coords = Arrays.asList(x, y);

				if (cellBoard[x][y] instanceof DetectiveToken) {
					// removes token from current position
					if (((DetectiveToken) cellBoard[x][y]).getDetectiveList().contains(detectiveName)) {
						((DetectiveToken) cellBoard[x][y]).removeDetective(detectiveName);
						// finds new detectiveToken position
						for (int move = 0; move < cellCount; move++) {
							coords = slideAround(coords, Arrays.asList(cellBoard[y].length - 1, cellBoard.length - 1));
							// if diagonal move to next space
							if (coords.get(0) == coords.get(1)) {
								coords = slideAround(coords,
										Arrays.asList(cellBoard[y].length - 1, cellBoard.length - 1));
							}
						}
						((DetectiveToken) cellBoard[coords.get(0)][coords.get(1)]).addDetective(detectiveName);
					}
				}
			}
		}

	}

	//Method used in moveDetectiveToken to determine how to move the detective depending on its current position
	public static List<Integer> slideAround(List<Integer> coords, List<Integer> maxCoord) {
		if (coords.get(1) == 0 && coords.get(0) > 0) {
			coords.set(0, coords.get(0) - 1);
		} else if (coords.get(0) == 0 && coords.get(1) < maxCoord.get(1)) {
			coords.set(1, coords.get(1) + 1);
		} else if (coords.get(1) == maxCoord.get(1) && coords.get(0) < maxCoord.get(0)) {
			coords.set(0, coords.get(0) + 1);
		} else if (coords.get(0) == maxCoord.get(0) && coords.get(1) > 0) {
			coords.set(1, coords.get(1) - 1);
		}

		return coords;
	}

	public String toString() {
		String sBoard = "";
		for (int y = 0; y < cellBoard.length; y++) {
			for (int x = 0; x < cellBoard[y].length; x++) {
				sBoard += cellBoard[x][y].toString() + ",";
			}
		}
		return sBoard;

	}

}
