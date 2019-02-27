package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		// COMPLETE THIS METHOD
		if (deckRear.cardValue == 27)
		{
			for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
			{
				if (tmp.next == deckRear){
					CardNode prevDeckRear = tmp; 
					CardNode n = deckRear.next;
					deckRear.next = n.next;
					n.next = deckRear;
					prevDeckRear.next = n; 
					deckRear = n;
					
					return;
				}
			}
		}
		if (deckRear.next.cardValue == 27)
		{
			CardNode tmp = deckRear.next;
			deckRear.next = tmp.next;
			tmp.next = tmp.next.next;
			deckRear.next.next = tmp;
			return;
		}
		CardNode previous = deckRear.next;
		for (CardNode tmp = deckRear.next.next; tmp != deckRear; tmp = tmp.next)
		{
			if (tmp.cardValue == 27	)
			{
				previous.next = tmp.next;
				tmp.next = tmp.next.next;
				previous.next.next = tmp;
				if (previous.next == deckRear){
					deckRear = tmp;
				}
				return;	
			}
			previous = previous.next;
		}
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD

		CardNode TwoForward = deckRear.next.next.next; 
		CardNode previous = deckRear;
		for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
		{
			if (deckRear.cardValue == 28)
			{
				for (CardNode tmp2 = deckRear.next; tmp2 != deckRear; tmp2 = tmp2.next)
				{
					if (tmp2.next == deckRear)
					{
						previous = tmp2;
						previous.next = deckRear.next;
						deckRear.next = deckRear.next.next.next;
						tmp.next.next = deckRear;
						deckRear = previous.next;
						return;
					}
				}
			}
			if (tmp.cardValue == 28)
			{
				if (tmp.next == deckRear)
				{
					previous.next = tmp.next; 
					tmp.next = deckRear.next.next;
					deckRear.next.next = tmp;
					deckRear = deckRear.next ; 
					return;
				}
				previous.next = tmp.next;
				tmp.next = TwoForward.next;
				TwoForward.next = tmp;
				if (TwoForward == deckRear)
				{
					deckRear = tmp;
				}
				return;
			}
			previous = previous.next;
			TwoForward = TwoForward.next;
		}
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		// COMPLETE THIS METHOD
		if (deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28)
		{
			for (CardNode tmp = deckRear.next.next; tmp != deckRear.next; tmp = tmp.next)
			{
				if (tmp.cardValue == 27 || tmp.cardValue ==28)
				{
					deckRear = tmp;
					return;
				}
			}
		}
		if (deckRear.cardValue == 27 || deckRear.cardValue == 28)
		{
			CardNode previous = deckRear; 
			for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
			{
				if (tmp.cardValue == 27 || tmp.cardValue ==28)
				{
					deckRear = previous;
					return;
				}
				previous = previous.next;
			}
		}
		CardNode previous = deckRear; 
		for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
		{
			if (tmp.cardValue == 27 || tmp.cardValue == 28)
			{
				for (CardNode tmp2 = tmp.next; tmp2 != deckRear; tmp2 = tmp2.next)
				{
					if (tmp2.cardValue == 27 || tmp2.cardValue == 28)
					{
						previous.next = tmp2.next; 
						tmp2.next = deckRear.next;
						deckRear.next = tmp; 
						deckRear = previous; 
						return;
					}
				}
			}
			previous = previous.next; 
		}
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		// COMPLETE THIS METHOD
		for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
		{
			if (tmp.next == deckRear)
			{
				int count = 0;
				int value = deckRear.cardValue;
				if (value == 28)
				{
					value =27;
				}
				for(CardNode EndOfCut = deckRear.next; EndOfCut != deckRear; EndOfCut = EndOfCut.next)
				{
					if (count == value-1)
					{
						tmp.next = deckRear.next;
						deckRear.next = EndOfCut.next;
						EndOfCut.next = deckRear;
						return;
					}
					count++;
				}
			}
		}
	}
	
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		printList(deckRear);
		jokerA();
		printList(deckRear);
		jokerB();
		printList(deckRear);
		tripleCut();
		printList(deckRear);
		countCut();
		printList(deckRear);
		
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		int value = deckRear.next.cardValue;
		int count = 0;
		if (deckRear.next.cardValue == 28)
		{
			value = 27;
		}
		for (CardNode tmp = deckRear.next; tmp != deckRear; tmp = tmp.next)
		{
			if (count == value-1)
			{
				if (tmp.next.cardValue == 27 || tmp.next.cardValue == 28)
				{
					return getKey();
				}
				return tmp.next.cardValue;
			}
			count ++;
		}
	    return -1;
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue	);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		String s = "";
		for (int i = 0; i < message.length(); i++)
		{
			if (message.charAt(i) > 64 && message.charAt(i) <91)
			{
				int key = getKey();
				System.out.print(key);
				char ch =message.charAt(i);
				System.out.print(ch+" ");
				int c = ch-'A'+1+key;
				System.out.print(c+ " ");
				if (c>26)
				{
				c = c-26;
				System.out.print(c+ " ");
				}
				ch = (char)(c-1+'A');
				System.out.print(ch + " ");
				s+= ch + " "; 
			}
		}	
	    return s;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		String s = "";
		for (int i = 0; i < message.length(); i++)
		{
			if (message.charAt(i) > 64 && message.charAt(i) <91)
			{
				int key = getKey();
				System.out.print(key);
				char ch =message.charAt(i);
				System.out.print(ch+" ");
				int c = ch-'A'+1-key;
				if (c<1)
				{
				c = c+26;
				ch = (char)(c-1+'A');
				s+= ch + " "; 
				}
				else
				{
				ch = (char)(c-1+'A');
				s+= ch + " "; 
				}
			}
		}
	    return s;
	}
	/*public static void main(String [] args){
		Solitaire x = new Solitaire(); 
		x.makeDeck();
		printList(x.deckRear);
		x.countCut();
		printList(x.deckRear);
		
		
	}*/
}
