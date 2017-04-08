package models;

import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.ResourceSupport;

import enums.Rank;
import enums.Suit;

/**
 * jackhickey
 **/
public class Card extends ResourceSupport{

    private Long ID;
    private Rank rank;
    private Suit suit;
    private String readableText;

    public Card(Long ID, Rank rank, Suit suit) {
        this.ID = ID;
        this.rank = rank;
        this.suit = suit;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String toString(){
        return getRank().toString() + " of " + getSuit().toString() + "s";
    }
}
