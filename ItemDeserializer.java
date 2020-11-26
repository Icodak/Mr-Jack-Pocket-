import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class ItemDeserializer extends StdDeserializer<JackPocketGame> {

	public ItemDeserializer() {
		this(null);
	}

	public ItemDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public JackPocketGame deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);

		Cell[][] EmptyCellListe = new Cell[][] {
				new Cell[] { new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell() },
				new Cell[] { new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell() },
				new Cell[] { new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell() },
				new Cell[] { new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell() },
				new Cell[] { new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell(), new EmptyCell() } };

		// Board
		Board board = new Board(EmptyCellListe);

		for (int y = 0; y < node.get("board").size(); y++) {
			for (int x = 0; x < node.get("board").get(0).size(); x++) {
				// Generate DetectiveToken Cell
				if (node.get("board").get(y).get(x).get("type").asText().equals("DetectiveToken")) {
					DetectiveToken detectiveToken = new DetectiveToken();
					if (node.get("board").get(y).get(x).get("detectiveList").size() > 0) {
						String stringName = node.get("board").get(y).get(x).get("detectiveList").get(0).asText();
						DetectiveName name = DetectiveName.valueOf(stringName);
						detectiveToken.addDetective(name);
					}
					board.setCell(detectiveToken, Arrays.asList(x, y));

				}
				// Generate District Cell
				if (node.get("board").get(y).get(x).get("type").asText().equals("District")) {
					District district = new District(null, null);
					// Character
					String stringName = node.get("board").get(y).get(x).get("character").asText();
					AlibiName name = AlibiName.valueOf(stringName);
					district.setCharacter(name);
					// Shape
					String stringShape = node.get("board").get(y).get(x).get("districtType").asText();
					DistrictType shape = DistrictType.valueOf(stringShape);
					district.setType(shape);
					// Orientation
					String stringOrientation = node.get("board").get(y).get(x).get("orientation").asText();
					Orientation orientation = Orientation.valueOf(stringOrientation);
					district.setOrientation(orientation);
					// recto
					boolean isRecto = node.get("board").get(y).get(x).get("recto").asBoolean();
					district.setRecto(isRecto);

					board.setCell(district, Arrays.asList(x, y));
				}

			}
		}

		// Deck
		ArrayList<Card> cardDeck = new ArrayList<>();
		for (int i = 0; i < node.get("Card").size(); i++) {
			Card card = new Card();
			card.setCharacter(AlibiName.valueOf(node.get("Card").get(i).get(1).get("character").asText()));
			card.setHourglass(node.get("Card").get(i).get(1).get("hourglass").asInt());
			cardDeck.add(card);

		}

		JackPocketGame game = new JackPocketGame();
		game.setBoard(board);
		game.setCardDeck(cardDeck);
		System.out.println(cardDeck);
		return game;
	}
}