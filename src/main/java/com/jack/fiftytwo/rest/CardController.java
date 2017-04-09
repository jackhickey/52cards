package com.jack.fiftytwo.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jack.fiftytwo.enums.Shuffle;
import com.jack.fiftytwo.models.Card;
import com.jack.fiftytwo.models.Deck;
import com.jack.fiftytwo.service.DeckService;

import java.util.Random;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * jackhickey
 **/
@RestController
public class CardController {
    DeckService service = new DeckService();
    boolean needsInit = true;

    @RequestMapping(value = "/deck", method = RequestMethod.GET)
    public HttpEntity<Deck> getDeck(
            @RequestParam(value = "refresh",
                          required = false,
                          defaultValue = "false") boolean refresh) {

        //generate a deck if none exists or refresh is set to true
        if (needsInit || refresh) {
            service.initDeck();
            needsInit = false;
        }

        Deck deck = new Deck(service.getDeck().getDeck());

        deck.add(linkTo(methodOn(CardController.class).getDeck(false)).withSelfRel());
        deck.add(linkTo(methodOn(CardController.class).shuffleDeck(Shuffle.INPLACE)).withSelfRel());
        deck.add(linkTo(methodOn(CardController.class).shuffleDeck(Shuffle.RIFFLE)).withSelfRel());

        return new ResponseEntity<>(deck, HttpStatus.OK);
    }

    @RequestMapping(value = "/deck", method = RequestMethod.POST)
    public HttpEntity<Card> addCard(@RequestBody Card card) {

        card.setID(getRandomPositive());
        card.setReadableText(card.toString());
        card = service.addCard(card);


        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/deck", method = RequestMethod.PUT)
    public HttpEntity<Deck> shuffleDeck(
            @RequestParam(value = "shuffle",
                          required = false)
                    Shuffle shuffle){
        if(shuffle == Shuffle.INPLACE) {
            return new ResponseEntity<>(service.inPlaceShuffle(), HttpStatus.OK);
        }

            return new ResponseEntity<>(service.riffleShuffle(), HttpStatus.OK);

    }

    @RequestMapping(value = "/deck/{ID}", method = RequestMethod.GET)
    public HttpEntity<Card> card(
            @PathVariable(value = "ID")
                    Long id) {
        Card card;

        try {
            card = service.getCardById(id);
            return new ResponseEntity<>(card, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/deck/{ID}", method = RequestMethod.DELETE)
    public HttpEntity<HttpStatus> removeCard(
            @PathVariable(value = "ID")
                    Long id) {
        boolean removed = service.removeCardById(id);

        if (removed) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    public Long getRandomPositive() {
        final long LOWER_RANGE = 0;
        final long UPPER_RANGE = 1000000;
        Random random = new Random();


        return LOWER_RANGE +
               (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));

    }


}
