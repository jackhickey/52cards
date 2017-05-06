package com.jack.fiftytwo.enums;

/**
 * jackhickey
 **/
public enum Rank {
    ACE("Ace"),
    TWO("Two"),
    THREE("Three"),
    FOUR("Four"),
    FIVE("Five"),
    SIX("Six"),
    SEVEN("Seven"),
    EIGHT("Eight"),
    NINE("Nine"),
    TEN("Ten"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King");

    private String readableRank;

    Rank(String rank) {
        readableRank = rank;
    }

    public String toString() {
        return this.readableRank;
    }
}
