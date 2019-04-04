package com.jocatelo;

import static org.junit.Assert.assertEquals;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Player;
import com.jocatelo.rule.PlayerStatus;

import org.junit.Test;

public class BlackJackTest {
    @Test
    public void distribute2Card() throws Exception {
        Round round = Round.of().setPlayerNumber(8);
        
        round.initialize();
        round.startTurn();        

        for (Player user : round.players()) {
            Hands hands = user.getHands();
            assertEquals(2, hands.getCardCount() );
        }
    }


    @Test
    public void bustStatus() throws Exception
    {
        Player player = Player.of("Player");
        player.setStatus(PlayerStatus.PLAYING);

        Hands hands = player.getHands();
        hands.add(Card.clover(10));
        hands.add(Card.clover(10));
        hands.add(Card.clover(3));

        player.updateScore();
        player.updateStatus();

        assertEquals(PlayerStatus.BUST, player.getStatus());        

    }


    @Test
    
    public void blackJackStatus() throws Exception
    {
        Player player = Player.of("Player");
        player.setCredit(100);
        player.bet(10);

        Hands hands = player.getHands();
        player.setStatus(PlayerStatus.PLAYING);
        hands.add(Card.clover(1));
        hands.add(Card.clover(10));
        player.updateScore();
        player.updateStatus();

        Dealer dealer = Dealer.of();
        
        player.setDealer(dealer);
    

        assertEquals(21, player.getScore());
        assertEquals(PlayerStatus.BLACKJACK, player.getStatus());
        player.finalizeStatus();
        assertEquals(25, player.getWinningCredit());

    }

}