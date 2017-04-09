package com.jack.fiftytwo.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jack.fiftytwo.models.Card;
import com.jack.fiftytwo.models.Deck;
import com.jack.fiftytwo.service.DeckService;

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

        //generate a deck if none exists
        if (needsInit || refresh) {
            service.initDeck();
        }

        return new ResponseEntity<>(new Deck(service.getDeck().getDeck()), HttpStatus.OK);
    }

    @RequestMapping(value = "/deck/{ID}", method = RequestMethod.GET)
    public HttpEntity<Card> card(
            @PathVariable(value = "ID",
                          required = true)
                    Long id) {
        Card card = service.getCardById(id);

        if (card.getID() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card, HttpStatus.OK);
    }



}
