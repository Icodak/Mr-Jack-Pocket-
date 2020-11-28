package testunit;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import board.district.District;
import items.ActionToken;
import items.Actions;
import players.Player;
import program.JackPocketGame;
import saves.SaveLoad;

class TestActions2 {

	@Test
	void test() {
		
		
		JackPocketGame j2 = SaveLoad.Load("F:\\Documents\\inge-1\\mr JackPocket\\classicJack.json");
		j2.setCurrentPlayer(new Player());
		System.out.println(j2.getBoard());
		ActionToken act = new ActionToken(Actions.SWAP_DISTRICT, Actions.SWAP_DISTRICT);
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,1))));
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,2))));
		j2.playAction(act);
		System.out.println(j2.getBoard());
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,1))));
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,2))));
		j2.playAction(act);
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,1))));
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,2))));

		j2.close();


	}

}
