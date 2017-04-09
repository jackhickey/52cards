package com.jack.fiftytwo.models;

import java.util.List;

/**
 * jackhickey
 **/
public class Deck {
    private List<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }
}
