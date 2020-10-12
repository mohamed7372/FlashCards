package flashCards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Card> cards = new ArrayList<Card>(); 
	
	public static void main(String[] args) {
		int fin = 0;
		while(fin == 0) {
			System.out.println("Input the action (add, remove, import, export, ask, exit):");
			String choise = sc.nextLine();
			switch (choise) {
			case "add": 
				add();
				break;
			case "remove":
				remove();
				break;
			case "import":
				importCard();
				sc = new Scanner(System.in);
				break;
			case "ask":
				ask();
				break;
			case "export":
				exportCard();
				break;
			case "exit":
				fin = 1;
				System.out.println("Bye bye!");
				break;
			}
			System.out.println();
		}
	}

	static boolean isExiste(String str, int n) {
		if(n == 1) {
			for (int j=0; j<cards.size(); j++) {
				if(cards.get(j).card.equals(str)) 
					return true;
			}
		}
		else {
			for (int j=0; j<cards.size(); j++) {
				if(cards.get(j).definition.equals(str)) 
					return true;
			}
		}
		return false;
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

	static void add() {
		System.out.println("The card:");
		String card = sc.nextLine();
		if(!isExiste(card, 1)) {
			System.out.println("The definition of the card:");
			String definition = sc.nextLine();
			if(!isExiste(definition, 2)) {
				cards.add(new Card(card, definition));
				System.out.println("The pair (\"" + card + "\":\"" + definition + "\") has been added.");
			}
			else
				System.out.println("The definition \"" + definition + "\" already exists.");
		}
		else
			System.out.println("The card \"" + card + "\" already exists.");
	}
	static void remove() {
		System.out.println("The card:");
		String card = sc.nextLine();
		if(isExiste(card, 1)) {
			for (int i = 0; i < cards.size(); i++) {
				if(cards.get(i).card.equals(card)) {
					cards.remove(i);
					System.out.println("The card has been removed.");
					return;
				}
			}
		}
		System.out.println("Can't remove \"" + card + "\": there is no such card.");
	}
	static void importCard() {
		System.out.println("File name:");
		String nameFile = sc.nextLine();
		File f = new File("C:\\Users\\HP\\eclipse-work\\zhard7\\src\\flashCards\\" + nameFile);
		try {
			if(!f.exists())
				System.out.println("File not found.");
			else {
				sc = new Scanner(f);
				int nbrCard = 0;
				while(sc.hasNext()) {
					String card = sc.nextLine();
					String definition = sc.nextLine();
					if(isExiste(card, 1)) {
						for (int i = 0; i < cards.size(); i++) {
							if(cards.get(i).card.equals(card)) {
								cards.get(i).definition = definition;
								break;
							}
						}
					}
					else if(isExiste(definition, 2)) {
						for (int i = 0; i < cards.size(); i++) {
							if(cards.get(i).definition.equals(definition)) {
								cards.get(i).card = card;
								break;
							}
						}
					}
					else 
						cards.add(new Card(card, definition));
					nbrCard++;
				}
				System.out.println(nbrCard + " cards have been loaded.");
				sc.close();
			}
		} 
		catch (Exception e) {
		}
	}
	static void ask() {
		System.out.println("How many times to ask?");
		int nbr = Integer.valueOf(sc.nextLine());
		for (int i = 0; i < nbr; i++) {
			System.out.println("Print the definition of \"" + cards.get(i).card + "\":");
			System.out.println(check(sc.nextLine(), cards.get(i).definition));
			if(i == cards.size()-1 && i < nbr) {
				i = -1;
				nbr -= cards.size();
			}
		}
	}
	static void exportCard() {
		System.out.println("File name:");
		String nameFile = sc.nextLine();
		File f = new File("C:\\Users\\HP\\eclipse-work\\zhard7\\src\\flashCards\\" + nameFile);
		try {
			FileWriter fw = new FileWriter(f);
			if(cards.size() > 0) {
				fw.write(cards.get(0).card);
				fw.append("\n");
				fw.append(cards.get(0).definition);
			}
			for (int i = 1; i < cards.size(); i++) {
				fw.append("\n");
				fw.append(cards.get(i).card);
				fw.append("\n");
				fw.append(cards.get(i).definition);
			}
			System.out.println(cards.size() + " cards have been saved.");
			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

