package com.jack.fiftytwo.repo;

import com.jack.fiftytwo.models.Deck;

/**
 * jackhickey
 *
 *
 * if anyone asks, this is a database :D
 **/

public class DeckRepo {
    public static Deck deck;

    public static Deck getRepoDeck() {
        return deck;
    }

    public static void setRepoDeck(Deck deck) {
        DeckRepo.deck = deck;
    }
}
