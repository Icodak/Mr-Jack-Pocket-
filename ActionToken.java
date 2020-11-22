import java.util.List;

public class ActionToken {

	Board board;
	
	ActionToken(Board board){
		this.board = board;
	}
	
	public void swap(List<Integer> coord1,List<Integer> coord2) {
		Cell cell2 = board.getCell(coord1).getCell();
		Cell cell1 = board.getCell(coord2).getCell();
		if (cell1.getCell() instanceof District && cell2.getCell() instanceof District) {
			board.swapCells(coord1, coord2);
		}
	}
	
	public void rotate(Orientation orientation, List<Integer> coords) {
		Cell cell = board.getCell(coords);
		if (cell.getCell() instanceof District) {
			((District) board.getCell(coords).getCell()).setOrientation(orientation);
			//board.getBoard()[coords.get(1)][coords.get(0)].getCell()).setOrientation(orientation);
		}

	}
	
	
	
}
