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
	public Card[] deck =new Card[9];
	public  TimeToken[] time =new TimeToken[8];
	public  ActionToken[] action = new  ActionToken[4];
	
	
	public void playGame(){
		gameInitialisation();
	}
	
	public void gameInitialisation() {
		Card[] liste =deckCreator();
		for (int j=0;j<9;j++) {
			System.out.println(liste[j].getCharacter()+"   "+liste[j].getHourglass());
		}
	randomIntGenerator();
	}
	
	
	

	
	
	
	
	public boolean[ ] randombooleanGenerate() {
		boolean[] liste = new boolean[4];
		Random random = new Random();
		liste[random.nextInt(4)]=true;
		return liste;
	}
	
	public 	int[] randomIntGenerator() {
		int[] liste= {0,1,2,3,4,5,6,7,8};
		Random random = new Random();
		for(int i=0;i<9;i++) {		
			int Aleatoire=random.nextInt(9);
			int swp=liste[i];
			liste[i]=liste[Aleatoire];
			liste[Aleatoire]=swp;
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
				new InvestigatorToken("      "),
				new InvestigatorToken("      "),
				new InvestigatorToken("      "),
		};
		Cell[][] arraytwo= new Cell[5][5];
		
		for (int i=0;i<5;i++) {
			for (int j=0;j<5;j++) {
				arraytwo[i][j]=new Cell(array, new Tile( randombooleanGenerate(), false, false,""));
			}
		}
		
		for (int p=1;p<4;p++) {
			for (int f=1;f<4;f++) {
				arraytwo[p][f]=new Cell(array, new Tile( randombooleanGenerate(), false, false,listeName[indice]));
				indice++;
			}
		}
		arraytwo[1][0]=new Cell(InvestigatorToken.listDetectiveConstructor(detectiveName[1], "", ""), new Tile( randombooleanGenerate(), false, false,""));
		arraytwo[1][4]=new Cell(InvestigatorToken.listDetectiveConstructor(detectiveName[0]+"  ","",""), new Tile( randombooleanGenerate(), false, false,""));
		arraytwo[4][2]=new Cell(InvestigatorToken.listDetectiveConstructor(detectiveName[2]+"  ", "",""), new Tile( randombooleanGenerate(), false, false,""));
		
		board.setBoard(arraytwo);		
		return board;
	}
	
	
	
	
	public void printBoard(Cell[][] board){
		System.out.println("");
		for(int i=0;i<5;i++) {
		for(int j=0;j<5;j++) {
			if(i==0 || i==4 ||j==0||j==4) {
				System.out.print(board[i][j].getDetectiveToString()+"\t");
			}
			else {
				System.out.print(board[i][j].getDiscrict().getCharacter()+"\t");
			}
		}
		System.out.println("");
		System.out.println("");
		}	
    }
	
	public Card[] deckCreator() {
		int[] timePoint= {0,0,1,1,1,1,1,1,2};;
		String[] liste=randomstringGenerator();
		for(int i=0;i<9;i++) {
			deck[i] = new Card(liste[i], false, timePoint[i], true);
		}
		return deck;
	}
	
	
	
	
	


}