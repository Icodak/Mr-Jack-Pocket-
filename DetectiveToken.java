
import java.util.ArrayList;

public class DetectiveToken extends Cell{
	
	private ArrayList<DetectiveName> detectiveList = new ArrayList<DetectiveName>();
	

	public DetectiveToken(){
	}
	
	public ArrayList<DetectiveName> getDetectiveList() {
		return detectiveList;
	}

	public void setDetectiveList(ArrayList<DetectiveName> detectiveList) {
		this.detectiveList = detectiveList;
	}
	
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
	

	public String toString(){
		return detectiveList.toString();
	}

	



}
