package graphics;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.fasterxml.jackson.core.JsonProcessingException;

import program.JackPocketGame;

public class Menu  {
	private JmenuItemsSize[] items = new JmenuItemsSize[50];
	private JmenuItemsSize[] itemsTwo= new JmenuItemsSize[50];
	private int[] currentResolution=new int[2];
	
	public Menu(JmenuItemsSize[] items) {
		super();
		this.items = items;
	}
	
	
	public JmenuItemsSize[] getItemsTwo() {
		return itemsTwo;
	}
	
	 public void setItemsTwo(JmenuItemsSize[] itemsTwo) {
		this.itemsTwo = itemsTwo;
	}



	public int[] getCurrentResolution() {
		return currentResolution;
	}


	public void createMenu(JFrame frame,String[] sizeTextString,int[] sizeTextTable,String[] resolution,int[][] resolutionTable,NewGraphicalWindow window,JackPocketGame jackGame)
	{	
		
		//toolBar 
		JMenuBar toolBar = new JMenuBar();
		JMenu setting = new JMenu( "settings" );
		
		//toolBar options		
		JMenu others = new JMenu( "save game" );
		JMenu rules = new JMenu( "Game rules" );
		JMenu sizeText = new JMenu( "Text size" );
		JMenu resolutionSize = new JMenu( "window size" );	
	
		JMenuItem saveGame= new JMenuItem ("save my game");
		JMenuItem gameRules= new JMenuItem ("Game rules");
		
		//toolBar options Images
		sizeText.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\TEXT_SIZE.png"));
		rules.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\ICON_ABOUT.png"));
		others.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\ICON_SAVE_AS.png"));
		resolutionSize.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\resources\\images\\icons\\ICON_SIZE.png"));
		
		rules.add(gameRules);
		others.add(saveGame);
		setting.add(others);
		setting.add(rules);
		setting.add(sizeText);	
		
		
		//file finder
		JFileChooser fc = new JFileChooser ();
		
		
		
		//add sizes possible
		for(int n =0; n<sizeTextTable.length;n++) {
			itemsTwo[n]= new JmenuItemsSize();
			itemsTwo[n].setText(sizeTextString[n]);
			itemsTwo[n].setStringTextSize(sizeTextTable[n]);
			sizeText.add(itemsTwo[n]);
			}
		for(int n =0; n<resolution.length;n++) {
		items[n]= new JmenuItemsSize();
		items[n].setText(resolution[n]);
		items[n].setResolution(resolutionTable[n]);
		resolutionSize.add(items[n]);
		}
		
		setting.add(resolutionSize);
	    toolBar.add(setting);
	    frame.setJMenuBar(toolBar);
	    
	    //ACTIONS TRIGGERED BY THE MENU
	    
	    
	    
	    //change window size
	    for(JmenuItemsSize n:items) {
	    	if(n!=null) {
	    n.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JmenuItemsSize event =(JmenuItemsSize)e.getSource();
	    		 currentResolution[0]=event.getResolution()[0];
	    		 currentResolution[1]=event.getResolution()[1]; 
	    		 window.changeSize(currentResolution,jackGame);
	    		 frame.setSize(currentResolution[0],currentResolution[1]);
	         }
	    });}}
	    
	    //change text size
	    for(JmenuItemsSize n:itemsTwo) {
	    	if(n!=null) {
		    n.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		JmenuItemsSize event =(JmenuItemsSize)e.getSource();
		    		window.TextSize=event.getStringTextSize();
		    		window.information.setText(window.information.getText());
		    		window.information.setFont(new Font("Arial",Font.BOLD,window.TextSize));
		         }
		    });}}
	    
	    //save game
	    saveGame.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SaveFile save = new SaveFile();
	    		try {
					save.savingFile(frame,fc,jackGame);
				} catch (JsonProcessingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		}
	    });
	    
	    //show game rules
	    gameRules.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try {
					java.awt.Desktop.getDesktop().open(new File(System.getProperty("user.dir")+"\\rulesJackGame\\"+"rules.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}    		
	    	}
	    	});
	    	
	    
	    
	    
	    }





	}

	

