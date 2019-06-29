package com.jocatelo;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import com.jocatelo.card.Card;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.player.status.PlayerStatus;

import org.junit.Test;

public class BlackJackTest {

    @Test
    public void distribute2Card() {
        Round round = Round.of();
        
        round.initialize();
        round.startTurn();

        Participants participants = round.getParticipants();
        PlayerGroup players = participants.getPlayers();

        for (Player user : players.getPlayers()) {
            Hands hands = user.getHands();
            assertEquals(2, hands.getCardCount() );
        }
    }


    @Test
    public void bustStatus() {
        Player player = Player.of("Player");
        player.setStatus(PlayerStatus.PLAYING);

        Hands hands = player.getHands();
        hands.add(Card.clover(10));
        hands.add(Card.clover(10));
        hands.add(Card.clover(3));
        
        player.updateStatus();

        assertThat("Player must be bust.", player.getStatus(),equalTo(PlayerStatus.BUST));

    }


    @Test
    
    public void blackJackStatus() {
        Round round = Round.of()
            .setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();

        Participants participants = round.getParticipants();
        PlayerGroup players = participants.getPlayers();
        Player player = players.get(0);
        player.setCredit(100);
        player.bet(10);

        Hands hands = player.getHands();
        player.setStatus(PlayerStatus.PLAYING);
        hands.add(Card.clover(1));
        hands.add(Card.clover(10));
        
        player.updateStatus();

        round.endTurn();
        

        assertThat("Player의 점수는 21이어야 한다.", player.getScore(), equalTo(21));
        assertThat("Player의 상태는 21이어야 한다.", player.getStatus(), equalTo(PlayerStatus.BLACKJACK));        
        round.endGame();
        assertThat("Player가 되돌려받는 돈은 25이어야 한다.", player.getWinningCredit(), equalTo(25));
    }

}