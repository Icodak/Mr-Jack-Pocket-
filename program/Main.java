package program;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import graphics.NewGraphicalWindow;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		NewGraphicalWindow newW = new NewGraphicalWindow();
		newW.initialize(newW);
		//Game game = new Game();
	}
}
