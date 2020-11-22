package fr.arsenesoulie.jackpocket;

public enum CellType {

	CROSS(false,false,false,false),
	T_SHAPE(false,false,true,false),
	LINE(false,true,false,true),
	L_SHAPE(false,false,true,true),
	CUL_DE_SAC(true,false,true,true),
	BLOCK(true,true,true,true);
	
	public final boolean e;
	public final boolean n;
	public final boolean w;
	public final boolean s;
	
	CellType(boolean e,boolean n, boolean w, boolean s){
		this.e = e;
		this.n = n;
		this.w = w;
		this.s = s;
	}
	
	public boolean[] getCellWalls() {
		return new boolean[] {e,n,w,s};
	}
	
}
