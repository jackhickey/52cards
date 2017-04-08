package models;

import java.util.ArrayList;

/**
 * jackhickey
 **/
public class Deck {
    private ArrayList<Card> deck;

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
