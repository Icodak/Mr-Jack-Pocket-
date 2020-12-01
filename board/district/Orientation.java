package board.district;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum Orientation {

	EAST, NORTH, WEST, SOUTH;

	private static final List<Orientation> ORIENTATIONS = Arrays.asList(Orientation.values());

	public static Orientation randomOrientation() {
		return ORIENTATIONS.get(ThreadLocalRandom.current().nextInt(0, 4));
	}
}
