package graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	public int[] cartSize={145,230};
	public int[] actionSize={140,140};
	public int[] distrcitSize= {250,250};
	public int[] inspectorSize={80,80};
	public int[] coordLayout= {4,5,6,9,10,11,14,15,16};
	public int[] coordLayoutTwo= {0,1,2,18,19,20};
	public int[] coordLayoutThree= {3,7,8,12,13,17};
	
	
	public NewJLabel[][] matrice=	{listegauche,listedroit,liste3,cardBack,listemilieuTwo,listemilieuThree,};
	public int[][] allSize= {timeSize,actionSize,distrcitSize,cartSize,inspectorSize,inspectorSize};
	public int[][] allSizeCopy= {{110,110},{140,140},{240,240},{145,230},{80,80},{80,80}};
	public String[] resolution= {"1920*1080","1680*1050","1600*1024","1280*720","1024*758","800*600"};
	public int[][] resolutionTable={{1900,1080},{1680,1050},{1600,1024},{1280,720},{1024,758},{800,600}};
	
	//CHEMIN DES IMAGES
	public String[] ActionToken = {"\\images\\alibicards\\ALIBI_CARD.png","\\images\\tokens\\TOKEN_SWAP.png","\\images\\tokens\\TOKEN_ROTATE.png","\\images\\tokens\\TOKEN_TOBBY.png","\\images\\tokens\\TOKEN_WATSON.png","\\images\\tokens\\TOKEN_SHERLOCK.png","\\images\\tokens\\TOKEN_CARD.png","\\images\\tokens\\TOKEN_JOKER.png","\\images\\tokens\\TOKEN_ROTATION.png"};
	public String[] TimeToken= {"\\images\\tokens\\TIME_DETECTIVE_1.png","\\images\\tokens\\TIME_JACK_2.png","\\images\\tokens\\TIME_DETECTIVE_3.png","\\images\\tokens\\TIME_JACK_4.png","\\images\\tokens\\TIME_DETECTIVE_5.png","\\images\\tokens\\TIME_JACK_6.png","\\images\\tokens\\TIME_DETECTIVE_7.png","\\images\\tokens\\TIME_JACK_8.png"};
	public String[] District= {"\\images\\districts\\0_INSP_LESTRADE.png","\\images\\districts\\0_JEREMY_BERT.png","\\images\\districts\\0_JOHN_PIZER.png","\\images\\districts\\0_JOHN_SMITH.png","\\images\\districts\\0_JOSEPH_LANE.png","\\images\\districts\\0_MADAME.png","\\images\\districts\\0_MISS_STEALTHY.png","\\images\\districts\\0_SGT_GOODLEY.png","\\images\\districts\\0_WILLIAM_GULL.png","\\images\\districts\\DISTRICT_CROSS_SHAPE.png","\\images\\districts\\DISTRICT_T_SHAPE.png",};
	public String[] AlibiCard= {"\\images\\alibicards\\ALIBI_INSP_LESTRADE.png","\\images\\alibicards\\ALIBI_JEREMY_BERT.png","\\images\\alibicards\\ALIBI_JOHN_PIZER.png","\\images\\alibicards\\ALIBI_JOHN_SMITH.png","\\images\\alibicards\\ALIBI_JOSEPH_LANE.png","\\images\\alibicards\\ALIBI_MADAME.png","\\images\\alibicards\\ALIBI_MISS_STEALTHY.png","\\images\\alibicards\\ALIBI_SGT_GOODLEY.png","\\images\\alibicards\\ALIBI_WILLIAM_GULL.png"};
	public String[] Detective= {"\\images\\tokens\\DETECTIVE_SHERLOCK.png","\\images\\tokens\\DETECTIVE_TOBBY.png","\\images\\tokens\\DETECTIVE_WATSON.png"};
	
}
