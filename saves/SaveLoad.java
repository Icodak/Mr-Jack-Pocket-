package saves;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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

	private static Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	public static void Save(JackPocketGame jackPocketGame, String jack_file_location) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {
			String jsonDataString = mapper.writeValueAsString(jackPocketGame.getBoard()) + ",\"Card\":" + mapper.writeValueAsString(jackPocketGame.getCardDeck());
			setJack_file_location(jack_file_location);
			JackWriteToFile(jsonDataString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*public static void Load(String jack_file_location) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
		try {
			String jsonDataString = mapper.writeValueAsString(jackPocketGame.getBoard()) + ",\"Card\":" + mapper.writeValueAsString(jackPocketGame.getCardDeck());
			setJack_file_location(jack_file_location);
			JackWriteToFile(jsonDataString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	
	// Save to file Utility
	public static void JackWriteToFile(String myData) {
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
		}

		try {
			// Convenience class for writing character files
			FileWriter JackWriter;
			JackWriter = new FileWriter(JackFile.getAbsoluteFile(), true);

			// Writes text to a character-output stream
			BufferedWriter bufferWriter = new BufferedWriter(JackWriter);
			bufferWriter.write(myData.toString());
			bufferWriter.close();

			log("Data saved at file location: " + Jack_file_location + " Data: " + myData + "\n");
		} catch (IOException e) {
			log("Could not save data : " + e.toString());
		}
	}

	// Read From File
	public static JackPocketGame JackReadFromFile() {
		File JackFile = new File(Jack_file_location);
		if (!JackFile.exists())
			log("File doesn't exist");

		InputStreamReader isReader;
		try {
			isReader = new InputStreamReader(new FileInputStream(JackFile), "UTF-8");

			JsonReader myReader = new JsonReader(isReader);
			JackPocketGame jackPocketGame = gson.fromJson(myReader, JackPocketGame.class);

			return jackPocketGame;

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
