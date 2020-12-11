import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.MatteBorder;

public class MouseListener extends MouseAdapter {
	@Override
	public  void mouseClicked(MouseEvent e) {
			NewJLabel jlabel=(NewJLabel) e.getComponent();
			int[] matrice=jlabel.getMatrice_position();
			System.out.println(matrice[0]+"   "+matrice[1]);
		
	}	
}
