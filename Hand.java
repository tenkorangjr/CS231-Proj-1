/*
 * Author: Michael Tenkorang
 * Black Jack Game
 * Date: 02/11/2023
 */

import java.util.ArrayList;

public class Hand {

    ArrayList<Card> cards;

    int count;

    /**
     * Creates an empty hand as an ArrayList of Cards.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Removes any cards currently in the hand.
     */
    public void reset() {
        cards.clear();
    }

    /**
     * Adds the specified card to the hand.
     * 
     * @param card the card to be added to the hand
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Returns the number of cards in the hand.
     * 
     * @return the number of cards in the hand
     */
    public int size() {
        return cards.size();
    }

    /**
     * Returns the card in the hand specified by the given index.
     * 
     * @param index the index of the card in the hand.
     * @return the card in the hand at the specified index.
     */
    public Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Returns the summed value over all cards in the hand.
     * 
     * @return the summed value over all cards in the hand
     */
    public int getTotalValue() {
        count = 0;
        for (Card my_card : cards) {
            count += my_card.getValue();
        }

        return count;
    }

    /**
     * Returns a string representation of the hand.
     * 
     * @return a string representation of the hand
     */
    public String toString() {
        return cards.toString() + " Total: " + getTotalValue();
    }
}