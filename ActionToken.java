import java.util.List;

public class ActionToken {

	Board board;
	Game game;
	
	ActionToken(Board board, Game game){
		this.board = board;
		this.game = game;
	}
	
	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		board.moveDetectiveToken(detectiveName, cellCount);
	}
	
	public void drawCard(Player player) {
		if (game.getCardDeck().size()>0) {
		player.addAlibiCard(game.getCardDeck().get(0));
		game.getCardDeck().remove(0);
		}
		
	}
	
	public void swap(List<Integer> coord1,List<Integer> coord2) {
			board.swapCells(coord1, coord2);
	}
	
	public void rotate(Orientation orientation, List<Integer> coords) {
		board.rotate(orientation, coords);
	}
	
	
	
}
