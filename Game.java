
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
	public Board board= new Board(new Cell[5][5]);
	public boolean isJackturn=false;
	public int turnCount;
	public InvestigatorToken[] investigators= new InvestigatorToken[3];
	public Card[] InvestigatorCards= new Card[8];
	public Card[] jackCard;
	public Card[] deck =new Card[8];
	public  TimeToken[] time =new TimeToken[8];
	public  ActionToken[] action = new  ActionToken[4];
	
}
