package testunit;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import board.detective.DetectiveName;
import board.district.District;
import items.ActionToken;
import items.Actions;
import program.JackPocketGame;
import saves.SaveLoad;

class TestActions {

	@Test
	void test() {
		
		
		JackPocketGame j2 = SaveLoad.Load("F:\\Documents\\inge-1\\mr JackPocket\\classicJack.json");
		
		System.out.println(j2.getBoard());
		ActionToken act = new ActionToken(Actions.MOVE_DETECTIVE, Actions.ROTATE_DISTRICT);
		act.setAction1Detective(DetectiveName.SHERLOCK);
		j2.playAction(act);
		System.out.println(j2.getBoard());
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,1))));
		j2.playAction(act);
		System.out.println(((District) j2.getBoard().getCell(Arrays.asList(1,1))));



	}

}
