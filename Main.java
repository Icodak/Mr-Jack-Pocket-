import java.io.IOException;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {

		DetectiveToken a1 = new DetectiveToken();
		DetectiveToken a2 = new DetectiveToken();
		DetectiveToken a3 = new DetectiveToken();
		DetectiveToken b1 = new DetectiveToken();
		District b2 = new District(AlibiName.JOHN_PIZER, DistrictType.L_SHAPE);
		DetectiveToken b3 = new DetectiveToken();
		DetectiveToken c1 = new DetectiveToken();
		DetectiveToken c2 = new DetectiveToken();
		DetectiveToken c3 = new DetectiveToken();
		
		Cell[] line1 = new Cell[] {a1,a2,a3};
		Cell[] line2 = new Cell[] {b1,b2,b3};
		Cell[] line3 = new Cell[] {c1,c2,c3};
		
		Cell[][] board = new Cell[][] {line1,line2,line3};
		
		Board plateau = new Board(board);
		System.out.println(plateau.getBoard()[1][1].getCell());
		ActionToken action = new ActionToken(plateau);
		action.rotate(Orientation.NORTH, Arrays.asList(1,1));
		System.out.println(plateau.getBoard()[1][1].getCell());
		System.out.println(plateau.getBoard()[0][0].getCell());
		a1.addDetective(DetectiveName.SHERLOCK);
		System.out.println(plateau.getBoard()[0][0].getCell());
		
		
	}
}
