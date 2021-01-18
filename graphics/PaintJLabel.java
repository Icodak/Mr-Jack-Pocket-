package graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PaintJLabel  extends JPanel{

	private static final long serialVersionUID = 1L;

	static ImageIcon imageIconsuperposer(ImageIcon icon, ImageIcon icon1){
			
		Image image1 = icon.getImage(); 
		Image image2 = icon1.getImage(); 	
		int w = 250;
		int h = 250;
		BufferedImage image = new BufferedImage(w, h,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.drawImage(image1, 0, 0, null);
		g2.drawImage(image2, 77, 76, null);
		g2.dispose();
		
		ImageIcon newImg = new ImageIcon(image);
		return newImg;
}
	static ImageIcon imageIconsuperposerTwo(ImageIcon icon, ImageIcon icon1){
			
		Image image1 = icon.getImage(); 
		Image image2 = icon1.getImage(); 	
		int w = 250;
		int h = 250;
		BufferedImage image = new BufferedImage(w, h,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.drawImage(image1, 0, 0, null);
		g2.drawImage(image2, 0, 0, null);
		g2.dispose();
		
		ImageIcon newImg = new ImageIcon(image);
		return newImg;
}
	
	
	
}