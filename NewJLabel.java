import javax.swing.JLabel;

public class NewJLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	int[] matrice_position;
	String path;
	
	
	public int[] getMatrice_position() {
		return matrice_position;
	}
	public void setMatrice_position(int[] matrice_position) {
		this.matrice_position = matrice_position;
	}

	public void setMatrice_position_int(int x,int y) {
		int matrice[]= {x,y};
		this.matrice_position = matrice;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}