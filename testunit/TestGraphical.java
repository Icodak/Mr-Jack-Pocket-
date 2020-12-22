package testunit;

import java.io.IOException;

import javax.swing.UnsupportedLookAndFeelException;

import org.junit.jupiter.api.Test;

import graphics.NewGraphicalWindow;

class TestGraphical {

	@Test
	void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {
		NewGraphicalWindow newW = new NewGraphicalWindow();
		newW.initialize(newW);
	}

}
