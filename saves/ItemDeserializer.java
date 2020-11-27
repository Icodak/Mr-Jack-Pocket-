package saves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import board.Board;
import board.Cell;
import board.detective.DetectiveName;
import board.detective.DetectiveToken;
import board.district.District;
import board.district.DistrictType;
import board.district.Orientation;
import items.AlibiName;
import items.Card;
import program.JackPocketGame;

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

		Cell[][] EmptyCellListe = new Cell[node.get("board").get("board").get(0).size()][node.get("board").get("board")
				.size()];

		// Board
		Board board = new Board(EmptyCellListe);

		for (int y = 0; y < node.get("board").get("board").size(); y++) {
			for (int x = 0; x < node.get("board").get("board").get(0).size(); x++) {
				// Generate DetectiveToken Cell
				if (node.get("board").get("board").get(y).get(x).get("type").asText().equals("DetectiveToken")) {
					DetectiveToken detectiveToken = new DetectiveToken();
					if (node.get("board").get("board").get(y).get(x).get("detectiveList").size() > 0) {

						for (int i = 0; i < node.get("board").get("board").get(y).get(x).get("detectiveList")
								.size(); i++) {
							String stringName = node.get("board").get("board").get(y).get(x).get("detectiveList").get(i)
									.asText();
							DetectiveName name = DetectiveName.valueOf(stringName);
							detectiveToken.addDetective(name);
						}
					}
					board.setCell(detectiveToken, Arrays.asList(x, y));

				}
				// Generate District Cell
				if (node.get("board").get("board").get(y).get(x).get("type").asText().equals("District")) {
					District district = new District();
					// Character
					String stringName = node.get("board").get("board").get(y).get(x).get("character").asText();
					AlibiName name = AlibiName.valueOf(stringName);
					district.setCharacter(name);
					// Shape
					String stringShape = node.get("board").get("board").get(y).get(x).get("districtType").asText();
					DistrictType shape = DistrictType.valueOf(stringShape);
					district.setDistrictType(shape);
					// Orientation
					String stringOrientation = node.get("board").get("board").get(y).get(x).get("orientation").asText();
					Orientation orientation = Orientation.valueOf(stringOrientation);
					district.setOrientation(orientation);
					// recto
					boolean isRecto = node.get("board").get("board").get(y).get(x).get("recto").asBoolean();
					district.setRecto(isRecto);

					board.setCell(district, Arrays.asList(x, y));
				}

			}
		}

		// Deck
		ArrayList<Card> cardDeck = new ArrayList<>();
		for (int i = 0; i < node.get("cardDeck").size(); i++) {
			Card card = new Card();
			card.setCharacter(AlibiName.valueOf(node.get("cardDeck").get(i).get("character").asText()));
			card.setHourglass(node.get("cardDeck").get(i).get("hourglass").asInt());
			cardDeck.add(card);

		}

		JackPocketGame game = new JackPocketGame();
		game.setBoard(board);
		game.setCardDeck(cardDeck);
		return game;
	}
}