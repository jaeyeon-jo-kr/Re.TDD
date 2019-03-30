package com.jocatelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Status;

import org.junit.Test;

public class BlackJackTest {
    @Test
    public void distribute2Card() throws Exception {
        Round round = Round.of().setPlayerNumber(8);
        
        round.initialize();
        round.start();        

        for (Playable user : round.users()) {
            assertEquals(2, user.getCardCount() );
        }
    }


    @Test
    public void bustStatus() throws Exception
    {
        Player player = User.createPlayer("Player");
        player.setStatus(Status.PLAYING);

        player.add(Card.clover(10));
        player.add(Card.clover(10));
        player.add(Card.clover(3));

        player.updateScore();
        player.updateStatus();

        assertEquals(Status.BUST, player.status());        

    }


    @Test
    
    public void blackJackStatus() throws Exception
    {
        Player player = User.createPlayer("Player");
        player.setCredit(100);
        player.bet(10);

        player.setStatus(Status.PLAYING);
        player.add(Card.clover(1));
        player.add(Card.clover(10));
        player.updateScore();
        player.updateStatus();

        Dealer dealer = User.createDealer();
        dealer.setStatus(Status.PLAYING);

        assertEquals(21, player.score());
        assertEquals(Status.BLACKJACK, player.status());
        assertEquals(15, player.getWinningCredit(dealer));

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
                    assertEquals(card.specialValue(), 11);
                } else if (j > 10) {
                    assertEquals(card.specialValue(), 10);
                } else {
                    assertEquals(card.specialValue(), j);
                }

                assertEquals(card.type(), Card.Type.values()[i - 1]);
            }

        }
    }

    @Test
    public void checkScore() throws Exception {

        Playable user = User.createPlayer("user1");
        user.add(Card.diamond(1));
        user.add(Card.diamond(1));

        Playable user2 = User.createPlayer("user2");
        user2.add(Card.clover(1));
        user2.add(Card.clover(8));

        Playable user3 = User.createPlayer("user3");
        user3.add(Card.clover(1));
        user3.add(Card.clover(10));

        Playable user4 = User.createPlayer("user4");
        user4.add(Card.clover(3));
        user4.add(Card.clover(10));
        user4.add(Card.clover(10));

        user.updateScore();
        user2.updateScore();
        user3.updateScore();
        user4.updateScore();
        assertEquals(12, user.score());
        assertEquals(19, user2.score());
        assertEquals(21, user3.score());

    }

}