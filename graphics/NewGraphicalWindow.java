package graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.MatteBorder;

public class NewGraphicalWindow extends VariableWarehouse{
	
	public void initialize(NewGraphicalWindow  window) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		
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
			listegauche[i].setIcon( imageSize(TimeToken[i],timeSize[0], timeSize[1],listegauche[i]));
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
			cardBack[i].setIcon(imageSize(ActionToken[i],cartSize[0],cartSize[1],cardBack[i]));
			actionToken.add(cardBack[i]);
			}else {
			listedroit[i-1]=new  NewJLabel();
			listedroit[i-1].setMatrice_position_int(1,i-1);
			listedroit[i-1].setIcon(imageSize(ActionToken[i],actionSize[0],actionSize[1],listedroit[i-1]));
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
		

		for(int n=0;n<matrice[4].length;n++) {
			matrice[4][n].setIcon(imageSize( Detective[0], inspectorSize[0], inspectorSize[1],matrice[4][n]));
			matrice[5][n].setIcon(imageSize( Detective[0], inspectorSize[0], inspectorSize[1],matrice[5][n]));
		}
		for(int n=0;n<matrice[2].length;n++) {
			matrice[2][n].setIcon(imageSize(  District[n], distrcitSize[0], distrcitSize[1],matrice[2][n]));
		}
	
		
		for(NewJLabel[] n:matrice) {
			for(NewJLabel v:n) {
				if(v==null) {
				}else {		
				v.addMouseListener(new MouseListener());
				}}}
		frame.setVisible(true);
	}	
	

	
	
	
	
	private ImageIcon  imageSize(String path,int xSize, int ySize,NewJLabel label) {			
		ImageIcon icon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\resources"+path).getImage().getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT));
		label.setPath(path);
		return icon;
	}	
	public void changeIcon(String path,NewJLabel label) {
		int[] type = label.getMatrice_position();
		label.setIcon(imageSize(path, allSize[type[0]][0], allSize[type[0]][1],label));	
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
					changeIcon(v.getPath(),v);
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
					changeIcon(v.getPath(),v);
				}}
		}
	}
	
	}
	public void updateCell(String path,int[] coord) {
		changeIcon(path,matrice[coord[0]][coord[1]]);
	}
}

    


