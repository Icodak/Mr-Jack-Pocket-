package testunit;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import program.Game;

class TestGame {

	@Test
	void test() throws JsonProcessingException {
		Game.launchGame();
	}

}
