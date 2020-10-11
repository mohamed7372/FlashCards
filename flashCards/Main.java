package flashCards;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		System.out.println("Input the number of cards:");
		int nbrCards = Integer.valueOf(sc.nextLine());
		int i = 1;
		while(i <= nbrCards) {
			System.out.println("Card #" + i + ":");
			String card = sc.nextLine();
			System.out.println("The definition for card #" + i + ":");
			String definition = sc.nextLine();
			
			cards.add(new Card(card, definition));
			i++;
		}
		i = 0;
		for (;  i<cards.size(); i++) {
			System.out.println("Print the definition of \"" + cards.get(i).card + "\":");
			System.out.println(cards.get(i).check(sc.nextLine()));
		}
	}
}
