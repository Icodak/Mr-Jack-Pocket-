import java.util.List;

public class ActionToken {

	Board board;
	
	ActionToken(Board board){
		this.board = board;
	}
	
	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		board.moveDetectiveToken(detectiveName, cellCount);
	}
	
	public void drawCard() {
		
	}
	
	public void swap(List<Integer> coord1,List<Integer> coord2) {
			board.swapCells(coord1, coord2);
	}
	
	public void rotate(Orientation orientation, List<Integer> coords) {
		board.rotate(orientation, coords);
	}
	
	
	
}
