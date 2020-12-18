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
	private static String jackFileLocation;

	// Constructor
	private SaveLoad() {
		throw new IllegalStateException("Utility class");
	}

	@SuppressWarnings("deprecation")
	public static void save(JackPocketGame jackPocketGame, String jackFileLocation) throws JsonProcessingException {
		// Save using serialisation methods
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();

		try {

			String jsonDataString = mapper.writeValueAsString(jackPocketGame);
			setjackFileLocation(jackFileLocation);
			try {
				jackWriteToFile(jsonDataString);
			} catch (IncorrectFileNameException e) {
				e.printStackTrace();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public static JackPocketGame load(String jackFileLocation) {
		// Load using custom deserialisation methods
		setjackFileLocation(jackFileLocation);
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();

		SimpleModule module = new SimpleModule();
		module.addDeserializer(JackPocketGame.class, new ItemDeserializer());
		mapper.registerModule(module);

		File jackFile = jackReadFromFile();

		try {
			JackPocketGame jackGame = mapper.readValue(jackFile, JackPocketGame.class);
			log("File successfully loaded at : " + jackFileLocation);
			return jackGame;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void jackWriteToFile(String myData) throws IncorrectFileNameException {
		// Write to file
		File jackFile = new File(jackFileLocation);
		// try to build new file
		if (!jackFile.exists()) {
			try {
				File directory = new File(jackFile.getParent());
				if (!directory.exists()) {
					directory.mkdirs();
				}
				if (jackFile.createNewFile()) {
					log("New file created");
				}
			} catch (IOException e) {
				log("Excepton Occured: " + e.toString());
			}
		} else {
			throw new IncorrectFileNameException("File already exists at : " + jackFileLocation);
		}
		// try to write to file
		try {
			FileWriter jackWriter;
			jackWriter = new FileWriter(jackFile.getAbsoluteFile(), true);
			BufferedWriter bufferWriter = new BufferedWriter(jackWriter);
			bufferWriter.write(myData);
			bufferWriter.close();

			log("Data saved at file location: " + jackFileLocation);
		} catch (IOException e) {
			log("Could not save data : " + e.toString());
		}
	}

	public static File jackReadFromFile() {
		// Read From File
		File jackFile = new File(jackFileLocation);
		if (!jackFile.exists())
			log("File doesn't exist");

		try {
			return jackFile;
		} catch (Exception e) {
			log("error load cache from file " + e.toString());
		}
		log("\nData loaded successfully from file " + jackFileLocation);
		return null;
	}


	// Getters and Setters
	public static String getjackFileLocation() {
		return jackFileLocation;
	}

	public static void setjackFileLocation(String jackFileLoc) {
		jackFileLocation = jackFileLoc;
	}
	
	//Console out log
	private static void log(String string) {
		System.out.println(string);
	}

}
