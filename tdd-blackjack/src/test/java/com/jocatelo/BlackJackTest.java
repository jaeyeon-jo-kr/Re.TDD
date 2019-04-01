package com.jocatelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
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

        for (Player user : round.players()) {
            Hands hands = user.getHands();
            assertEquals(2, hands.getCardCount() );
        }
    }


    @Test
    public void bustStatus() throws Exception
    {
        Player player = Player.of("Player");
        player.setStatus(Status.PLAYING);

        Hands hands = player.getHands();
        hands.add(Card.clover(10));
        hands.add(Card.clover(10));
        hands.add(Card.clover(3));

        player.updateScore();
        player.updateStatus();

        assertEquals(Status.BUST, player.getStatus());        

    }


    @Test
    
    public void blackJackStatus() throws Exception
    {
        Player player = Player.of("Player");
        player.setCredit(100);
        player.bet(10);

        Hands hands = player.getHands();
        player.setStatus(Status.PLAYING);
        hands.add(Card.clover(1));
        hands.add(Card.clover(10));
        player.updateScore();
        player.updateStatus();

        Dealer dealer = Dealer.of();
        dealer.setStatus(Status.PLAYING);

        assertEquals(21, player.getScore());
        assertEquals(Status.BLACKJACK, player.getStatus());
        assertEquals(15, player.getWinningCredit(dealer));

    }

}