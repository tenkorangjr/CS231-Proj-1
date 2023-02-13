/*
 * Author: Michael Tenkorang
 * Black Jack Game
 * Date: 02/11/2023
 * CS 231
 * Section B
 * BlackJack.java
 */

import java.util.Scanner;

public class BlackJack {

    // For the deck of cards
    Deck deck;
    // For the hands to play the game
    Hand playerHand;
    Hand dealerHand;

    // For reshuffling
    int minShuffle;

    // For input
    Scanner uScanner;

    public BlackJack(int reshuffleCutOff) {
        /*
         * Constructor with defined reshuffle cutoff
         */
        minShuffle = reshuffleCutOff;
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        uScanner = new Scanner(System.in);
    }

    public BlackJack() {
        /*
         * Constructor with default reshuffle cutoff
         */
        minShuffle = 26;
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
        uScanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BlackJack blackJackGame = new BlackJack();
        blackJackGame.game(true);
    }

    private int checkHigher(Hand player, Hand dealer) {
        /*
         * Check for the hand with the higher value of cards
         * Returns 1 if player has higher, -1 if dealer has higher and 0 if both hands
         * have equal cards
         */

        if (playerHand.getTotalValue() > dealerHand.getTotalValue()) {
            return 1;
        } else if (playerHand.getTotalValue() < dealerHand.getTotalValue()) {
            return -1;
        } else {
            return 0;
        }
    }

    private int playerTurnInteractive(String input) {
        /*
         * Check input players against "Hit" or "Stand" to control game
         */

        if (input.equalsIgnoreCase("Hit")) {
            return 1;
        } else if (input.equalsIgnoreCase("Stand")) {
            return 0;
        }
        return -1;
    }

    public int game(boolean verbose) {
        /*
         * Start a new game
         */

        reset();
        int res = 0;

        if (verbose) {
            System.out.println(toString()); // Display initial cards
        }

        boolean playerNotDone = playerTurn(false);
        if (!(playerNotDone)) {
            System.out.println(toString());
            res = -1;
            if (verbose) {
                getResult(res);
            }
            return res;
        }
        boolean dealerNotDone = dealerTurn();

        if (verbose) {
            System.out.println(toString()); // Display final cards after picking
        }

        if (!(playerNotDone) && dealerNotDone) {
            res = -1;
        } else if (playerNotDone && !(dealerNotDone)) {
            res = 1;
        } else if (playerNotDone && dealerNotDone) {
            res = checkHigher(playerHand, dealerHand);
        } else {
            res = 0;
        }

        // Give information about the result if verbose is true
        if (verbose) {
            if (res == 0) {
                System.out.println("This game was a tie!\n\n");
            } else if (res == -1) {
                System.out.println("Dealer won this game! \n\n");
            } else if (res == 1) {
                System.out.println("Player won this game \n\n");
            }
        }
        return res;
    }

    public int game(boolean verbose, boolean interactive) {
        /*
         * Start a new BlackJack game (mainly for the user interactivity)
         */

        reset();
        int res = 0;

        if (verbose) {
            System.out.println(toString()); // Display initial cards
        }

        boolean playerNotDone = playerTurn(interactive);
        if (!(playerNotDone)) {
            res = -1;
            return res;
        }
        boolean dealerNotDone = dealerTurn();

        if (verbose) {
            System.out.println(toString()); // Display final cards after picking
        }

        if (!(playerNotDone) && dealerNotDone) {
            res = -1;
        } else if (playerNotDone && !(dealerNotDone)) {
            res = 1;
        } else if (playerNotDone && dealerNotDone) {
            res = checkHigher(playerHand, dealerHand);
        } else {
            res = 0;
        }

        // Give information about the result if verbose is true
        if (verbose) {
            getResult(res);
        }
        return res;
    }

    private static void getResult(int res) {
        /*
         * Displays the result on the terminal
         */

        if (res == 0) {
            System.out.println("This game was a tie!\n\n");
        } else if (res == -1) {
            System.out.println("Dealer won this game! \n\n");
        } else if (res == 1) {
            System.out.println("Player won this game \n\n");
        }
    }

    private void reset() {
        /*
         * Reset the deck and clear the hands of the dealer and player
         */

        if (deck.size() < minShuffle) {
            deck = new Deck();
        }

        playerHand.reset();
        dealerHand.reset();
    }

    public void deal() {
        /*
         * Deal out cards to both the player and the dealer
         */
        playerHand.add(deck.deal()); // deal a card from deck to player
        dealerHand.add(deck.deal()); // deal a card from deck to player
    }

    public boolean playerTurn(boolean interactivity) {
        /*
         * Add cards to player's hand till 16 is reached
         */

        if (!(interactivity)) {
            while (playerHand.getTotalValue() < 16) {
                playerHand.add(deck.deal());

                if (playerHand.getTotalValue() > 21) {
                    return false;
                }
            }
        } else { // Modification to allow player interactivity
            while (true) {
                playerHand.add(deck.deal());
                System.out.println(toString());

                if (playerHand.getTotalValue() > 21) {
                    return false;
                }

                System.out.print("Hit or Stand: ");
                String userIn = uScanner.nextLine();
                if (playerTurnInteractive(userIn) == 0) {
                    break;
                }
            }
        }
        return true;
    }

    public boolean dealerTurn() {
        /*
         * Add cards to dealer's hands till 17 is reached
         */

        while (dealerHand.getTotalValue() < 17) {
            dealerHand.add(deck.deal());

            if (dealerHand.getTotalValue() > 21) {
                return false;
            }
        }
        return true;
    }

    public void setReshuffleCutOff(int newCutOff) {
        /*
         * Changes the cutoff for reshuffle
         */
        minShuffle = newCutOff;
    }

    public int getReshuffleCutOff() {
        /*
         * Return the current minimum value for cutoff
         */
        return minShuffle;
    }

    public String toString() {
        /*
         * Displays the current cards in both the player's and dealer's hand
         */
        return "Player: " + playerHand + "\nDealer: " + dealerHand;
    }
}
