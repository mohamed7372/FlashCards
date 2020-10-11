package flashCards;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Card> cards = new ArrayList<Card>(); 
	
	public static void main(String[] args) {
		System.out.println("Input the number of cards:");
		int nbrCards = Integer.valueOf(sc.nextLine());
		int i = 1;
		while(i <= nbrCards) {
			System.out.println("Card #" + i + ":");
			String card = "", definition = "";
			do {
				card = sc.nextLine();
			}while(!isExiste(card, 1));
			System.out.println("The definition for card #" + i + ":");
			do {
				definition = sc.nextLine();				
			}while(!isExiste(definition, 2));
			
			cards.add(new Card(card, definition));
			i++;
		}
		i = 0;
		for (;  i<cards.size(); i++) {
			System.out.println("Print the definition of \"" + cards.get(i).card + "\":");
			System.out.println(check(sc.nextLine(), cards.get(i).definition));
		}
	}

	static boolean isExiste(String str, int n) {
		boolean tr = true;
		if(n == 1) {
			for (int j=0; j<cards.size(); j++) {
				if(cards.get(j).card.equals(str)) {
					tr = false;
					break;
				}
			}
			if(!tr) {
				System.out.println("The card \"" + str + "\" already exists. Try again:");
				return tr;
			}
		}
		else {
			for (int j=0; j<cards.size(); j++) {
				if(cards.get(j).definition.equals(str)) {
					tr = false;
					break;
				}
			}
			if(!tr) {
				System.out.println("The definition \"" + str + "\" already exists. Try again:");
				return tr;
			}
		}
		return true;
	}
	
	static String check(String input, String defi) {
		if(input.equals(defi))
			return "Correct!";
		
		String s = "";
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).definition.equals(input)) {
				s = cards.get(i).card;
				return "Wrong. The right answer is \"" + defi + "\", but your definition is correct for \"" + s + "\".";
			}
		}
		
		return "Wrong. The right answer is \"" + defi + "\".";
	}
}
