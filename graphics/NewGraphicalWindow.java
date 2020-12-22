package graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

public class NewGraphicalWindow extends VariableWarehouse{
	
	public void initialize(NewGraphicalWindow  window) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
		
		//main window divided in 4 parts(up,left,center,right)
		frame = new JFrame();
		frame.setTitle("Mrjackpocket");
		frame.setIconImage(new ImageIcon(System.getProperty("user.dir") + "\\images\\icons\\ICON_LOGO.png").getImage());	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Menu bar
		Menu menu =new Menu(new JmenuItemsSize[resolution.length]);
		menu.createMenu(frame,resolution,resolutionTable,window);
		
		//UP
		JPanel up= new JPanel();
		up.setBorder(new MatteBorder(3, 3, 3, 3,new Color(0, 0, 0)));
		frame.getContentPane().add(up, BorderLayout.NORTH);		
		JLabel gameNews= new JLabel("game information");
		up.add( gameNews);
		
		
		//LEFT
		JPanel left = new JPanel();
		frame.getContentPane().add(left, BorderLayout.WEST);
		left.setLayout(new GridLayout(0, 1, 50, 5));
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
		right.add(information);
		
		
		
		JPanel actionToken = new JPanel();
		right.add(actionToken);
		actionToken.setLayout(new GridLayout(0, 1, 0, 0));
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
		
	
		
		for(NewJLabel[] n:matrice) {
			for(NewJLabel v:n) {
				if(v==null) {
				}else {		
				v.addMouseListener(new MouseListener());
				}}}
		frame.setVisible(true);
		
	for(int n=0;n<liste3.length;n++) {
		imageInitialize(District[0],caracter[n],liste3[n]);
		liste3[n].setIcon(reSize(PaintJlabel.imageIconsuperposer(reSize(new ImageIcon(liste3[n].getPath()),allSize[2][0],allSize[2][1]), reSize(new ImageIcon(liste3[n].getPathtwo()),allSize[6][0],allSize[6][1])),allSize[2][0],allSize[2][1]));	
		liste3[n].setAngle(0);
	}
	for(int n=0;n<listemilieuThree.length;n++) {
		imageInitialize(Detective[0],"",listemilieuThree[n]);
		imageInitialize(Detective[0],"",listemilieuTwo[n]);
		listemilieuThree[n].setIcon(reSize(new ImageIcon(listemilieuThree[n].getPath()),allSize[4][0],allSize[4][1]));	
		listemilieuTwo[n].setIcon(reSize(new ImageIcon(listemilieuTwo[n].getPath()),allSize[4][0],allSize[4][1]));	
	}
	Thread.sleep(10000);
	rotateImage(liste3[0],1.57);
	changeImage(liste3[0],liste3[2]);
	}	
	
	
	
	
	

	
	
	
	
	private void  imageInitialize(String path,String pathtwo, NewJLabel label) {			
		label.setPath(System.getProperty("user.dir")+path);
		label.setPathtwo(System.getProperty("user.dir")+pathtwo);
	}	

	private void  changePath(String path,String pathtwo, NewJLabel label) {			
		label.setPath(path);
		label.setPathtwo(pathtwo);
	}	
	
	
	
	private ImageIcon reSize(ImageIcon image,int xSize,int ySize) {
		ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT));
		return icon;
	}
	
	private void changeSizeItems( NewJLabel label) {
		if(label.getMatrice_position()[0]==2) {
		label.setIcon(reSize(PaintJlabel.imageIconsuperposer(reSize(new ImageIcon(label.getPath()),allSizeCopy[2][0],allSizeCopy[2][1]), reSize(new ImageIcon(label.getPathtwo()),allSizeCopy[6][0],allSizeCopy[6][1])),allSize[2][0],allSize[2][1]));	
		}
		else {	
		label.setIcon(reSize(new ImageIcon(label.getPath()),allSize[label.getMatrice_position()[0]][0],allSize[label.getMatrice_position()[0]][1]));
		}}
	private void  rotateImage(NewJLabel label,double angle) {
		label.setIcon(reSize(PaintJlabel.imageIconsuperposer(RotateImage.rotateImage(reSize(new ImageIcon(label.getPath()),allSizeCopy[2][0],allSizeCopy[2][1]),angle),reSize(new ImageIcon(label.getPathtwo()),allSizeCopy[6][0],allSizeCopy[6][1])),allSize[2][0],allSize[2][1]));
		label.setAngle(angle);
	}
	private void changeImage(NewJLabel label,NewJLabel labeltwo) {
	String pathlabel=label.getPath();
	String pathtwolabel=label.getPathtwo();
	double anglelabel=label.getAngle();
			
	changePath(labeltwo.getPath(),labeltwo.getPathtwo(), label);
	changePath(pathlabel,pathtwolabel, labeltwo);
	
	rotateImage(label, labeltwo.getAngle());
	rotateImage(labeltwo, anglelabel);
	}
	
	
	
	
	public void changeSize(int[] size){
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
	
	
	public void MainWindow() {
		JFrame frametwo = new JFrame();
		frametwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frametwo.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frametwo.setTitle("MrjackpocketMain");
		frametwo.setIconImage(new ImageIcon(System.getProperty("user.dir") + "\\images\\icons\\ICON_LOGO.png").getImage());	
		frametwo.setVisible(true);
		
		JButton btnNewButton = new JButton("vs ia");
		btnNewButton.setSize(500, 500);
		frametwo.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New game");
		btnNewButton.setSize(500, 500);
		frametwo.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("load game");
		btnNewButton.setSize(500, 500);
		frametwo.getContentPane().add(btnNewButton_2);
	}
	
	
	
	
	
} 


