package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import enums.Rank;
import enums.Suit;
import models.Card;
import models.Deck;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * jackhickey
 **/
@RestController
public class CardController {
    ArrayList<Card> deck = new ArrayList<>();


    @RequestMapping("/deck")
    public HttpEntity<Deck> getDeck(
            @RequestParam(value = "refresh",
                          required = false,
                          defaultValue = "false") boolean refresh) {
        //generate a deck if none exists
        if (deck.isEmpty() || refresh) {
            deck = freshDeck();
        }

        return new ResponseEntity<>(new Deck(deck), HttpStatus.OK);
    }

    @RequestMapping("/deck/{ID}")
    public HttpEntity<Card> card(
            @PathVariable(value = "ID",
                          required = true)
            Long id){


        return null;
    }

    private ArrayList<Card> freshDeck() {
        ArrayList<Card> freshDeck = new ArrayList<>();

        Long i = 0L;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                i = i + 1;
                Card card = new Card(i, rank, suit);
                //adds hateoas self ref links
                card.add(linkTo(methodOn(CardController.class).card(card.getID())).withSelfRel());
                freshDeck.add(card);
            }
        }

        return freshDeck;
    }


}
