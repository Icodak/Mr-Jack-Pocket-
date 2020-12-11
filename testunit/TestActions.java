package testunit;

import org.junit.jupiter.api.Test;

import program.JackPocketGame;
import saves.SaveLoad;

class TestActions {

	@Test
	void test() {
		
		
		JackPocketGame j2 = SaveLoad.load(System.getProperty("user.dir") + "\\resources\\classicJack.json");
		System.out.println(j2);
		
		System.out.println(j2.getActionTokenList().get(0).getAction1().toString());
		System.out.println(j2.getActionTokenList().get(0).getAction1Detective());
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		j2.playAction(j2.getActionTokenList().get(0));
		j2.getActionTokenList().get(0).setRecto(true);
		System.out.println(j2);
		
	}

}
