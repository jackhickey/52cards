package com.jack.fiftytwo.repo;

import com.jack.fiftytwo.models.Deck;

/**
 * jackhickey
 *
 *
 * This could be some sort of database...
 **/

public class DeckRepo {
    public static Deck deck;

    public static Deck getDeck() {
        return deck;
    }

    public static void setDeck(Deck deck) {
        DeckRepo.deck = deck;
    }
}
