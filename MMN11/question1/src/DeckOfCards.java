/*
 * Advanced Java - MMN11 - Question 1
 * Ronen Lapushner, Nov. 2019
 */

import java.util.*;

/* A class that represents a deck of cards. */
public class DeckOfCards {
    // Properties
    private ArrayList<Card> deck;
    private Random randObj;
    
    // Constants
    private final int NUMBER_OF_CARDS = 52;

    // Cnstr.
    public DeckOfCards(boolean populate) {
        // Initialize random
        randObj = new Random();
        
        // Declare a new deck array with maximum possible capacity of cards.
        deck = new ArrayList<Card>(NUMBER_OF_CARDS);

        // Populate deck with card objects, if required.
        if (populate) {
            for (int count = 0; count < NUMBER_OF_CARDS; count++)
                deck.add(new Card(Card.FACES[count % 13], Card.SUITS[count / 13]));

            // Shuffle to simulate the creation of a deck of cards
            shuffle();
        }
    }

    /** Shuffles the deck */
    public void shuffle() {
        for (int first = 0; first < deck.size(); first++) {
            // Get a random card that we will put instead of this one.
            int second = randObj.nextInt(deck.size());

            // Swap the cards
            Card temp = deck.get(first);
            deck.set(first, deck.get(second));
            deck.set(second, temp);
        }
    }

    /** Deals the topmost card, removing it from the deck and returning it. */
    public Card dealCardTop() {
        if (deck.size() != 0) {
            // "Get" the card, meaning it will no longer be in the deck when we're done.
            return deck.remove(0);
        }
        else
            return null; // All cards were dealt
    }
    
    /** Deals the bottom-most card, removing it form the deck and returning it. */
    public Card dealCardBottom() {
        if (deck.size() != 0) {
            // Remove the absolute last card in the deck
            return deck.remove(deck.size() - 1);
        }
        else
            return null; // All cards were dealt.
    }

    /** Adds a card to the bottom of the deck. */
    public void addCardBottom(Card card) {
        // The last card should be the card we were provided with
        deck.add(card);
    }

    /** Adds a card to the top of the deck. */
    public void addCardTop(Card card) {
        // The first card is the card we were provided with
        deck.add(0, card);
    }
    
    /** Adds the cards of the other deck to this one. */
    public void addDeckTop(DeckOfCards other) {
        while (other.size() != 0) {
            Card drawn = other.dealCardTop();
            this.addCardTop(drawn);
        }
    }

    /** Returns the size of the deck. */
    public int size() {
        return deck.size();
    }
}
