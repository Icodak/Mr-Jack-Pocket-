package testunit;

import org.junit.jupiter.api.Test;

class TestMath {

	@Test
	void test() {
		int a = 2;
		if (a == 2) {
			a = 3;
		} else if (a == 3) {
			a = 4;
		}
		System.out.println(a);
	}

}
