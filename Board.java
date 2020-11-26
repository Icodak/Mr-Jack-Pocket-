import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.SetBinding;

public class Board {

	Cell[][] cellBoard;

	Board(Cell[][] cellBoard) {
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
	
	public void setCell(Cell cell,List<Integer> coord) {
		cellBoard[coord.get(1)][coord.get(0)] = cell;
	}


	public void swapCells(List<Integer> coord1, List<Integer> coord2) {
		if (cellBoard[coord1.get(0)][coord1.get(1)] instanceof District
				&& cellBoard[coord2.get(0)][coord2.get(1)] instanceof District) {
			Cell cellTemp = cellBoard[coord1.get(0)][coord1.get(1)];
			setCell(cellBoard[coord2.get(0)][coord2.get(1)], Arrays.asList(coord1.get(0),coord1.get(1)));
			setCell(cellTemp, Arrays.asList(coord2.get(0),coord2.get(1)));
		}
	}
	
	public void rotate(Orientation orientation, List<Integer> coords) {
		Cell cell = getCell(coords);
		if (cell instanceof District) {
			((District) getCell(coords)).setOrientation(orientation);
		}

	}
	

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
						}
						((DetectiveToken) cellBoard[coords.get(0)][coords.get(1)]).addDetective(detectiveName);
					}
				}
			}
		}

	}

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
