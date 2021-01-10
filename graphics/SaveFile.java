package graphics;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.fasterxml.jackson.core.JsonProcessingException;

import program.JackPocketGame;
import saves.SaveLoad;

public class SaveFile {
	private String pathSave;

	public void savingFile(JFrame frame,JFileChooser fc,JackPocketGame jackGame) throws JsonProcessingException {
		int returnVal = fc.showSaveDialog (frame);	    		
		 if (returnVal == JFileChooser.APPROVE_OPTION) {
             File fichier = fc.getSelectedFile();
             pathSave=fichier.getAbsolutePath()+".json";
             SaveLoad.save(jackGame,pathSave);
		 }}
	public String getSave() {
		return pathSave;
	}}
