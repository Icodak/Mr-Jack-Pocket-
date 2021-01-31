package graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{

	@Override
	public  void mouseClicked(MouseEvent e) {
			NewJLabel jlabel=(NewJLabel) e.getComponent();
			try {
				jlabel.JlabelClicked(jlabel);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}	
	
}
