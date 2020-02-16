/*
 * Advanced Java - MMN11 - Question 1
 * Ronen Lapushner, Nov. 2019
 */

import javax.swing.*;

/**
 * Represents the game state, and includes the main function.
 */
public class Game {

    // Properties
    private DeckOfCards player1Deck, player2Deck;
    private int victor;

    // Constants
    private final int WAR_CARDS_DRAWN = 3;
    private final int CARDS_TO_DEAL = 26;
    private final int VICTOR_PLAYER_1 = 1;
    private final int VICTOR_PLAYER_2 = 2;

    public Game() {
        // Initialize the first deck.
        // The second player will get half the cards from the first deck,
        // which is why we only initialize the first deck.
        player1Deck = new DeckOfCards(true);
        player2Deck = new DeckOfCards(false);

        // Initialize the second deck by dealing exactly 26 cards from the first one.
        for (int i = 0; i < CARDS_TO_DEAL; i++) {
            Card dealt = player1Deck.dealCardTop();
            player2Deck.addCardTop(dealt);
        }

        // Initialize victor. By default it's zero, which means that nobody is winning.
        victor = 0;
    }

    public void gameLoop() {
        // Step 1: Deal two decks. We did that when we initialized the game.

        // Actual game.
        while (true) {
            // Two players show their top card.
            Card card1 = player1Deck.dealCardTop();
            Card card2 = player2Deck.dealCardTop();

            // Check if a player has run out of cards, thus losing.
            if (card1 == null) {
                victor = VICTOR_PLAYER_2;
                break;
            } else if (card2 == null) {
                victor = VICTOR_PLAYER_1;
                break;
            }

            // Show cards.
            if (showMessageOrQuit("Player 1 card = " + card1.toString()
                    + "\nPlayer 2 card = " + card2.toString())) {
                return;
            }

            // Does one player have a higher card?
            if (card1.isBiggerThan(card2)) {
                player1Deck.addCardBottom(card2);
                player1Deck.addCardBottom(card1);

                if (showMessageOrQuit("Player 1 wins, gets both cards!")) {
                    return;
                }
            } else if (card1.isLessThan(card2)) {
                player2Deck.addCardBottom(card1);
                player2Deck.addCardBottom(card2);

                if (showMessageOrQuit("Player 2 wins, gets both cards!")) {
                    return;
                }
            } else {
                // War mode, since both cards are equal.
                DeckOfCards warDeck = new DeckOfCards(false);
                String warMessage = "WAR!\n";
                boolean victoryDeclared = false;

                // Draw cards from both decks.
                for (int i = 0; i < WAR_CARDS_DRAWN; i++) {
                    // Draw two cards from the player's decks.
                    Card c1 = player1Deck.dealCardTop(), c2 = player2Deck.dealCardTop();

                    // Check if a player has run out of cards, thus losing.
                    if (c1 == null) {
                        victor = VICTOR_PLAYER_2;

                        victoryDeclared = true;
                        break;
                    } else if (c2 == null) {
                        victor = VICTOR_PLAYER_1;

                        victoryDeclared = true;
                        break;
                    }

                    // Put cards in the special war deck
                    warDeck.addCardTop(c1);
                    warDeck.addCardTop(c2);
                }

                // If a player hasn't lost all of his cards (thus losing the game),
                // keep checking.
                if (!victoryDeclared) {
                    // For convinience store the last cards.
                    // We draw in reverse order, since the FIRST card in the war deck
                    // is the SECOND PLAYER'S, and the SECOND card is the FIRST PLAYER'S.
                    Card p2LastCard = warDeck.dealCardTop();
                    Card p1LastCard = warDeck.dealCardTop();

                    // Display the third cards the players had
                    warMessage += "\nPlayer 1's third card is:\n" + p1LastCard.toString();
                    warMessage += "\nPlayer 2's third card is:\n" + p2LastCard.toString();

                    // We can now shuffle the war deck. We do this to decrease the maximum
                    // amount of turns we would have, since games can be quite long.
                    warDeck.shuffle();

                    // Check the third cards. If one is greater, the player with that card
                    // gets the rest of the cards, and we're out of war mode.
                    // If equal, continue the war.
                    if (p1LastCard.isBiggerThan(p2LastCard)) {
                        warMessage += "\n\nPlayer 1 wins, gets all cards!";

                        // Put all cards in player 1's deck.
                        player1Deck.addDeckTop(warDeck);
                        player1Deck.addCardBottom(p1LastCard);
                        player1Deck.addCardBottom(p2LastCard);
                    } else if (p2LastCard.isBiggerThan(p1LastCard)) {
                        warMessage += "\n\nPlayer 2 wins, gets all cards!";

                        // Put all cards in player 2's deck.
                        player2Deck.addDeckTop(warDeck);
                        player2Deck.addCardBottom(p1LastCard);
                        player2Deck.addCardBottom(p2LastCard);
                    } else {
                        // Still at war!
                        warMessage += "\n\nBoth third cards equal!";

                        // Put the last cards back in the deck, so that in the next turn
                        // we would repeat the war.
                        player1Deck.addCardTop(p1LastCard);
                        player2Deck.addCardTop(p2LastCard);
                    }

                    // Show game state
                    if (showMessageOrQuit(warMessage)) {
                        return;
                    }
                }
            }
        }

        // Game ended, check the victor, and print a nice message.
        String victoryMessage = "Game ended!\n";
        if (victor == VICTOR_PLAYER_1) {
            victoryMessage += "\nPlayer 1 wins!\n";
        } else if (victor == VICTOR_PLAYER_2) {
            victoryMessage += "\nPlayer 2 wins!\n";
        } else {
            victoryMessage += "\nIt's a tie!\n";
        }

        // Also attach the deck sizes and turn count for the message.
        victoryMessage += "\nDeck sizes: " + player1Deck.size() + " " + player2Deck.size();

        // Print message and finish the game.
        JOptionPane.showMessageDialog(null, victoryMessage);
    }

    /**
     * Shows a message in a confirmation dialog. Returns true if the user has
     * chosen to exit the application. Otherwise returns false.
     */
    private boolean showMessageOrQuit(String message) {
        int ans = JOptionPane.showConfirmDialog(null, message);
        return (ans != JOptionPane.OK_OPTION);
    }
}
