package com.jack.fiftytwo.service;

import org.springframework.boot.context.config.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.jack.fiftytwo.enums.Rank;
import com.jack.fiftytwo.enums.Suit;
import com.jack.fiftytwo.models.Card;
import com.jack.fiftytwo.models.Deck;
import com.jack.fiftytwo.repo.DeckRepo;
import com.jack.fiftytwo.rest.CardController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * jackhickey
 **/
public class DeckService {

    public void addDeck(Deck deck) {
        DeckRepo.setRepoDeck(deck);
    }

    public Deck getDeck() {
        return DeckRepo.getRepoDeck();
    }

    public void initDeck() {
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

        DeckRepo.setRepoDeck(new Deck(freshDeck));
    }

    public Card getCardById(Long ID) throws ResourceNotFoundException {
        final List<Card> resultCards = getDeck().getDeck().stream()
                                           .filter(card -> card.getID() == ID)
                                           .collect(Collectors.toList());

            return resultCards.get(0);

    }

    public boolean removeCardById(Long ID) {
        int deckSizeBeforeRemove = getDeck().getDeck().size();

        final List<Card> remainingCards = getDeck().getDeck().stream()
                .filter(card -> card.getID() != ID)
                .collect(Collectors.toList());

        int deckSizeAfterRemove = remainingCards.size();

        if (deckSizeAfterRemove <  deckSizeBeforeRemove){
            DeckRepo.setRepoDeck(new Deck((List<Card>) remainingCards));
            return true;
        }

        return false;

    }

    public Card addCard(Card card) {
        Deck deck = DeckRepo.getRepoDeck();
        List<Card> cards = deck.getDeck();

        card.add(linkTo(methodOn(CardController.class).card(card.getID())).withSelfRel());

        cards.add(card);

        deck.setDeck(cards);

        return card;
    }
}
