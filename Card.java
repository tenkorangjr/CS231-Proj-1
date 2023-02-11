/*
 * Author: Michael Tenkorang
 * Black Jack Game
 * Date: 02/11/2023
 * CS 231
 * Section B
 * Card.java
 */

public class Card {

    /**
     * The value of the card.
     */
    private int value;

    /**
     * Constructs a card with the specified value.
     */

    public Card(int val) {
        value = val;
    }

    /**
     * Returns the value of the card.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns a string representation of this card.
     */
    public String toString() {
        return "" + value;
    }
}
