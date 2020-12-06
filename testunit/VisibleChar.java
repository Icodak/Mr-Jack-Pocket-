package testunit;

import org.junit.jupiter.api.Test;

import program.JackPocketGame;
import saves.SaveLoad;

class VisibleChar {

	@Test
	void test() {
		JackPocketGame j2 = SaveLoad.load(System.getProperty("user.dir") + "\\resources\\testToDown.json");
		System.out.println(j2.getBoard());
		System.out.println(j2.getBoard().visibleCharacters());

	}

}
