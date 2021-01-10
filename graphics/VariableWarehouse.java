  
package graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.detective.DetectiveName;

public class VariableWarehouse {
	public JFrame frame;
	public NewJLabel[] liste3=new NewJLabel[9];
	public JPanel[] liste2=new JPanel[6];
	public JPanel[] liste6=new JPanel[6];
	public JPanel[] liste=new JPanel[21];
	public NewJLabel[] listemilieuTwo=new NewJLabel[18];
	public NewJLabel[] listemilieuThree= new NewJLabel[18];
	public NewJLabel[] listegauche=new NewJLabel[8];
	public NewJLabel[] listedroit=new NewJLabel[4];
	public NewJLabel[] cardBack=new NewJLabel[1];
	public int[] timeSize= {110,110};
	public int[] cartSize={145,180};
	public int[] actionSize={120,120};
	public int[] distrcitSize= {250,250};
	public int[] inspectorSize={80,80};
	public int[] caracterSize={100,100};
	public int[] coordLayout= {4,5,6,9,10,11,14,15,16};
	public int[] coordLayoutTwo= {0,1,2,18,19,20};
	public int[] coordLayoutThree= {3,8,13,7,12,17};
	public JLabel information=new JLabel();
	public boolean MOVE_DETECTIVE=false;
	public boolean SWAP_DISTRICT=false;
	public boolean ROTATE_DISTRICT=false;
	public boolean moveJoker=false;
	public int compteur_District=0;
	public int[][] swapPosition = new int[2][2]; 
	public int[] currentRotable;
	public boolean end=false;
	public JButton valider;
	public DetectiveName   detectiveToMove;
	public int[] coordOne= new int [2];
	public int[] coordTwo= new int [2];
	
	public NewJLabel[][] matrice=	{listegauche,listedroit,liste3,cardBack,listemilieuTwo,listemilieuThree,};
	public int[][] allSize= {timeSize,actionSize,distrcitSize,cartSize,inspectorSize,inspectorSize,caracterSize};
	public int[][] allSizeCopy= {{110,110},{120,120},{250,250},{145,180},{80,80},{80,80},{100,100}};
	public String[] resolution= {"1920*1080","1680*1050","1600*1024","1280*720","1024*758","800*600"};
	public int[][] resolutionTable={{1900,1080},{1680,1050},{1600,1024},{1280,720},{1024,758},{800,600}};
	
	//images path
	public String[] ActionToken = {"\\resources\\images\\alibicards\\ALIBI_CARD.png","\\resources\\images\\tokens\\TOKEN_SWAP.png","\\resources\\images\\tokens\\TOKEN_ROTATE.png","\\resources\\images\\tokens\\TOKEN_TOBBY.png","\\resources\\images\\tokens\\TOKEN_WATSON.png","\\resources\\images\\tokens\\TOKEN_SHERLOCK.png","\\resources\\images\\tokens\\TOKEN_CARD.png","\\resources\\images\\tokens\\TOKEN_JOKER.png","\\resources\\images\\tokens\\TOKEN_ROTATION.png"};
	public String[] TimeToken= {"\\resources\\images\\tokens\\TIME_DETECTIVE_1.png","\\resources\\images\\tokens\\TIME_JACK_2.png","\\resources\\images\\tokens\\TIME_DETECTIVE_3.png","\\resources\\images\\tokens\\TIME_JACK_4.png","\\resources\\images\\tokens\\TIME_DETECTIVE_5.png","\\resources\\images\\tokens\\TIME_JACK_6.png","\\resources\\images\\tokens\\TIME_DETECTIVE_7.png","\\resources\\images\\tokens\\TIME_JACK_8.png"};
	public String[] District= {"\\resources\\images\\districts\\common-verso-3.png","\\resources\\images\\districts\\common-verso-2.png","\\resources\\images\\districts\\DISTRICT_CROSS_SHAPE.png","\\resources\\images\\districts\\0_INSP_LESTRADE.png","\\resources\\images\\districts\\0_JEREMY_BERT.png","\\resources\\images\\districts\\0_JOHN_PIZER.png","\\resources\\images\\districts\\0_JOHN_SMITH.png","\\images\\districts\\0_JOSEPH_LANE.png","\\resources\\images\\districts\\0_MADAME.png","\\resources\\images\\districts\\0_MISS_STEALTHY.png","\\resources\\images\\districts\\0_SGT_GOODLEY.png","\\resources\\images\\districts\\0_WILLIAM_GULL.png","\\resources\\images\\districts\\DISTRICT_CROSS_SHAPE.png","\\resources\\images\\districts\\DISTRICT_T_SHAPE.png",};
	public String[] AlibiCard= {"\\resources\\images\\alibicards\\ALIBI_INSP_LESTRADE.png","\\resources\\images\\alibicards\\ALIBI_JEREMY_BERT.png","\\resources\\images\\alibicards\\ALIBI_JOHN_PIZER.png","\\resources\\images\\alibicards\\ALIBI_JOHN_SMITH.png","\\resources\\images\\alibicards\\ALIBI_JOSEPH_LANE.png","\\resources\\images\\alibicards\\ALIBI_MADAME.png","\\resources\\images\\alibicards\\ALIBI_MISS_STEALTHY.png","\\resources\\images\\alibicards\\ALIBI_SGT_GOODLEY.png","\\resources\\images\\alibicards\\ALIBI_WILLIAM_GULL.png"};
	public String[] Detective= {"\\resources\\images\\tokens\\DETECTIVE_SHERLOCK.png","\\resources\\images\\tokens\\DETECTIVE_TOBBY.png","\\resources\\images\\tokens\\DETECTIVE_WATSON.png"};
	public String[] caracter= {"\\resources\\images\\caracter\\MADAME.png","\\resources\\images\\caracter\\INSPECTOR_LESTRADE.png","\\resources\\images\\caracter\\JEREMY_BERT.png","\\resources\\images\\caracter\\JOHN_PIZER.png","\\resources\\images\\caracter\\JOSEPH_LANE.png","\\resources\\images\\caracter\\JOHN_SMITH.png","\\resources\\images\\caracter\\MISS_STENTHY.png","\\resources\\images\\caracter\\SERGENT_GOODLEY.png","\\resources\\images\\caracter\\WILLIAM_GULL.png"};
}
