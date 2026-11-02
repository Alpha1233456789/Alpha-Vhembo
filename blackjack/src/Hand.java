/**
 * hand.java
 * Author: Alpha Vhembo
 * Date: 2026- 10- 06
 * Time: 12:42PM
 * Description: Collections : hand
 */

import java.util.ArrayList;

public class Hand {

    private final ArrayList<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    // Adds up the hand, treating Aces as 11 until that would bust
    public int getValue() {
        int total = 0;
        int aces = 0;

        for (Card c : cards) {
            total += c.getValue();
            if (c.getRank().equals("Ace")) aces++;
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }
        return total;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cards.size(); i++) {
            sb.append(cards.get(i));
            if (i < cards.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
}
