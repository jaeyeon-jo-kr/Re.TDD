package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import com.jocatelo.Player.Status;

import java.util.ArrayList;

public class BlackJackTest {
    @Test
    public void sendTwoCard() {
        Round round = Round.round().setPlayerNumber(8).shuffle().distribute().start();

        for (Player player : round.players()) {
            assertEquals(player.getCardCount(), 2);
        }

        while (!round.isOver()) {
            for (Player player : round.players()) {
                if (player.status == Status.PLAYING) {
                    player.command(Command.DRAW);
                }
            }
            for (Player player : round.players()) {
                if (player.score() == 21) {
                    assertEquals(Status.BLACKJACK, player.status());
                } else if (player.score() > 21) {
                    assertEquals(Status.BUST, player.status());
                } else {
                    assertEquals(Status.PLAYING, player.status());
                }
            }
            round.endTurn();
        }
        assertTrue(round.bestScore() <= 21);
        for (Player player : round.players()) {
            if (player.score == round.bestScore())
                assertEquals(Status.WIN, player.status());
            else
                assertEquals(Status.LOSE, player.status());
        }

    }

    @Test
    public void cardStatus() {
        CardDeck deck = new CardDeck();
        deck.initialize();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 13; j++) {
                Card card = deck.popCard();

                if (j > 10) {
                    assertEquals(card.value(), 10);
                } else {
                    assertEquals(card.value(), j);
                }

                if (j == 1) {
                    assertEquals(card.nextValue(), 11);
                } else if (j > 10) {
                    assertEquals(card.nextValue(), 10);
                } else {
                    assertEquals(card.nextValue(), j);
                }

                assertEquals(card.type(), Card.Type.values()[i - 1]);
            }

        }
    }

    @Test
    public void checkBlackJackStatus() {
        CardDeck deck = new CardDeck();
        deck.initialize();
        Round mock = Round.round().setPlayerNumber(4).start();

        Player player = mock.players()[0];
        player.draw(Card.diamond(Card.Number.A));
        player.draw(Card.diamond(Card.Number.A));

        Player player2 = mock.players()[1];
        player2.draw(Card.clover(Card.Number.A));
        player2.draw(Card.clover(Card.Number.N8));

        Player player3 = mock.players()[2];
        player3.draw(Card.clover(Card.Number.A));
        player3.draw(Card.clover(Card.Number.N10));

        Player player4 = mock.players()[3];
        player4.draw(Card.clover(Card.Number.N3));
        player4.draw(Card.clover(Card.Number.K));
        player4.draw(Card.clover(Card.Number.J));

        mock.endTurn();
        assertEquals(player.score(), 12);
        assertEquals(player2.score(), 19);
        assertEquals(player3.score(), 21);
        assertEquals(player4.score(), 23);

    }

}