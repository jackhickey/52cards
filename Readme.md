This is a basic deck/card management app made with Spring Boot.

There's no backend, just a static "DeckRepo".

To run it you need maven and java installed.

`mvn spring-boot:run`

GET /deck will generate a deck. The "_link" object in the Deck and Card objects
will link to to other functionality.

You can...

* Add cards, just provide a suit and a rank). Duplicate cards are allowed
   * POST - {rank: 'ACE', suit: 'S'} to add an ace of spades to the deck

* Remove card
  * DELETE - /deck/{ID}

* Shuffle in place and riffle. (See response object from "deck")

