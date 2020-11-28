package items;

public enum Actions {

	MOVE_DETECTIVE(2),
	MOVE_JOCKER(1),
	DRAW_CARD(0),
	ROTATE_DISTRICT(0),
	SWAP_DISTRICT(0);

	int limitMove;
	
	
	private Actions(int limitMove) {
		this.limitMove = limitMove;
	}
	
	public int getLimitMove() {
		return limitMove;
	}
}
