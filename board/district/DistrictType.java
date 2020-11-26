package board.district;

public enum DistrictType {

	CROSS_SHAPE(false, false, false, false), T_SHAPE(false, false, true, false), LINE_SHAPE(false, true, false, true),
	L_SHAPE(false, false, true, true), CUL_DE_SAC_SHAPE(true, false, true, true), BLOCK_SHAPE(true, true, true, true);

	public final boolean e;
	public final boolean n;
	public final boolean w;
	public final boolean s;

	DistrictType(boolean e, boolean n, boolean w, boolean s) {
		this.e = e;
		this.n = n;
		this.w = w;
		this.s = s;
	}

	public boolean[] getCellWalls() {
		return new boolean[] { e, n, w, s };
	}

}
