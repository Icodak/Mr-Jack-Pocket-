import java.util.ArrayList;

public class JackPocketGame {
		private Board board;
		private ArrayList<Card> cardDeck;
		
		public Board getBoard() {
			return board;
		}
		
		public void setBoard(Cell[][] board2) {
			this.board = new Board(board2);
		}
		public ArrayList<Card> getCardDeck() {
			return cardDeck;
		}
		public void setCardDeck(ArrayList<Card> cardDeck) {
			this.cardDeck = cardDeck;
		}
}