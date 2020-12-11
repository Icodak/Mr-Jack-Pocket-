import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.AncestorListener;

public class Menu  {
	private JmenuItemsSize[] items;
	private int[] currentResolution=new int[2];
	
	public Menu(JmenuItemsSize[] items) {
		super();
		this.items = items;
	}
	
	 public int[] getCurrentResolution() {
		return currentResolution;
	}


	public void createMenu(JFrame frame,String[] resolution,int[][] resolutionTable,NewGraphicalWindow window)
	{		
		JMenuBar toolBar = new JMenuBar();
		JMenu setting = new JMenu( "settings" );	
		JMenu others = new JMenu( "save game" );
		JMenuItem saveGame= new JMenuItem ("save my game");
		setting.add(others);
		others.add(saveGame);
		others.setIcon(new ImageIcon("C:\\Isep\\ing�nieur1\\projet_info\\interface tets\\image3\\save_as.png"));
		JMenu resolutionSize = new JMenu( "window size" );	
		JFileChooser fc = new JFileChooser ();
		resolutionSize.setIcon(new ImageIcon("C:\\Isep\\ing�nieur1\\projet_info\\interface tets\\image3\\jacksizeicon.png"));
		for(int n =0; n<resolution.length;n++) {
		items[n]= new JmenuItemsSize();
		items[n].setText(resolution[n]);
		items[n].setResolution(resolutionTable[n]);
		resolutionSize.add(items[n]);
		}
		setting.add(resolutionSize);
	    toolBar.add(setting);
	    frame.setJMenuBar(toolBar); 
	    for(JmenuItemsSize n:items) {
	    n.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		JmenuItemsSize event =(JmenuItemsSize)e.getSource();
	    		 currentResolution[0]=event.getResolution()[0];
	    		 currentResolution[1]=event.getResolution()[1]; 
	    		 window.changeSize(currentResolution);
	    		 frame.setSize(currentResolution[0],currentResolution[1]);
	         }
	    });}
	    saveGame.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SaveFile save = new SaveFile();
	    		save.savingFile(frame,fc);
	    		}

	    });    
	    }
	}

	

	
	

