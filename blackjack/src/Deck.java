/**
 * deck.java
 * Author: Alpha Vhembo
 * Date: 2026- 10- 06
 * Time: 12:42PM
 * Description: Collections : deck
 */

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        buildDeck();
    }

    // Builds a fresh 52-card deck
    private void buildDeck() {
        cards.clear();

        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = i + 2;                     // 2 .. 14
                if (value > 10 && value < 14) value = 10;  // Jack, Queen, King
                if (value == 14) value = 11;               // Ace
                cards.add(new Card(suit, ranks[i], value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card deal() {
        if (cards.isEmpty()) {   // safety net if the deck runs out
            buildDeck();
            shuffle();
        }
        return cards.remove(cards.size() - 1);
    }
}