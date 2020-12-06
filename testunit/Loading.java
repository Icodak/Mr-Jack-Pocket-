package testunit;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import program.JackPocketGame;
import saves.SaveLoad;

class Loading {

	@Test
	void test() throws JsonProcessingException {
		//Local Save and Load test unit
		JackPocketGame j = SaveLoad.load("F:\\Documents\\inge-1\\mr JackPocket\\jtest.json");
		System.out.println(j.getActionTokenList());
		SaveLoad.save(j, "F:\\Documents\\inge-1\\mr JackPocket\\test5.json");
		JackPocketGame j2 = SaveLoad.load("F:\\Documents\\inge-1\\mr JackPocket\\test5.json");
		System.out.println(j2.getActionTokenList());
		
	}

}
