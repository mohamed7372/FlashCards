package flashCards;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String card = sc.nextLine();
		String definition = sc.nextLine();
		Card c = new Card(card, definition);
		String input = sc.nextLine();
		System.out.println(c.check(input));
	}
}
