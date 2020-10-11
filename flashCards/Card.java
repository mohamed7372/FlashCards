package flashCards;

public class Card {
	String card;
	String definition;
	
	public Card(String card, String definition) {
		this.card = card;
		this.definition = definition;
	}

	@Override
	public String toString() {
		return "Card:\n" + card + "\nDefinition:\n" + definition;
	}
}
