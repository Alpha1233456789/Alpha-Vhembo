/**
 * Main.java
 * Author: Alpha Vhembo
 * Date: 2026- 10- 06
 * Time: 12:42PM
 * Description: Collections : Main
 */


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("================================");
        System.out.println("           BLACKJACK");
        System.out.println("================================");

        while (playAgain) {
            Deck deck = new Deck();
            deck.shuffle();

            Hand player = new Hand();
            Hand dealer = new Hand();

            // Deal two cards each
            player.add(deck.deal());
            dealer.add(deck.deal());
            player.add(deck.deal());
            dealer.add(deck.deal());

            System.out.println("\n--- New Round ---");
            System.out.println("Dealer shows: " + dealer.getCards().get(0));
            System.out.println("Your hand: " + player + " (" + player.getValue() + ")");

            // ---- Player's turn ----
            boolean playerBusted = false;

            while (true) {
                System.out.print("\nHit or stand? (h/s): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("h")) {
                    Card c = deck.deal();
                    player.add(c);
                    System.out.println("You drew: " + c);
                    System.out.println("Your hand: " + player + " (" + player.getValue() + ")");

                    if (player.isBust()) {
                        System.out.println("Bust! You went over 21.");
                        playerBusted = true;
                        break;
                    }
                } else if (choice.equals("s")) {
                    break;
                } else {
                    System.out.println("Please enter 'h' or 's'.");
                }
            }

            // ---- Dealer's turn ----
            if (!playerBusted) {
                System.out.println("\nDealer reveals: " + dealer + " (" + dealer.getValue() + ")");

                while (dealer.getValue() < 17) {
                    Card c = deck.deal();
                    dealer.add(c);
                    System.out.println("Dealer draws: " + c);
                    System.out.println("Dealer hand: " + dealer + " (" + dealer.getValue() + ")");
                }

                if (dealer.isBust()) {
                    System.out.println("Dealer busts!");
                }
            }

            // ---- Results ----
            System.out.println("\n--- Result ---");

            if (playerBusted) {
                System.out.println("Dealer wins.");
            } else if (dealer.isBust()) {
                System.out.println("You win!");
            } else if (player.getValue() > dealer.getValue()) {
                System.out.println("You win!");
            } else if (player.getValue() < dealer.getValue()) {
                System.out.println("Dealer wins.");
            } else {
                System.out.println("Push (tie).");
            }

            System.out.print("\nPlay again? (y/n): ");
            playAgain = scanner.nextLine().trim().toLowerCase().equals("y");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}