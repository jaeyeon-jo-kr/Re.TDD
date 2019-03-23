package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import com.jocatelo.User.Status;

import java.util.ArrayList;

public class BlackJackTest {
    @Test
    public void playing1Round() {
        Round round = Round.round().setPlayerNumber(8).shuffle().distribute().start();

        for (Playable user : round.users()) {
            assertEquals(2, user.getCardCount() );
        }

        while (!round.isOver()) {
            for (Playable user : round.users()) {
                if (user.status() == Status.PLAYING && user.score() < 21) {
                    user.command(Command.DRAW);
                } else {
                    user.command(Command.FOLD);
                }
            }
            for (Playable user : round.users()) {
                if (user.score() == 21) {
                    assertEquals(Status.BLACKJACK, user.status());
                } else if (user.score() > 21) {
                    assertEquals(Status.BUST, user.status());
                } else {
                    assertEquals(Status.PLAYING, user.status());
                }
            }
            round.endTurn();
        }
        assertTrue(round.bestScore() <= 21);
        Dealer dealer = round.dealer();

        assertNotEquals(Status.WIN, dealer.status());
        assertNotEquals(Status.LOSE, dealer.status());

        for (Playable player : round.players()) {

            if(player.status() != Status.FOLD){
                if(player.score() > 21)
                {
                    assertEquals(Status.LOSE, player.status());
                }else if(dealer.score() > 21 && player.score() <= 21)
                {
                    assertEquals(Status.WIN, player.status());
                }else if(dealer.score() == 21 && player.score() == 21)
                {
                    assertEquals(Status.DRAW, player.status());                            
                }else if(dealer.score() > player.score() ){
                    assertEquals(Status.LOSE, player.status());
                }else if(dealer.score() < player.score()){
                    assertEquals(Status.WIN, player.status());
                }else if(dealer.score() == player.score()){
                    assertEquals(Status.DRAW, player.status());
                }
            }

            
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

        Playable user = mock.players()[0];
        user.draw(Card.diamond(Card.Number.A));
        user.draw(Card.diamond(Card.Number.A));

        Playable user2 = mock.players()[1];
        user2.draw(Card.clover(Card.Number.A));
        user2.draw(Card.clover(Card.Number.N8));

        Playable user3 = mock.players()[2];
        user3.draw(Card.clover(Card.Number.A));
        user3.draw(Card.clover(Card.Number.N10));

        Playable user4 = mock.players()[3];
        user4.draw(Card.clover(Card.Number.N3));
        user4.draw(Card.clover(Card.Number.K));
        user4.draw(Card.clover(Card.Number.J));

        mock.endTurn();
        assertEquals(12, user.score());
        assertEquals(19, user2.score());
        assertEquals(21, user3.score());
        assertEquals(23, user4.score());

    }

}