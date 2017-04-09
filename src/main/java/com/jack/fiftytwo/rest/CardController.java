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

import com.jack.fiftytwo.models.Card;
import com.jack.fiftytwo.models.Deck;
import com.jack.fiftytwo.service.DeckService;

import java.util.Random;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * jackhickey
 **/
@RestController
public class CardController {
    DeckService service = new DeckService();
    boolean needsInit = true;

    @RequestMapping("/deck")
    public HttpEntity<Deck> getDeck(
            @RequestParam(value = "refresh",
                          required = false,
                          defaultValue = "false") boolean refresh) {

        //generate a deck if none exists or refresh is set to true
        if (needsInit || refresh) {
            service.initDeck();
            needsInit = false;
        }

        return new ResponseEntity<>(new Deck(service.getDeck().getDeck()), HttpStatus.OK);
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

    @RequestMapping(value = "/deck", method = RequestMethod.POST)
    public HttpEntity<Card> addCard(@RequestBody Card card) {

        card.setID(getRandomPositive());
        card.setReadableText(card.toString());
        card = service.addCard(card);


        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }


    public Long getRandomPositive() {
        final long LOWER_RANGE = 0;
        final long UPPER_RANGE = 1000000;
        Random random = new Random();


        return LOWER_RANGE +
               (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));

    }


}
