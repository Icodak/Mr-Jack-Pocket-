package testunit;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import program.JackPocketGame;
import saves.SaveLoad;

class Loading {

	@Test
	void test() throws JsonProcessingException {
		//Local Save and Load test unit
		JackPocketGame j = SaveLoad.Load("F:\\Documents\\inge-1\\mr JackPocket\\classicJack.json");
		SaveLoad.Save(j, "F:\\Documents\\inge-1\\mr JackPocket\\test.json");
		JackPocketGame j2 = SaveLoad.Load("F:\\Documents\\inge-1\\mr JackPocket\\test.json");
		
		
	}

}
