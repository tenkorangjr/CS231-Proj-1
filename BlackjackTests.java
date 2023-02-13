/*
 * Author: Michael Tenkorang
 * Black Jack Game
 * Date: 02/11/2023
 * CS 231
 * Section B
 * BlackjackTests.java
 */

public class BlackjackTests {

    public static void blackjackTests() {

        // case 1: testing Blackjack() and Blackjack(i)
        {
            // set up
            BlackJack bj1 = new BlackJack();
            BlackJack bj2 = new BlackJack(5);

            // verify
            System.out.println(bj1.getReshuffleCutOff() + "== 26");
            System.out.println(bj2.getReshuffleCutOff() + "== 5");

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
            assert bj2 != null : "Error in Blackjack::Blackjack()";
        }

        // case 2: testing setReshuffleCutOff() and getReshuffleCutOff(i)
        {
            // set up
            BlackJack bj1 = new BlackJack();
            bj1.setReshuffleCutOff(10);

            // verify
            System.out.println(bj1.getReshuffleCutOff() + " == 10");

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        // case 3: testing reset()
        {
            // set up
            BlackJack bj1 = new BlackJack();
            bj1.deal();

            // verify
            System.out.println(bj1.deck.size() + " == 50");

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
        }

        System.out.println("*** Done testing Blackjack! ***\n");
    }

    public static void main(String[] args) {

        blackjackTests();
    }
}