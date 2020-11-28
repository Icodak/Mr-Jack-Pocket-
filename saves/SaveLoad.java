package saves;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import program.JackPocketGame;

public class SaveLoad {

	// Save Load methods to create and modify a game

	private static String Jack_file_location;

	public static String getJack_file_location() {
		return Jack_file_location;
	}

	public static void setJack_file_location(String jack_file_location) {
		Jack_file_location = jack_file_location;
	}


	@SuppressWarnings("deprecation")
	public static void Save(JackPocketGame jackPocketGame, String jack_file_location) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {

			String jsonDataString = mapper.writeValueAsString(jackPocketGame);
			setJack_file_location(jack_file_location);
			try {
				JackWriteToFile(jsonDataString);
			} catch (IncorrectFileNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static JackPocketGame Load(String jack_file_location){
		setJack_file_location(jack_file_location);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
			
			SimpleModule module = new SimpleModule();
			module.addDeserializer(JackPocketGame.class, new ItemDeserializer());
			mapper.registerModule(module);

			File jackFile = JackReadFromFile();
			
				try {
					JackPocketGame jackGame = mapper.readValue(jackFile,JackPocketGame.class);
					log("File successfully loaded at : " + jack_file_location);
					return jackGame;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			return null;
		
	}
	
	
	// Save to file Utility
	public static void JackWriteToFile(String myData) throws IncorrectFileNameException {
		File JackFile = new File(Jack_file_location);
		if (!JackFile.exists()) {
			try {
				File directory = new File(JackFile.getParent());
				if (!directory.exists()) {
					directory.mkdirs();
				}
				JackFile.createNewFile();
			} catch (IOException e) {
				log("Excepton Occured: " + e.toString());

			}
		} else {
			throw new IncorrectFileNameException("File already exists at : " + Jack_file_location);
			
		}

		try {
			// Convenience class for writing character files
			FileWriter JackWriter;
			JackWriter = new FileWriter(JackFile.getAbsoluteFile(), true);

			// Writes text to a character-output stream
			BufferedWriter bufferWriter = new BufferedWriter(JackWriter);
			bufferWriter.write(myData);
			bufferWriter.close();

			log("Data saved at file location: " + Jack_file_location);
		} catch (IOException e) {
			log("Could not save data : " + e.toString());
		}
	}

	// Read From File
	public static File JackReadFromFile() {
		File JackFile = new File(Jack_file_location);
		if (!JackFile.exists())
			log("File doesn't exist");

		try {
			return JackFile;


		} catch (Exception e) {
			log("error load cache from file " + e.toString());
		}

		log("\nData loaded successfully from file " + Jack_file_location);

		return null;
	}

	private static void log(String string) {
		System.out.println(string);
	}

}
