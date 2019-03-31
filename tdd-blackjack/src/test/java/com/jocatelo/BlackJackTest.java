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

}