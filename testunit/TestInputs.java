package testunit;

import org.junit.jupiter.api.Test;

import program.InputListener;

class TestInputs {

	@Test
	void test() {
		InputListener listen = new InputListener();

		System.out.println(listen.getInputCoord());

	}

}
