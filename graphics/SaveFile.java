package graphics;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveFile {
	private String pathSave;

	public void savingFile(JFrame frame,JFileChooser fc) {
		int returnVal = fc.showSaveDialog (frame);	    		
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
             File fichier = fc.getSelectedFile();
             pathSave=fichier.getAbsolutePath()+".json";
		 }}
	public String getSave() {
		return pathSave;
	}}
