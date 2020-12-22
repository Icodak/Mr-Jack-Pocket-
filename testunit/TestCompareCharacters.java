package testunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import items.AlibiName;

class TestCompareCharacters {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		AlibiName a = AlibiName.INSPECTOR_LESTRADE;
		AlibiName b = AlibiName.INSPECTOR_LESTRADE;
		System.out.println(a==b);
	
	}

}
