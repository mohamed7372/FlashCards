package flashCards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static ArrayList<Card> cards = new ArrayList<Card>(); 
	static String allInputOutput = "";
	
	public static void main(String[] args) {
		int fin = 0;
		while(fin == 0) {
			String s = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\n";
			saveInOut(s);
			System.out.print(s);
			String choise = sc.nextLine();
			saveInOut(choise + "\n");
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
				String exitStr = "Bye bye!";
				saveInOut(exitStr);
				System.out.println(exitStr);
				break;
			case "hardest card":
				hardestCard();
				break;
			case "reset stats":
				resetStats();
				break;
			case "log":
				logInOut();
				break;
			}
			if(fin == 0) {
				saveInOut("\n");
				System.out.println();
			}
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
		saveInOut("The card:\n");
		System.out.println("The card:");
		String card = sc.nextLine();
		saveInOut(card + "\n");
		if(!isExiste(card, 1)) {
			System.out.println("The definition of the card:");
			saveInOut("The definition of the card:\n");
			String definition = sc.nextLine();
			saveInOut(definition+"\n");
			if(!isExiste(definition, 2)) {
				cards.add(new Card(card, definition));
				String res = "The pair (\"" + card + "\":\"" + definition + "\") has been added.";
				System.out.println(res);
				saveInOut(res + "\n");
			}
			else {
				String res = "The definition \"" + definition + "\" already exists.";
				System.out.println(res);
				saveInOut(res + "\n");
			}
		}
		else {
			String res = "The card \"" + card + "\" already exists.";
			System.out.println(res);
			saveInOut(res + "\n");
		}
	}
	static void remove() {
		System.out.println("The card:");
		saveInOut("The card:\n");
		String card = sc.nextLine();
		saveInOut(card + "\n");
		if(isExiste(card, 1)) {
			for (int i = 0; i < cards.size(); i++) {
				if(cards.get(i).card.equals(card)) {
					cards.remove(i);
					System.out.println("The card has been removed.");
					saveInOut("The card has been removed.\n");
					return;
				}
			}
		}
		System.out.println("Can't remove \"" + card + "\": there is no such card.");
	}
	static void importCard() {
		System.out.println("File name:");
		saveInOut("File name:");
		String nameFile = sc.nextLine();
		saveInOut(nameFile + "\n");
		File f = new File("C:\\Users\\HP\\eclipse-work\\zhard7\\src\\flashCards\\" + nameFile);
		try {
			if(!f.exists()) {
				System.out.println("File not found.");
				saveInOut("File not found.\n");
			}
			else {
				sc = new Scanner(f);
				int nbrCard = 0;
				while(sc.hasNext()) {
					String card = sc.nextLine();
					String definition = sc.nextLine();
					int mistake = Integer.valueOf(sc.nextLine());
					if(isExiste(card, 1)) {
						for (int i = 0; i < cards.size(); i++) {
							if(cards.get(i).card.equals(card)) {
								cards.get(i).definition = definition;
								cards.get(i).mistake = mistake;
								break;
							}
						}
					}
					else if(isExiste(definition, 2)) {
						for (int i = 0; i < cards.size(); i++) {
							if(cards.get(i).definition.equals(definition)) {
								cards.get(i).card = card;
								cards.get(i).mistake = mistake;
								break;
							}
						}
					}
					else {
						cards.add(new Card(card, definition));
						cards.get(cards.size()-1).mistake = mistake;
					}
					nbrCard++;
				}
				System.out.println(nbrCard + " cards have been loaded.");
				saveInOut(nbrCard + " cards have been loaded.\n");
				sc.close();
			}
		} 
		catch (Exception e) {
		}
	}
	static void ask() {
		System.out.println("How many times to ask?");
		saveInOut("How many times to ask?\n");
		int nbr = Integer.valueOf(sc.nextLine());
		saveInOut(nbr + "\n");
		for (int i = 0; i < nbr; i++) {
			String a = "Print the definition of \"" + cards.get(i).card + "\":";
			System.out.println(a);
			saveInOut(a + "\n");
			String inp = sc.nextLine();
			saveInOut(inp + "\n");
			String res = check(inp, cards.get(i).definition);
			if(!res.equals("Correct!"))
				cards.get(i).mistake++;
			System.out.println(res);
			saveInOut(res + "\n");
			if(i == cards.size()-1 && i < nbr) {
				i = -1;
				nbr -= cards.size();
			}
		}
	}
	static void exportCard() {
		System.out.println("File name:");
		saveInOut("File name:\n");
		String nameFile = sc.nextLine();
		saveInOut(nameFile + "\n"); 
		File f = new File("C:\\Users\\HP\\eclipse-work\\zhard7\\src\\flashCards\\" + nameFile);
		try {
			FileWriter fw = new FileWriter(f);
			if(cards.size() > 0) {
				fw.write(cards.get(0).card);
				fw.append("\n");
				fw.append(cards.get(0).definition);
				fw.append("\n");
				fw.append(String.valueOf(cards.get(0).mistake));
			}
			for (int i = 1; i < cards.size(); i++) {
				fw.append("\n");
				fw.append(cards.get(i).card);
				fw.append("\n");
				fw.append(cards.get(i).definition);
				fw.append("\n");
				fw.append(String.valueOf(cards.get(i).mistake));
			}
			System.out.println(cards.size() + " cards have been saved.");
			saveInOut(cards.size() + " cards have been saved.\n");
			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void hardestCard() {
		HashMap<Integer, ArrayList<String>> allMistakes = new HashMap<Integer, ArrayList<String>>();
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).mistake > 0) {
				if(allMistakes.containsKey(cards.get(i).mistake)) 
					allMistakes.get(cards.get(i).mistake).add(cards.get(i).card);
				else {
					ArrayList<String> str = new ArrayList<String>();
					str.add(cards.get(i).card);
					allMistakes.put(cards.get(i).mistake, str);
				}
			}
		}
		if(allMistakes.size() == 0) {
			System.out.println("There are no cards with errors.");
			saveInOut("There are no cards with errors.\n");
			return;
		}
		for (Integer mistake : allMistakes.keySet()) {
			if(allMistakes.get(mistake).size() == 1) {
				String gg = "The hardest card is \""+allMistakes.get(mistake).get(0)+"\". You have "+mistake+" errors answering it.";
				System.out.println(gg);
				saveInOut(gg + "\n");
			}
			else {
				System.out.print("The hardest card are \"" + allMistakes.get(mistake).get(0)+"\"");
				saveInOut("The hardest card are \"" + allMistakes.get(mistake).get(0)+"\"");
				for (int i = 1; i < allMistakes.get(mistake).size(); i++) {
					System.out.print(", \"" + allMistakes.get(mistake).get(i) + "\"");
					saveInOut(", \"" + allMistakes.get(mistake).get(i) + "\"");
				}
				System.out.println(". You have "+mistake+" errors answering them.");
				saveInOut(". You have "+mistake+" errors answering them.\n");
			}
		}
	}
	static void resetStats() {
		for (int i = 0; i < cards.size(); i++) 
			cards.get(i).mistake = 0;
		System.out.println("Card statistics have been reset.");
		saveInOut("Card statistics have been reset.\n");
	}
	static void logInOut() {
		System.out.println("File name:");
		saveInOut("File name:\n");
		String nameFile = sc.nextLine();
		saveInOut(nameFile + "\n");
		File f = new File("C:\\Users\\HP\\eclipse-work\\zhard7\\src\\flashCards\\" + nameFile);
		try {
			FileWriter fw = new FileWriter(f);
			saveInOut("The log has been saved.");
			fw.write(allInputOutput);
			System.out.println("The log has been saved.");
			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void saveInOut(String text) {
		allInputOutput = allInputOutput.concat(text);
	}
}

