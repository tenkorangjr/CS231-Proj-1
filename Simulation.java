public class Simulation {

    public static void main(String[] args) {
        BlackJack blackJack = new BlackJack();

        int playerWins = 0;
        int dealerWins = 0;
        int push = 0;

        int winner;
        for (int i = 0; i < 1000; i++) {
            winner = blackJack.game(false);
            if (winner == 1) {
                playerWins++;
            } else if (winner == -1) {
                dealerWins++;
            } else {
                push++;
            }
        }

        System.out.println("Player won: " + playerWins + " games (" + (playerWins / 10) + "% of the total games)");
        System.out.println("Dealer won: " + dealerWins + " games (" + (dealerWins / 10) + "% of the total games)");
        System.out.println("Dealer and Player tied in " + push + " games (" + (push / 10) + "% of the total games)");
    }
}
