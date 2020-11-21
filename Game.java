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
	
	
	public void playGame(){
		gameInitialisation();
	}
	
	public void gameInitialisation() {
		tileInitialisation();
		
	}
	
	
	

	
	
	
	
	public boolean[ ] randombooleanGenerate() {
		boolean[] liste = new boolean[4];
		Random random = new Random();
		liste[random.nextInt(4)]=true;
		return liste;
	}
	
	public 	int[] randomIntGenerator() {
		int[] liste= {0,1,2,3,4,5,6,7,8,9};
		Random random = new Random();
		for(int i=0;i<9;i++) {		
			int Aleatoire=random.nextInt(9);
			int Aleatoiretwo=random.nextInt(9);
			int swp=liste[Aleatoire];
			liste[Aleatoire]=liste[Aleatoiretwo];
			liste[Aleatoiretwo]=swp;			
		}		
		return liste;
	}
	
	public String[] randomstringGenerator() {
		int [] Aleatoire=randomIntGenerator() ;
		int [] Aleatoiretwo=randomIntGenerator() ;
		String[] listetwo= new String[9];
		String[] liste=AlibiName.getName();
		for(int i=0;i<9;i++) {
			listetwo[Aleatoire[i]]=liste[Aleatoiretwo[i]];
		}
		return listetwo;
	}
	
	
	
	
	
	
	public Board tileInitialisation() {
		String[] detectiveName=DetectiveName.getName();
		String[] listeName=randomstringGenerator();
		int indice=0;
		InvestigatorToken[] array= {
				new InvestigatorToken(""),
				new InvestigatorToken(""),
				new InvestigatorToken(""),
		};
		Cell[][] arraytwo= new Cell[5][5];
		
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				arraytwo[i][j]=new Cell(array, new Tile( randombooleanGenerate(), false, false,""));
				indice++;
			}
		}
		
		for (int i=1;i<3;i++) {
			for (int j=1;j<3;j++) {
				arraytwo[i][j]=new Cell(array, new Tile( randombooleanGenerate(), false, false,listeName[indice]));
				indice++;
			}
		}
		
		board.setBoard(arraytwo);		
		return board;
	}
	
	
	public void printBoard(Cell[][] board){
		for(int i=0;i<5;i++) {
		for(int j=0;j<5;j++) {
			System.out.print(board[i][j].getDetectiveToString()+""+board[i][j].getDiscrict());
		}
		System.out.println("");
		}
	}
	
	
	
}