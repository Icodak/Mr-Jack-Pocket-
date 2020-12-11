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
	public String[] ActionToken = {"\\image3\\alibi-card.png","\\image3\\Jeton1-Face1.png","\\image3\\Jeton1-Face2.png","\\image3\\Jeton2-Face1.png","\\image3\\Jeton2-Face2.png","\\image3\\Jeton3-Face1.png","\\image3\\Jeton3-Face2.png","\\image3\\Jeton4-Face1.png","\\image3\\Jeton4-Face2.png"};
	public String[] TimeToken= {"\\image3\\timeinspector.png","\\image3\\timejack.png","\\image3\\timeinspector.png","\\image3\\timejack.png","\\image3\\timeinspector.png","\\image3\\timejack.png","\\image3\\timeinspector.png","\\image3\\timejack.png","\\image3\\timeinspector.png","\\image3\\timejack.png"};
	public String[] District= {"\\image3\\InspLestrade-recto.png","\\image3\\JeremyBert-recto.png","\\image3\\JohnPizer-recto.png","\\image3\\johnSmith-recto.png","\\image3\\JosephLane-recto.png","\\image3\\Madame-recto.png","\\image3\\MissStealthy-recto.png","\\image3\\SgtGoodley-recto.png","\\image3\\WilliamGull-recto.png","\\image3\\JosephLane-verso.png","\\image3\\common-verso.png",};
	public String[] AlibiCard= {"\\image3\\InspLestrade-alibi.png","\\image3\\JeremyBert-alibi.png","\\image3\\JohnPizer-alibi.png","\\image3\\JohnSmith-alibi.png","\\image3\\JosephLane-alibi.png","\\image3\\Madame-alibi.png","\\image3\\MissStealthy-alibi.png","\\image3\\SgtGoodley-alibi.png","\\image3\\WilliamGull-alibi.png"};
	public String[] Detective= {"\\image3\\Sherlock.png","\\image3\\Tobi.png","\\image3\\Watson.png"};
	
}
