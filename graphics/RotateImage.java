package graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class RotateImage {
	//rotate a image
	static ImageIcon rotateImage(ImageIcon icon,double d) {	
		
		Image image1 = icon.getImage();
		int w = 250;
		int h = 250;
		BufferedImage image = new BufferedImage(w, h,  BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();	
		g2.rotate(d,125,125);
		g2.drawImage(image1, 0, 0, null);
		g2.dispose();
		ImageIcon newImg = new ImageIcon(image);
		return newImg;
	
	}
}