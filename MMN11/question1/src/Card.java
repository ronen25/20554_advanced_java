/*
 * Advanced Java - MMN11 - Question 1
 * Ronen Lapushner, Nov. 2019
 */

/* A class that represents a card in the game. */
public class Card {
    // Allowed faces ans suits.
    public static final String[] FACES = { "Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Jack", "Queen", "King" };
    public static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" };

    // Properties
    private final String face;
    private final String suit;

    /** Constructs a new card with a face and a suit. */
    public Card(String cardFace, String cardSuit) {
        this.face = cardFace;
        this.suit = cardSuit;
    }

    /**
     * Converts the card to a number, based on some rules. It can later be used for comparisons.
     * For jack, returns 10.
     * For queen, returns 11.
     * For king, returns 13.
     * For ace, returns 1.
     */
    public int faceToInt() {
        for (int i = 0; i < FACES.length; i++) {
            if (face.equals(FACES[i]))
                return i + 1; // To compensate for the cards starting at 1.
        }

        return -1; // Invalid face value.
    }
    
    /**
     * Returns true if THE FACE of this card is bigger than other's.
     * Otherwise returns false.
     */
    public boolean isBiggerThan(Card other) {
        return this.faceToInt() > other.faceToInt();
    }
    
    /**
     * Returns true if THE FACE of this card is smaller than other's.
     * Otherwise returns false.
     */
    public boolean isLessThan(Card other) {
        return this.faceToInt() < other.faceToInt();
    }

    /** Returns a representation of this Card as a string. */
    public String toString() {
        return face + " of " + suit;
    }
}
