package flashCards;

class Card {
	String card;
	String definition;
	
	public Card(String card, String definition) {
		this.card = card;
		this.definition = definition;
	}

	public String check(String input) {
		if(input.equals(this.definition))
			return "Correct!";
		return "Wrong. The right answer is \"" + this.definition + "\".";
	}
	
	@Override
	public String toString() {
		return "Card:\n" + card + "\nDefinition:\n" + definition;
	}
}
