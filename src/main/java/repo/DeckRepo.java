package repo;

import org.springframework.boot.context.config.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import models.Card;
import models.Deck;


/**
 * jackhickey
 **/
public class DeckRepo {

    public Card getCardById(Deck deck, Long ID) throws ResourceNotFoundException {
        final List<Card> resultCards = deck.getDeck().stream()
                                 .filter(card -> card.getID() == ID)
                                 .collect(Collectors.toList());

        try{
           return resultCards.get(0);
        } catch (Exception e) {
            return new Card();
        }

    }
}
