package com.jocatelo;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.jocatelo.character.Hands;
import com.jocatelo.character.Player;
import com.jocatelo.rule.player.PlayingStatus;

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
        player.setStatus(PlayingStatus.PLAYING);

        Hands hands = player.getHands();
        hands.add(Card.clover(10));
        hands.add(Card.clover(10));
        hands.add(Card.clover(3));
        
        player.updateStatus();

        assertThat("Player must be bust.", player.getStatus(),equalTo(PlayingStatus.BUST));

    }


    @Test
    
    public void blackJackStatus() throws Exception
    {
        Round round = Round.of().setPlayerNumber(1)
            .setAutomaticDistribute(false).setPlayerGeneration(true);
        round.initialize();
        round.startTurn();

        Player player = round.players().get(0);
        player.setCredit(100);
        player.bet(10);

        Hands hands = player.getHands();
        player.setStatus(PlayingStatus.PLAYING);
        hands.add(Card.clover(1));
        hands.add(Card.clover(10));
        
        player.updateStatus();

        round.endTurn();

        assertThat("Player의 점수는 21이어야 한다.", player.getScore(), equalTo(21));
        assertThat("Player의 상태는 21이어야 한다.", player.getStatus(), equalTo(PlayingStatus.BLACKJACK));        
        round.endGame();
        assertThat("Player가 되돌려받는 돈은 25이어야 한다.", round.getWinningCredit(player), equalTo(25));

    }

}