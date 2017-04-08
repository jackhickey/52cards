package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import enums.Rank;
import enums.Suit;
import models.Card;
import models.Deck;
import repo.DeckRepo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * jackhickey
 **/
@RestController
public class CardController {
    ArrayList<Card> deck = new ArrayList<>();
    DeckRepo repo = new DeckRepo();


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

    @RequestMapping(value = "/deck/{ID}", method = RequestMethod.GET)
    public HttpEntity<Card> card(
            @PathVariable(value = "ID",
                          required = true)
                    Long id) {
        Deck currentDeck = new Deck(deck);

        Card card = repo.getCardById(currentDeck, id);

        if (card.getID() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card, HttpStatus.OK);
    }

//populates deck with a full ordered deck of cards
    private ArrayList<Card> freshDeck() {
        ArrayList<Card> freshDeck = new ArrayList<>();

        Long i = 0L;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(i, rank, suit);
                //adds hateoas self ref links
                card.add(linkTo(methodOn(CardController.class).card(card.getID())).withSelfRel());
                //increment index
                i = i + 1;
                freshDeck.add(card);
            }
        }

        return freshDeck;
    }


}
