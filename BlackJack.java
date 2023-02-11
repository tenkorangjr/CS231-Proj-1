public class BlackJack {

    // For the deck of cards
    Deck deck;
    // For the hands to play the game
    Hand playerHand;
    Hand dealerHand;

    // For reshuffling
    int minShuffle;

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();

        blackJack.game(true);
    }

    public int checkHigher(Hand player, Hand dealer) {
        // returns 1 if player has higher total, 0 if player and dealer have
        // equal cards and -1 if dealer has a higher total

        if (player.getTotalValue() > dealer.getTotalValue()) {
            return -1;
        } else if (player.getTotalValue() < dealer.getTotalValue()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int game(boolean verbose) {
        // Start a game of BlackJack

        reset();
        int res = 0;

        if (verbose) {
            System.out.println(toString()); // Display initial cards
        }

        boolean playerNotDone = playerTurn();
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

    public BlackJack(int reshuffleCutOff) {
        // Constructor with the value to reshuffle cards
        minShuffle = reshuffleCutOff;
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    public BlackJack() {
        // Constructor with default value for reshuffle
        minShuffle = 26;
        deck = new Deck();
        playerHand = new Hand();
        dealerHand = new Hand();
    }

    public void reset() {
        // Reset the deck and clear the hands of the dealer and player

        if (deck.size() < minShuffle) {
            deck = new Deck();
        }

        playerHand.reset();
        dealerHand.reset();
    }

    public void deal() {
        playerHand.add(deck.deal()); // deal a card from deck to player
        dealerHand.add(deck.deal()); // deal a card from deck to player
    }

    public boolean playerTurn() {
        // Add cards to player till 16 is reached
        while (playerHand.getTotalValue() < 16) {
            playerHand.add(deck.deal());

            if (playerHand.getTotalValue() > 21) {
                return false;
            }
        }
        return true;
    }

    public boolean dealerTurn() {
        // Add cards to dealer till 17 is reached

        while (dealerHand.getTotalValue() < 17) {
            dealerHand.add(deck.deal());

            if (dealerHand.getTotalValue() > 21) {
                return false;
            }
        }
        return true;
    }

    public void setReshuffleCutOff(int newCutOff) {
        // Change the cutoff for reshuffle
        minShuffle = newCutOff;
    }

    public int getReshuffleCutOff() {
        // Return the current minimum value for cutoff
        return minShuffle;
    }

    public String toString() {
        // Displays current cards and values
        return "Player: " + playerHand + "\nDealer: " + dealerHand;
    }
}
