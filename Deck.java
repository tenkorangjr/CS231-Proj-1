/*
 * Author: Michael Tenkorang
 * Black Jack Game
 * Date: 02/11/2023
 * CS 231
 * Section B
 * Deck.java
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    ArrayList<Card> deck;
    Random random = new Random();

    /**
     * Creates the underlying deck as an ArrayList of Card objects.
     * Calls build() as a subroutine to build the deck itself.
     */
    public Deck() {
        deck = new ArrayList<>();
        build();
        shuffle();
    }

    /**
     * Builds the underlying deck as a standard 52 card deck.
     * Replaces any current deck stored.
     */
    public void build() {
        deck.clear();

        for (int i = 2; i <= 11; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i));
            }
        }
        for (int i = 0; i < 12; i++) {
            deck.add(new Card(10));
        }
    }

    /**
     * Returns the number of cards left in the deck.
     */
    public int size() {
        return deck.size();
    }

    /**
     * Returns and removes the first card of the deck.
     * 
     */
    public Card deal() {
        Card currCard = deck.get(0);
        deck.remove(0);

        return currCard;
    }

    /**
     * Shuffles the cards currently in the deck in-place.
     */
    public void shuffle() {
        int loop = deck.size();

        for (int i = loop - 1; i > 0; i--) {

            int randNum = random.nextInt(i + 1);

            Card temp = deck.get(i);
            deck.add(i, deck.get(randNum));
            deck.remove(deck.get(i + 1));
            deck.add(randNum, temp);
            deck.remove(randNum + 1);
        }

    }

    /**
     * Returns a string representation of the deck.
     */
    public String toString() {
        return deck.toString();
    }
}