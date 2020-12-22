package testunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class TestListOrder {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		int[][] a = new int[][] {{0,1,2},{3,4,5},{6,7,8}};
		for (int[] y : a) {
			for (int x : y) {
				System.out.print(x);
			}
			System.out.println();
		}

		System.out.println(a[1][0]);
		
		
		
	}

}
