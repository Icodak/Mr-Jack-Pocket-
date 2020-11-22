public abstract class ActionToken {
	private boolean isRecto;
	
	public void playAction(){	
	}
	
	public void flip() {
		isRecto = !isRecto;
	}

	public boolean isRecto() {
		return isRecto;
	}

	public void setRecto(boolean isRecto) {
		this.isRecto = isRecto;
	}
}
