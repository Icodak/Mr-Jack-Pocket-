package testunit;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import graphics.NewGraphicalWindow;
import program.Game;

class TestGame {

	@Test
	void test() throws JsonProcessingException {
		NewGraphicalWindow window = new NewGraphicalWindow();
		window.MainWindow();
	}

}
