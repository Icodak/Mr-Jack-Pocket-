import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	Cell[][] board;

	Board(Cell[][] board) {
		this.setBoard(board);
	}

	public Cell[][] getBoard() {
		return board;
	}
	
	public void setBoard(Cell[][] board) {
		this.board = board;
	}
	
	public Cell getCell(List<Integer> coord) {
		return board[coord.get(1)][coord.get(0)];
	}
	
	public void setCell(Cell cell,List<Integer> coord) {
		board[coord.get(1)][coord.get(0)] = cell;
	}



	public void swapCells(List<Integer> coord1, List<Integer> coord2) {
		if (board[coord1.get(0)][coord1.get(1)].getCell() instanceof District
				&& board[coord2.get(0)][coord2.get(1)].getCell() instanceof District) {
			Cell cellTemp = board[coord1.get(0)][coord1.get(1)].getCell();
			setCell(board[coord2.get(0)][coord2.get(1)].getCell(), Arrays.asList(coord1.get(0),coord1.get(1)));
			setCell(cellTemp, Arrays.asList(coord2.get(0),coord2.get(1)));
		}
	}

	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		List<Integer> coords = new ArrayList<>();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				coords = Arrays.asList(x, y);

				if (board[x][y].getCell() instanceof DetectiveToken) {
					// removes token from current position
					if (((DetectiveToken) board[x][y].getCell()).getDetectiveList().contains(detectiveName)) {
						((DetectiveToken) board[x][y].getCell()).removeDetective(detectiveName);
						// finds new detectiveToken position
						for (int move = 0; move < cellCount; move++) {
							coords = slideAround(coords, Arrays.asList(board[y].length - 1, board.length - 1));
						}
						((DetectiveToken) board[coords.get(0)][coords.get(1)].getCell()).addDetective(detectiveName);
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

}
