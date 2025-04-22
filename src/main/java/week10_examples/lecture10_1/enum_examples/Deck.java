package week10_examples.lecture10_1.enum_examples;

import java.util.ArrayList;

public class Deck {
    public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
    public enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

    private ArrayList<Card> deck;
    public static class Card {
        private Rank rank;
        private Suit suit;
        private Card(Rank rank, Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }
    }

}
