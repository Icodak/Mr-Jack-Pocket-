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
		
		

		ActionToken act = new ActionToken(Actions.MOVE_DETECTIVE, Actions.ROTATE_DISTRICT);
		ActionToken act2 = new ActionToken(Actions.MOVE_DETECTIVE, Actions.ROTATE_DISTRICT);
System.out.println(act.getAction1() == act2.getAction1());

	}

}
