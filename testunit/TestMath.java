package testunit;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

class TestMath {

	@Test
	void test() {
		System.out.println(ThreadLocalRandom.current().nextInt(1, 3 + 1));

	}

}
