package models;

import org.junit.Test;

import enums.Rank;
import enums.Suit;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * jackhickey
 **/
public class CardTest {

    //THE ACE OF SPADES!!! THE ACE OF SPADES!!!
    @Test
    public void toStringTest(){
        String expected = "Ace of Spades";

        Card card = new Card(1L, Rank.ACE, Suit.S);
        assertThat(card.toString()).isEqualTo(expected);
    }

}