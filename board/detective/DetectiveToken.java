package board.detective;

import java.util.ArrayList;
import java.util.List;

import board.Cell;

public class DetectiveToken extends Cell {

	private List<DetectiveName> detectiveList = new ArrayList<>();
//TODO : add detective position and change mode method
	
	public void addDetective(DetectiveName detectiveName) {
		if (!(detectiveList.contains(detectiveName))) {
			detectiveList.add(detectiveName);
		}
	}

	public void removeDetective(DetectiveName detectiveName) {
		if (detectiveList.contains(detectiveName)) {
			detectiveList.remove(detectiveName);
		}
	}

	// Getters and Setters
	public List<DetectiveName> getDetectiveList() {
		return detectiveList;
	}

	public void setDetectiveList(List<DetectiveName> detectiveList) {
		this.detectiveList = detectiveList;
	}

	// Console toString
	public String toString() {
		return (detectiveList.toString() + "         ").substring(0, 8);
	}
}
