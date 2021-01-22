  
package graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import board.detective.DetectiveName;
import board.detective.DetectiveToken;
import board.district.District;
import board.district.Orientation;
import items.ActionToken;
import players.Player;
import program.Game;
import program.JackPocketGame;

public class NewJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private int[] matrice_position;
	private String path;
	private String pathtwo;
	private double angle;
	private JackPocketGame jackgame;
	private NewGraphicalWindow window;
	private int clicked=0;
	private int cellcountToMove;
	
	public int[] getMatrice_position() {
		return matrice_position;
	}
	public void setMatrice_position(int[] matrice_position) {
		this.matrice_position = matrice_position;
	}	
	public void setCellcountToMove(int cellcountToMove) {
		this.cellcountToMove = cellcountToMove;
	}
	public int getCellcountToMove() {
		return cellcountToMove;
	}
	public void setMatrice_position_int(int x,int y) {
		int matrice[]= {x,y};
		this.matrice_position = matrice;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPathtwo() {
		return pathtwo;
	}
	public void setPathtwo(String pathtwo) {
		this.pathtwo = pathtwo;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void JlabelClicked(NewJLabel label) throws InterruptedException {
		if(label.getMatrice_position()[0]==3) {
		String name=jackgame.getJackName().toString();
		String currentName=jackgame.getCurrentPlayer().getName().toString();
		
		if(jackgame.getTurnCount()==0 && currentName.equals("Jack") && clicked==0) {
		window.cardBack[0].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\alibicards\\"+"ALIBI_"+name+".png"), window.cartSize[0], window.cartSize[1]));
		clicked=1;
		window.information.setText("appuyez sur la pile de carte pour masquer votre carte Mr.Jack");
		}
		else if(jackgame.getTurnCount()==0 && currentName.equals("Jack") &&clicked==1) {
			window.cardBack[0].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\alibicards\\"+"ALIBI_"+"CARD"+".png"), window.cartSize[0], window.cartSize[1]));
			jackgame.setCurrentPlayer(new Player(false, "Detective"));
			window.information.setText(jackgame.getCurrentPlayer().toString()+"  it's your time to pick a action");
			Game.gameTurn(jackgame,window);
			}}	

		
		if(label.getMatrice_position()[0]==1 && jackgame.getTurnCount()>0 && window.actionPlaying==false) {
		if(jackgame.getActionTokenList().get(label.getMatrice_position()[1]).hasBeenPlayed()==false) {	
			if(jackgame.getActionTokenList().get(label.getMatrice_position()[1]).isRecto() ) {
				
		
		jackgame.playAction(jackgame.actionGetFromList(window,jackgame.getActionTokenList().get(label.getMatrice_position()[1]).getAction1().toString(),label.getMatrice_position()[1],jackgame),window,jackgame);
		
		String greyImage=new StringBuilder(label.getPath()).reverse().toString();
		greyImage="DESU_"+greyImage.substring(4, greyImage.length());
		greyImage=new StringBuilder(greyImage).reverse().toString();
		label.setPath(greyImage+".png");
		label.setIcon(window.reSize(new ImageIcon(greyImage+".png") ,window.allSize[1][0],window.allSize[1][1]));	
			}else {
		
		
		jackgame.playAction(jackgame.actionGetFromList(window,jackgame.getActionTokenList().get(label.getMatrice_position()[1]).getAction2().toString(),label.getMatrice_position()[1],jackgame),window,jackgame);

		
		String greyImage=new StringBuilder(label.getPath()).reverse().toString();
		greyImage="DESU_"+greyImage.substring(4, greyImage.length());
		greyImage=new StringBuilder(greyImage).reverse().toString();
		label.setPath(greyImage+".png");
		label.setIcon(window.reSize(new ImageIcon(greyImage+".png") ,window.allSize[1][0],window.allSize[1][1]));
			}
			System.out.println(jackgame);
		}
		}
		
 
		if(label.getMatrice_position()[0]==2 && window.swapDistrict) {
			int x=label.getMatrice_position()[0];
			int y=label.getMatrice_position()[1];	
			if(window.swapPosition[0][0]==x && window.swapPosition[0][1]==y) {
				window.swapPosition[0][0]=100;
				window.swapPosition[0][1]=100;
				window.compteur_District--;	
				label.setIcon(window.reSize(PaintJLabel.imageIconsuperposer(RotateImage.rotateImage(window.reSize(new ImageIcon(label.getPath()),
						window.allSizeCopy[2][0],window.allSizeCopy[2][0]),label.getAngle()),
						window.reSize(new  ImageIcon(label.getPathtwo()),window.allSizeCopy[6][0],
								window.allSizeCopy[6][1])),window.allSize[2][0],window.allSize[2][0]));
			}else {	
			window.swapPosition[window.compteur_District][0]=x;
			window.swapPosition[window.compteur_District][1]=y;	
			label.setIcon(window.reSize(PaintJLabel.imageIconsuperposerTwo(PaintJLabel.imageIconsuperposer(RotateImage.rotateImage(window.reSize(new ImageIcon(label.getPath()),
					window.allSizeCopy[2][0],window.allSizeCopy[2][0]),label.getAngle()),
					window.reSize(new  ImageIcon(label.getPathtwo()),window.allSizeCopy[6][0],
							window.allSizeCopy[6][1])),window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\districts\\selectedDistrict.png"),
									window.allSizeCopy[2][0],window.allSizeCopy[2][0])),window.allSize[2][0],window.allSize[2][0]));
			window.compteur_District++;	
			}
			if(window.compteur_District==2) {
				window.changeImage(window.matrice[window.swapPosition[0][0]][window.swapPosition[0][1]],window.matrice[window.swapPosition[1][0]][window.swapPosition[1][1]]);
				window.swapDistrict=false;
				window.compteur_District=0;
				jackgame.swap(window.transformCoordToList(window.swapPosition[0]),window.transformCoordToList(window.swapPosition[1]));
				window.changeTurn(window,jackgame);
				window.actionPlaying=false;
				window.swapPosition[0][0]=100;
				window.swapPosition[0][1]=100;
				window.swapPosition[1][0]=100;
				window.swapPosition[1][1]=100;
				
			}
		}
		
		if(label.getMatrice_position()[0]==2 && window.rotateDistrict ) {
			if(((District)jackgame.getBoard().getCell(window.transformCoordToList(label.getMatrice_position()))).isRotate()==false) {
			window.valider.setVisible(true);
			window.currentRotable = label.getMatrice_position();
			window.rotateDistrict=false;
			window.currentOrientation=label.getAngle();		
			window.end=true;
			((District)jackgame.getBoard().getCell(window.transformCoordToList(label.getMatrice_position()))).setRotate(true);
			}
			else {
				window.information.setText("this District have been already rotated");
			}
		}
		
		
		if(label.getMatrice_position()==(window.currentRotable)&&window.end) {
			window.rotateImage(label,(label.getAngle()%6.28+1.57));
			jackgame.rotate(window.RadianToOrientation(label.getAngle()), window.transformCoordToList(window.currentRotable),window );		
		}
				
		if((label.getPath().contains("USED")&&(label.getMatrice_position()[0]==4))||((label.getMatrice_position()[0]==5)&&label.getPath().contains("USED"))) {
			jackgame.moveDetectiveToken(window.detectiveToMove,label.getCellcountToMove(),window,jackgame);
			window.actionPlaying=false;
		}
		
		
		if(window.moveDetective) {
			window.coordOne[0]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(0);
			window.coordOne[1]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(1);
			window.coordTwo[0]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 2).get(0);
			window.coordTwo[1]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 2).get(1);
			window.moveDetective=false;		
			transformCoord(window.coordOne,1);
			transformCoord(window.coordTwo,2);
		}
		
		if(window.moveJoker && (label.getMatrice_position()[0]==5||label.getMatrice_position()[0]==4)) {
			String reverseImage=new StringBuilder(label.getPath().substring(System.getProperty("user.dir").length() + 35,label.getPath().length())).reverse().toString();
			reverseImage=new StringBuilder(reverseImage.substring(4,reverseImage.length())).reverse().toString();
			window.detectiveToMove= DetectiveName.valueOf(reverseImage);
			window.moveJoker=false;
			
			if (jackgame.getCurrentPlayer().isJack()) {
				window.coordOne[0]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 0).get(0);
				window.coordOne[1]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 0).get(1);
				window.coordTwo[0]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(0);
				window.coordTwo[1]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(1);
				
				transformCoord(window.coordOne,0);
				transformCoord(window.coordTwo,1);
			
			}else {
				window.coordTwo[0]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(0);
				window.coordTwo[1]=jackgame.getBoard().moveDetectiveTokenTwo(window.detectiveToMove, 1).get(1);
				
				transformCoord(window.coordTwo,1);						
			
			}}
	}


	public void transformCoord(int[] coord,int cellCount) {
		if(coord[0]==4) {
			for(int x=0;x<3;x++) {
				if(window.listemilieuTwo[9+(coord[1]-1)*3+x].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
			if(window.listemilieuTwo[9+(coord[1]-1)*3+1].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
				window.listemilieuTwo[9+(coord[1]-1)*3+1].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
						window.allSize[4][0],window.allSize[4][1]));
				window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuTwo[9+(coord[1]-1)*3+1]);
				window.listemilieuTwo[9+(coord[1]-1)*3+1].setCellcountToMove(cellCount);
				break;
			}else {
				window.listemilieuTwo[9+(coord[1]-1)*3+x].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
						window.allSize[4][0],window.allSize[4][1]));
				window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuTwo[9+(coord[1]-1)*3+x]);
				window.listemilieuTwo[9+(coord[1]-1)*3+x].setCellcountToMove(cellCount);
				break;
			}
			
			
				}
		
			}
		}
		else if(coord[0]==0) {
			for(int i=0;i<3;i++) {
				if(window.listemilieuTwo[(coord[1]-1)*3+i].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
					if(window.listemilieuTwo[(coord[1]-1)*3+1].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
						window.listemilieuTwo[(coord[1]-1)*3+1].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuTwo[(coord[1]-1)*3+1]);	
						window.listemilieuTwo[(coord[1]-1)*3+1].setCellcountToMove(cellCount);
						break;	
					}else {
						window.listemilieuTwo[(coord[1]-1)*3+i].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuTwo[(coord[1]-1)*3+i]);
						window.listemilieuTwo[(coord[1]-1)*3+i].setCellcountToMove(cellCount);
						break;
					}
					
						}
			}}
		
		else if(coord[1]==4) {
			for(int i=0;i<3;i++) {
				if(window.listemilieuThree[9+(coord[0]-1)*3+i].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
					if(window.listemilieuThree[9+(coord[0]-1)*3+1].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
						window.listemilieuThree[9+(coord[0]-1)*3+1].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuThree[9+(coord[0]-1)*3+1]);
						window.listemilieuThree[9+(coord[0]-1)*3+1].setCellcountToMove(cellCount);
					break;	
					}else {
						window.listemilieuThree[9+(coord[0]-1)*3+i].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuThree[9+(coord[0]-1)*3+i]);
						window.listemilieuThree[9+(coord[0]-1)*3+i].setCellcountToMove(cellCount);
					break;	
					}
						}
			}}	
		else if(coord[1]==0) {
			for(int i=0;i<3;i++) {
				if(window.listemilieuThree[(coord[0]-1)*3+i].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
					if(window.listemilieuThree[(coord[0]-1)*3+1].getPath().equals(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_.png")) {
						window.listemilieuThree[(coord[0]-1)*3+1].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuThree[(coord[0]-1)*3+1]);
						window.listemilieuThree[(coord[0]-1)*3+1].setCellcountToMove(cellCount);
					break;
					}else {
						window.listemilieuThree[(coord[0]-1)*3+i].setIcon(window.reSize(new ImageIcon(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove.toString()+"_USED.png"),
								window.allSize[4][0],window.allSize[4][1]));
						window.changePath(System.getProperty("user.dir")+"\\resources\\images\\tokens\\DETECTIVE_"+window.detectiveToMove+"_USED.png","",window.listemilieuThree[(coord[0]-1)*3+i]);
						window.listemilieuThree[(coord[0]-1)*3+i].setCellcountToMove(cellCount);
					break;
					}

						}
			}}
	}
	
	public void setJackGame(JackPocketGame jackgame) {
		this.jackgame = jackgame;
	}
	public void setWindow(NewGraphicalWindow window) {
		this.window = window;
	}
	 
	
	
	
	
	

}
