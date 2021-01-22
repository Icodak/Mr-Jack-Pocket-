package graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

import com.fasterxml.jackson.core.JsonProcessingException;

import board.detective.DetectiveToken;
import board.district.District;
import board.district.Orientation;
import items.ActionToken;
import items.AlibiName;
import players.Player;
import program.Game;
import program.JackPocketGame;
import saves.SaveLoad;

public class NewGraphicalWindow extends VariableWarehouse{
	
	// THE WINDOW GAME
	public void initialize(NewGraphicalWindow  window,JackPocketGame jackgame) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
		
		//main window divided in 4 parts(up,left,center,right)
		frame = new JFrame();
		frame.setTitle("Mrjackpocket");
		frame.setIconImage(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\ICON_LOGO.png").getImage());	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Menu bar
		Menu menu =new Menu(new JmenuItemsSize[resolution.length]);
		menu.createMenu(frame,resolution,resolutionTable,window,jackgame);
		
		//UP
		JPanel up= new JPanel();
		up.setBorder(new MatteBorder(3, 3, 3, 3,new Color(0, 0, 0)));
		frame.getContentPane().add(up, BorderLayout.NORTH);		
		JLabel gameNews= new JLabel("game information");
		up.add( gameNews);
		this.information=gameNews;
		
		//LEFT
		JPanel left = new JPanel();
		frame.getContentPane().add(left, BorderLayout.WEST);
		left.setLayout(new GridLayout(0, 1, 0, 5));
		for(int i=0;i<8;i++) {
			listegauche[i]=new NewJLabel();
			listegauche[i].setMatrice_position_int(0, i);
			imageInitialize(TimeToken[i],"",listegauche[i]);
			listegauche[i].setIcon(reSize(new ImageIcon(listegauche[i].getPath()),allSize[0][0],allSize[0][1]));
			left.add(listegauche[i]);
		}
		
		
		//RIGHT
		JPanel right = new JPanel();
		frame.getContentPane().add(right, BorderLayout.EAST);
		right.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel information = new JPanel();
		JButton bouton = new JButton("valider");
		bouton.setVisible(false);
		valider=bouton;
	    bouton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		
		    		window.end=false;
		    		bouton.setVisible(false);
		    		changeTurn( window,jackgame);
		    		window.actionPlaying=false;
		         }
		    });
		information.add(bouton);
		right.add(information);
		
		
		
		JPanel actionToken = new JPanel();
		right.add(actionToken);
		actionToken.setLayout(new GridLayout(0, 1,0,0));
		for(int i=0;i<5;i++) {
			if(i==0) {
			cardBack[i]=new NewJLabel();
			cardBack[i].setMatrice_position_int(3,i);
			imageInitialize(ActionToken[0],"",cardBack[i]);
			cardBack[i].setIcon(reSize(new ImageIcon(cardBack[i].getPath()),allSize[3][0],allSize[3][1]));
			actionToken.add(cardBack[i]);
			}else {
			listedroit[i-1]=new  NewJLabel();
			listedroit[i-1].setMatrice_position_int(1,i-1);
			imageInitialize(ActionToken[i],"",listedroit[i-1]);
			listedroit[i-1].setIcon(reSize(new ImageIcon(listedroit[i-1].getPath()),allSize[1][0],allSize[1][1]));
			 actionToken.add(listedroit [i-1]);
			}
		}
			
		//CENTER
		JPanel center = new JPanel();
		center .setLayout(new GridBagLayout());	
		frame.getContentPane().add(center , BorderLayout.CENTER);
		GridBagConstraints gc = new GridBagConstraints();
		
		for(int k=0;k<5;k++) {	
		if(k==0||k==4)	{
			for(int n=1;n<4;n++) {
				gc.gridx = n;
				gc.gridy = k;
				if(k==0) {
				liste[n-1]= new JPanel();
				center.add(liste[n-1], gc);
				}else {
				liste[17+n]= new JPanel();
				center.add(liste[17+n], gc);
				}
			}
		}			
		else {
			for(int n=0;n<5;n++) {
				gc.gridx = n;
				gc.gridy = k;
				liste[3+5*(k-1)+n]=new JPanel();
				center.add(liste[3+5*(k-1)+n], gc);
			}}
		}
		int indice=0;
		for(int n:coordLayout) {
			liste3[indice]=new NewJLabel();
			liste3[indice].setMatrice_position_int(2,indice);
			liste[n].add(liste3[indice]);
			indice++;
		}
		indice=0;
		for(int n:coordLayoutTwo) {
			liste2[indice]=new JPanel();
			liste2[indice].setLayout(new GridLayout(1,0, 0, 0));
			liste[n].add(liste2[indice]);
			indice++;
		}
		indice=0;
		for(int n:coordLayoutThree) {
			liste6[indice]=new JPanel();
			liste6[indice].setLayout(new GridLayout(0,1, 0, 0));
			liste[n].add(liste6[indice]);
			indice++;
		}
		indice=0;
		for(JPanel n:liste2) {
			for(int k=0;k<3;k++) {
				listemilieuTwo[3*indice+k]=new NewJLabel();
				listemilieuTwo[3*indice+k].setMatrice_position_int(4,3*indice+k);
				n.add(listemilieuTwo[3*indice+k]);
			}
			indice++;
		}
		indice=0;
		for(JPanel n:liste6) {
			for(int k=0;k<3;k++) {
				listemilieuThree[3*indice+k]=new NewJLabel();
				listemilieuThree[3*indice+k].setMatrice_position_int(5,3*indice+k);
				n.add(listemilieuThree[3*indice+k]);
			}		
			indice++;
		}
		
	//ADD MouseListener to whole NewJLabel 
		for(NewJLabel[] n:matrice) {
			for(NewJLabel v:n) {
				if(v==null) {
				}else {		
				v.addMouseListener(new MouseListener());
				v.setJackGame(jackgame);
				v.setWindow(window);
				}}}
	frame.setVisible(true);
		
	iniatialiseBoard(jackgame);
	
	
	}	
	

	
	
	public ArrayList<Integer> transformCoordToList(int[] coord) {
		if(coord[1]<3) {
			return new ArrayList<Integer>(Arrays.asList(1,(coord[1]%3)+1));
		}else if(coord[1]<6) {
			return new ArrayList<Integer>(Arrays.asList(2,(coord[1]%3)+1));
		}else{
			return new ArrayList<Integer>(Arrays.asList(3,(coord[1]%3)+1));
		}
	}
	


	// SHOW A CARD 
	public void showCard(String cardName,NewGraphicalWindow window,JackPocketGame jackGame) throws InterruptedException{
		cardBack[0].setIcon(reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\alibicards\\ALIBI_"+cardName+".png"),allSize[3][0],allSize[3][1]));
		window.actionPlaying=false;
		window.changeTurn( window,jackGame);
		//hideCard();
	}
	public void hideCard() throws InterruptedException {
		cardBack[0].setIcon(reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\alibicards\\ALIBI_"+"CARD"+".png"),allSize[3][0],allSize[3][1]));
	}

	//Initialise the BOARD ( District,ActionToken,Inspector)
	private void  iniatialiseBoard(JackPocketGame jackgame) {
		updateDistrict(jackgame);
		updateInspector(jackgame);
		randomizeAction(jackgame);	
		}
	
	public void updateDistrict(JackPocketGame jackgame) {
		int indice=0;
		for(int x=1;x<4;x++) {
			for(int y=1;y<4;y++) {
			AlibiName name=((District) jackgame.getBoard().getCellInt(x, y)).getCharacter();
			String newName=name.toString();
			Orientation orientation=((District) jackgame.getBoard().getCellInt(x, y)).getOrientation();		
			if(newName.equals("JOSEPH_LANE")) {
				if(((District) jackgame.getBoard().getCellInt(x, y)).isRecto()) {
			imageInitialize(District[0],"\\resources\\images\\caracter\\"+newName+".png",liste3[indice]);	
				}else {
			imageInitialize(District[2],"",liste3[indice]);			
				}
			}else {
				if(((District) jackgame.getBoard().getCellInt(x, y)).isRecto()) {
			imageInitialize(District[1],"\\resources\\images\\caracter\\"+newName+".png",liste3[indice]);
				}else {
			imageInitialize(District[1],"",liste3[indice]);		
				}
			}		
			rotateImage(liste3[indice],orientationToRadian( orientation));		
			indice++;
			}}
	}
	
	public void updateInspector(JackPocketGame jackgame) {
		int indicetwo=0;
		for(int x=1;x<jackgame.getBoard().getBoard().length-1;x++) {	
			String[] detectiveName=((DetectiveToken) jackgame.getBoard().getCellInt(x,0)).getDetectiveListToString();
			String[] detectiveNametwo=((DetectiveToken) jackgame.getBoard().getCellInt(x, jackgame.getBoard().getBoard().length-1)).getDetectiveListToString();
			
			String[] detectiveNamethree=((DetectiveToken) jackgame.getBoard().getCellInt(0,x)).getDetectiveListToString();
			String[] detectiveNamefour=((DetectiveToken) jackgame.getBoard().getCellInt(jackgame.getBoard().getBoard().length-1,x)).getDetectiveListToString();
			
			for(int nb=0;nb<3;nb++) {
				imageInitialize("\\resources\\images\\tokens\\DETECTIVE_"+detectiveName[nb]+".png","", listemilieuThree[indicetwo]);
				imageInitialize("\\resources\\images\\tokens\\DETECTIVE_"+detectiveNametwo[nb]+".png","",listemilieuThree[9+indicetwo]);
				imageInitialize("\\resources\\images\\tokens\\DETECTIVE_"+detectiveNamethree[nb]+".png","", listemilieuTwo[indicetwo]);
				imageInitialize("\\resources\\images\\tokens\\DETECTIVE_"+detectiveNamefour[nb]+".png","", listemilieuTwo[9+indicetwo]);
				
			listemilieuThree[indicetwo].setIcon(reSize(new ImageIcon(listemilieuThree[indicetwo].getPath()), inspectorSize[0], inspectorSize[1]) );
			listemilieuThree[9+indicetwo].setIcon(reSize(new ImageIcon(listemilieuThree[9+indicetwo].getPath()), inspectorSize[0], inspectorSize[1]) );			
			listemilieuTwo[indicetwo].setIcon(reSize(new ImageIcon(listemilieuTwo[indicetwo].getPath()), inspectorSize[0], inspectorSize[1]) );
			listemilieuTwo[9+indicetwo].setIcon(reSize(new ImageIcon(listemilieuTwo[9+indicetwo].getPath()), inspectorSize[0], inspectorSize[1]) );
			indicetwo++;			
			}
	
	}}

	public void randomizeAction(JackPocketGame jackgame) {
		int indicetwo=0;		
		for(ActionToken list: jackgame.getActionTokenList() ) {
			
			if(list.toString().equals("MOVE_DETECTIVE")) {
				if(list.isRecto()) {
					  imageInitialize("\\resources\\images\\tokens\\"+list.getAction1Detective().toString()+".png","", listedroit[indicetwo]);				
				}else {
					  imageInitialize("\\resources\\images\\tokens\\"+list.getAction2Detective().toString()+".png","", listedroit[indicetwo]);	 
				}	  
			}else {
			   imageInitialize("\\resources\\images\\tokens\\"+list.toString()+".png","", listedroit[indicetwo]);	
			}			
			listedroit[indicetwo].setIcon(reSize(new ImageIcon(listedroit[indicetwo].getPath()),actionSize[0],actionSize[1]));
			indicetwo++;
		}}
	

	public double orientationToRadian(Orientation orientation) {

		if(orientation.toString().equals("EAST")){
			double angle=1.57;
			return angle;
		}else if(orientation.toString().equals("NORTH")){
			double angle=6.28;
			return angle;
		}else if(orientation.toString().equals("WEST")){
			double angle=4.71;
			return angle;
		}else{
			double angle=3.14;
			return angle;
		}	
	}
	public Orientation RadianToOrientation(double radian) {
		if(radian==1.57){
			return Orientation.EAST;
		}else if(radian==6.28){
			return Orientation.NORTH;
		}else if(radian==4.71){
			return Orientation.WEST;
		}else{
			return Orientation.SOUTH;
		}
	}
	
	
	
	
	
	private void  imageInitialize(String path,String pathtwo, NewJLabel label) {			
		label.setPath(System.getProperty("user.dir")+path);
		label.setPathtwo(System.getProperty("user.dir")+pathtwo);
	}	

	public void  changePath(String path,String pathtwo, NewJLabel label) {			
		label.setPath(path);
		label.setPathtwo(pathtwo);
	}	
	
	
	public ImageIcon reSize(ImageIcon image,int xSize,int ySize) {
		ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT));
		return icon;
	}
	
	private void changeSizeItems( NewJLabel label) {
		if(label.getMatrice_position()[0]==2) {
		label.setIcon(reSize(PaintJLabel.imageIconsuperposer(reSize(new ImageIcon(label.getPath()),allSizeCopy[2][0],allSizeCopy[2][1]), reSize(new ImageIcon(label.getPathtwo()),allSizeCopy[6][0],allSizeCopy[6][1])),allSize[2][0],allSize[2][1]));	
		 rotateImage(label,label.getAngle());
		}
		else {	
		label.setIcon(reSize(new ImageIcon(label.getPath()),allSize[label.getMatrice_position()[0]][0],allSize[label.getMatrice_position()[0]][1]));
		}}
	
	public void rotateImage(NewJLabel label,double angle) {
		label.setIcon(reSize(PaintJLabel.imageIconsuperposer(RotateImage.rotateImage(reSize(new ImageIcon(label.getPath()),allSizeCopy[2][0],allSizeCopy[2][1]),angle),reSize(new ImageIcon(label.getPathtwo()),allSizeCopy[6][0],allSizeCopy[6][1])),allSize[2][0],allSize[2][1]));
		label.setAngle(angle);
	}
	
	public void changeImage(NewJLabel label,NewJLabel labeltwo) {
	String pathlabel=label.getPath();
	String pathtwolabel=label.getPathtwo();
	double anglelabel=label.getAngle();
			
	changePath(labeltwo.getPath(),labeltwo.getPathtwo(), label);
	changePath(pathlabel,pathtwolabel, labeltwo);
	
	rotateImage(label, labeltwo.getAngle());
	rotateImage(labeltwo, anglelabel);
	}
	
	public	void changeTurn(NewGraphicalWindow window, JackPocketGame jackgame) {
		int indice=0;
		for(ActionToken token:jackgame.getActionTokenList()) {
			if(token.hasBeenPlayed()) {
				indice++;
			}	
		}
		if(indice==1) {
				System.out.println(jackgame.getCurrentPlayer().toString()+"after change");
			jackgame.switchPlayer();
			System.out.println(jackgame.getCurrentPlayer().toString()+"with change");
			window.information.setText(jackgame.getCurrentPlayer().toString()+"   it's your time to pick a action");
		}else if(indice==3) {
			jackgame.switchPlayer();
			window.information.setText(jackgame.getCurrentPlayer().toString()+"  it's your time to pick a action");
			
		}else if(indice==4) {
			
			Player winningPlayer = jackgame.hasReactedObjectives(window,jackgame);

			if (winningPlayer != null) {
			information.setText(winningPlayer + " wins, congratulations !!");
			}
			// Else continue the game
			else {
				jackgame.switchPlayer();
				jackgame.gameTurn(jackgame,window);
				information.setText(jackgame.getCurrentPlayer().toString()+"  it's your time to pick a action");
			}
		}
		
	}
	
	
	public void changeSize(int[] size, JackPocketGame jackGame){
		if(size[0]<1600 || size[1]<1050) {
		int indice=0;
		float newX=1600/((float)size[1]);
		float newY=1050/((float)size[0]);
		float newXY=(newX+newY)/2;
		for(int[] n:allSize) {
			n[0]=(int)(((float)allSizeCopy[indice][0])/ newXY);
			n[1]=(int)(((float)allSizeCopy[indice][1])/ newXY);
			indice++;
		}
		for(NewJLabel[] n:matrice) {
			for(NewJLabel v:n) {
				if(v==null) {				
				}else {		
					changeSizeItems(v);

				}}
		}
	}else {
		int indice=0;
		for(int[] n:allSize) {
			n[0]=(int)(((float)allSizeCopy[indice][0]));
			n[1]=(int)(((float)allSizeCopy[indice][1]));
			indice++;
		}
		for(NewJLabel[] n:matrice) {
			for(NewJLabel v:n) {
				if(v==null) {				
				}else {		
					changeSizeItems(v);
					

				}}
		}
	}
	
	}
	
	
	public void MainWindow(NewGraphicalWindow  window) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		JFrame frametwo = new JFrame();
		frametwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frametwo.getContentPane().setLayout(null);
		frametwo.setTitle("MrjackpocketMain");
		frametwo.setBounds(660, 240, 600, 600);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frametwo.setIconImage(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\ICON_LOGO.png").getImage());	
		frametwo.setVisible(true);
		frametwo.setResizable(false);
		
		JButton btnNewButton = new JButton("vs ia");
		btnNewButton.setBounds(50, 50, 200, 150);
		frametwo.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New game");
		btnNewButton_1.setBounds(350, 50, 200, 150);
		frametwo.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("load game");
		btnNewButton_2.setBounds(200, 250, 200, 150);
		frametwo.getContentPane().add(btnNewButton_2);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGraphicalWindow window = new NewGraphicalWindow();
				frametwo.dispose();
				try {
					Game.launchGame(System.getProperty("user.dir") +"\\resources\\saved_games\\classicJack.json",window);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException | IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGraphicalWindow window = new NewGraphicalWindow();
				frametwo.dispose();
				JFileChooser choosingFile = new JFileChooser ();
				int val= choosingFile.showOpenDialog(frametwo);
				if (val == JFileChooser.APPROVE_OPTION) {
		             File loadingGame = choosingFile.getSelectedFile();
		             try {
						Game.launchGame(loadingGame.getAbsolutePath(),window);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException | IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }}
				
			}
		);
		
	
	
	
	
	
} 
	}


