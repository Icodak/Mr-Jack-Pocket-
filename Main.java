import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		
		ObjectMapper mapper= new ObjectMapper();
		mapper.enableDefaultTyping();
		
		Board plato = Game.generateGameBoard();
		System.out.println(plato);
		ArrayList<Card> Deck = Game.generateCardDeck();

		String jsonDataString = mapper.writeValueAsString(plato);	
		String jsonDataString2 = mapper.writeValueAsString(Deck);	
		
		System.out.println(jsonDataString);
		System.out.println(jsonDataString2);
		
		SimpleModule module = new SimpleModule();
		module.addDeserializer(JackPocketGame.class, new ItemDeserializer());
		mapper.registerModule(module);
		
		DetectiveToken d = new DetectiveToken();
		d.addDetective(DetectiveName.valueOf("WATSON"));
		
		JackPocketGame jeu = mapper.readValue("{\"board\":[[{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]}],[{\"type\":\"DetectiveToken\",\"detectiveList\":[\"SHERLOCK\"]},{\"type\":\"District\",\"character\":\"INSPECTOR_LESTRADE\",\"orientation\":\"SOUTH\",\"walls\":[false,true,false,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"JEREMY_BERT\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"JOHN_PIZER\",\"orientation\":\"NORTH\",\"walls\":[false,false,false,true],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"DetectiveToken\",\"detectiveList\":[\"WATSON\"]}],[{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"District\",\"character\":\"JOHN_SMITH\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"JOSEPH_LANE\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"MADAME\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"DetectiveToken\",\"detectiveList\":[]}],[{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"District\",\"character\":\"MISS_STENTHY\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"SERGENT_GOODLEY\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"District\",\"character\":\"WILLIAM_GULL\",\"orientation\":\"EAST\",\"walls\":[false,false,true,false],\"districtType\":\"T_SHAPE\",\"recto\":true},{\"type\":\"DetectiveToken\",\"detectiveList\":[]}],[{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[\"TOBBY\"]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]},{\"type\":\"DetectiveToken\",\"detectiveList\":[]}]]},[[\"Card\",{\"character\":\"WILLIAM_GULL\",\"hourglass\":1}],[\"Card\",{\"character\":\"JOHN_SMITH\",\"hourglass\":1}],[\"Card\",{\"character\":\"JOSEPH_LANE\",\"hourglass\":1}],[\"Card\",{\"character\":\"SERGENT_GOODLEY\",\"hourglass\":0}],[\"Card\",{\"character\":\"JOHN_PIZER\",\"hourglass\":1}],[\"Card\",{\"character\":\"JEREMY_BERT\",\"hourglass\":1}],[\"Card\",{\"character\":\"INSPECTOR_LESTRADE\",\"hourglass\":0}],[\"Card\",{\"character\":\"MADAME\",\"hourglass\":2}],[\"Card\",{\"character\":\"MISS_STENTHY\",\"hourglass\":1}]]", JackPocketGame.class);
		
		
		//SaveLoad.setJack_file_location("F:\\Documents\\inge-1\\mr Jack Pocket\\jack.json");
		//SaveLoad.JackWriteToFile(gson.toJson(jeu));
		
		
		
	}
}
