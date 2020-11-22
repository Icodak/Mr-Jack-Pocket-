package fr.arsenesoulie.jackpocket;

public class Cell<T> {
	
	private T cell;
	
	public Cell(T cell) {
		this.setCell((T) cell);
		
	}

	public T getCell() {
		return cell;
	}

	public void setCell(T cell2) {
		this.cell = cell2;
	}
	
	

}
