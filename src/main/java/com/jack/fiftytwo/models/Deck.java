package com.jack.fiftytwo.models;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * jackhickey
 **/
public class Deck extends ResourceSupport{
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
