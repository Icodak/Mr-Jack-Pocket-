import java.util.ArrayList;
import java.util.Collections;

public class Game {

	ArrayList<Card> cardDeck;
	Board board;

	Game(Board board, ArrayList<Card> cardDeck) {
		this.board = board;
		this.cardDeck = cardDeck;

	}

	public ArrayList<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(ArrayList<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public static ArrayList<Card> generateCardDeck() {
		ArrayList<Card> cardArray = new ArrayList<Card>();

		cardArray.add(new Card(AlibiName.INSPECTOR_LESTRADE, 0));
		cardArray.add(new Card(AlibiName.JEREMY_BERT, 1));
		cardArray.add(new Card(AlibiName.JOHN_PIZER, 1));
		cardArray.add(new Card(AlibiName.JOHN_SMITH, 1));
		cardArray.add(new Card(AlibiName.JOSEPH_LANE, 1));
		cardArray.add(new Card(AlibiName.MADAME, 2));
		cardArray.add(new Card(AlibiName.MISS_STENTHY, 1));
		cardArray.add(new Card(AlibiName.SERGENT_GOODLEY, 0));
		cardArray.add(new Card(AlibiName.WILLIAM_GULL, 1));
		Collections.shuffle(cardArray);

		return cardArray;
	}

	public static Board generateGameBoard() {

		DetectiveToken a1 = new DetectiveToken();
		DetectiveToken a2 = new DetectiveToken();
		DetectiveToken a3 = new DetectiveToken();
		DetectiveToken a4 = new DetectiveToken();
		DetectiveToken a5 = new DetectiveToken();

		DetectiveToken b1 = new DetectiveToken();
		District b2 = new District(AlibiName.INSPECTOR_LESTRADE, DistrictType.L_SHAPE);
		District b3 = new District(AlibiName.JEREMY_BERT, DistrictType.T_SHAPE);
		District b4 = new District(AlibiName.JOHN_PIZER, DistrictType.T_SHAPE);
		DetectiveToken b5 = new DetectiveToken();

		DetectiveToken c1 = new DetectiveToken();
		District c2 = new District(AlibiName.JOHN_SMITH, DistrictType.T_SHAPE);
		District c3 = new District(AlibiName.JOSEPH_LANE, DistrictType.T_SHAPE);
		District c4 = new District(AlibiName.MADAME, DistrictType.T_SHAPE);
		DetectiveToken c5 = new DetectiveToken();

		DetectiveToken d1 = new DetectiveToken();
		District d2 = new District(AlibiName.MISS_STENTHY, DistrictType.T_SHAPE);
		District d3 = new District(AlibiName.SERGENT_GOODLEY, DistrictType.T_SHAPE);
		District d4 = new District(AlibiName.WILLIAM_GULL, DistrictType.T_SHAPE);
		DetectiveToken d5 = new DetectiveToken();

		DetectiveToken e1 = new DetectiveToken();
		DetectiveToken e2 = new DetectiveToken();
		DetectiveToken e3 = new DetectiveToken();
		DetectiveToken e4 = new DetectiveToken();
		DetectiveToken e5 = new DetectiveToken();

		b1.addDetective(DetectiveName.SHERLOCK);
		b5.addDetective(DetectiveName.WATSON);
		e3.addDetective(DetectiveName.TOBBY);

		d3.setOrientation(Orientation.EAST);
		b2.setOrientation(Orientation.SOUTH);
		b4.setOrientation(Orientation.NORTH);

		Cell[] line1 = new Cell[] { a1, a2, a3, a4, a5 };
		Cell[] line2 = new Cell[] { b1, b2, b3, b4, b5 };
		Cell[] line3 = new Cell[] { c1, c2, c3, c4, c5 };
		Cell[] line4 = new Cell[] { d1, d2, d3, d4, d5 };
		Cell[] line5 = new Cell[] { e1, e2, e3, e4, e5 };

		Cell[][] cellBoard = new Cell[][] { line1, line2, line3, line4, line5 };

		Board plateau = new Board(cellBoard);
		return plateau;
	}

}
