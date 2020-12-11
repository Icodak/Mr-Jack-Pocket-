package items;

public class TimeToken {
	private boolean isFlipped;
	private int hourGlass;

	public TimeToken(boolean isFlipped, int hourGlass) {
		super();
		this.isFlipped = isFlipped;
		this.hourGlass = hourGlass;
	}

	public boolean isFlipped() {
		return isFlipped;
	}

	public void setFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}

	public int getHourGlass() {
		return hourGlass;
	}

	public void setHourGlass(int hourGlass) {
		this.hourGlass = hourGlass;
	}

}
