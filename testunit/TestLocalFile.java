package testunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TestLocalFile {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String localFile = System.getProperty("user.dir") + "\\resources\\classicJack.json";
		System.out.println(localFile);
		//JackPocketGame j = SaveLoad.Load(localFile);
	}

}
