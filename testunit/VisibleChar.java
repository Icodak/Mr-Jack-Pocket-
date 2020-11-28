package testunit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.detective.DetectiveName;
import items.ActionToken;
import items.Actions;
import program.JackPocketGame;
import saves.SaveLoad;

class VisibleChar {

	@Test
	void test() {
		JackPocketGame j2 = SaveLoad.Load("F:\\Documents\\inge-1\\mr JackPocket\\classicJack.json");
		System.out.println(j2.getBoard());
		System.out.println(j2.getBoard().visibleCharacters());

	}

}
