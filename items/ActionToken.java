package items;

import board.detective.DetectiveName;

public class ActionToken {

	Actions action1;
	Actions action2;
	boolean isRecto = true;
	boolean hasBeenPlayed = false;
	DetectiveName action1Detective = null;
	DetectiveName action2Detective = null;
	
	public DetectiveName getAction1Detective() {
		return action1Detective;
	}

	public void setAction1Detective(DetectiveName action1Detective) {
		this.action1Detective = action1Detective;
	}

	public DetectiveName getAction2Detective() {
		return action2Detective;
	}

	public void setAction2Detective(DetectiveName action2Detective) {
		this.action2Detective = action2Detective;
	}

	public boolean isHasBeenPlayed() {
		return hasBeenPlayed;
	}

	public ActionToken(Actions action1, Actions action2) {
		this.action1 = action1;
		this.action2 = action2;

	}
	
	public Actions getAction1() {
		return action1;
	}

	public void setAction1(Actions action1) {
		this.action1 = action1;
	}

	public Actions getAction2() {
		return action2;
	}

	public void setAction2(Actions action2) {
		this.action2 = action2;
	}

	public boolean isRecto() {
		return isRecto;
	}

	public void setRecto(boolean isRecto) {
		this.isRecto = isRecto;
	}

	public boolean hasBeenPlayed() {
		return hasBeenPlayed;
	}

	public void setHasBeenPlayed(boolean hasBeenPlayed) {
		this.hasBeenPlayed = hasBeenPlayed;
	}

public String toString() {
	return "1:" + action1.toString() + " 2:" + action2.toString();
}


}
