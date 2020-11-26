package items;
import java.util.List;

import board.Board;
import board.detective.DetectiveName;
import board.district.Orientation;
import players.Player;
import program.JackPocketGame;

public class ActionToken {

	Board board;
	JackPocketGame jackPocketGame;

	ActionToken(Board board, JackPocketGame jackPocketGame) {
		this.board = board;
		this.jackPocketGame = jackPocketGame;
	}

	public void moveDetectiveToken(DetectiveName detectiveName, int cellCount) {
		board.moveDetectiveToken(detectiveName, cellCount);
	}

	public void drawCard(Player player) {
		if (jackPocketGame.getCardDeck().size() > 0) {
			player.addAlibiCard(jackPocketGame.getCardDeck().get(0));
			jackPocketGame.getCardDeck().remove(0);
		}

	}

	public void swap(List<Integer> coord1, List<Integer> coord2) {
		board.swapCells(coord1, coord2);
	}

	public void rotate(Orientation orientation, List<Integer> coords) {
		board.rotate(orientation, coords);
	}

}
