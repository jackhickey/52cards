package enums;

/**
 * jackhickey
 **/
public enum Suit {
    C("Club"),
    D("Diamond"),
    H("Heart"),
    S("Spade");

    private String readableSuit;

    Suit(String suit) {
        readableSuit = suit;
    }

    public String toString() {
        return this.readableSuit;
    }
}
