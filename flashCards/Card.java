package flashCards;

class Card {
	String card;
	String definition;
	int mistake;
	
	public Card(String card, String definition) {
		this.card = card;
		this.definition = definition;
		this.mistake = 0;
	}

	@Override
	public String toString() {
		return "Card:\n" + card + "\nDefinition:\n" + definition;
	}
}
