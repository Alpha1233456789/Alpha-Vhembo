/**
 * card.java
 * Author: Alpha Vhembo
 * Date: 2026- 10- 06
 * Time: 12:42PM
 * Description: Collections : card
 */

public class Card {

    private final String suit;
    private final String rank;
    private final int value;   // 2–10, face cards = 10, Ace = 11

    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public String getSuit() { return suit; }
    public String getRank() { return rank; }
    public int getValue()    { return value; }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
