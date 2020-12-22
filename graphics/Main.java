package graphics;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
		NewGraphicalWindow window = new NewGraphicalWindow();
		//window.initialize(window);
		window.MainWindow();
	}

}
