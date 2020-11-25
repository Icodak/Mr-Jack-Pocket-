import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		
		
		ObjectMapper mapper= new ObjectMapper();
		mapper.enableDefaultTyping();
		
		Board plato = new Board(Game.generateGameBoard());
		ArrayList<Card> Deck = Game.generateCardDeck();

		String jsonDataString = mapper.writeValueAsString(plato);	
		String jsonDataString2 = mapper.writeValueAsString(Deck);	
		
		System.out.println(jsonDataString);
		System.out.println(jsonDataString2);
		
		//SaveLoad.setJack_file_location("F:\\Documents\\inge-1\\mr Jack Pocket\\jack.json");
		//SaveLoad.JackWriteToFile(gson.toJson(jeu));
		
		
		
	}
}
