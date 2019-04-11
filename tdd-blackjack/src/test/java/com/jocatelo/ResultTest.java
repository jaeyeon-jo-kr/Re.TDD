package com.jocatelo;

import static org.assertj.core.api.Assertions.assertThat;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.player.PlayerCommand;

import org.junit.Before;
import org.junit.Test;

public class ResultTest {

    Round round;
    Dealer dealer;
    Player player;
    

    @Before
    public void BeforeTest() throws Exception
    {
        round = Round.of().setPlayerNumber(1).setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();
        Participants participants = round.getParticipants();
        PlayerGroup players = participants.getPlayers();

        player = players.get(0);
        dealer = participants.getDealer();

    }

    @Test
    public void winningTest() throws Exception {
        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(10));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(1));
        
        player.setCommand(PlayerCommand.STAND);
        round.executeAll();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.BLACKJACK_WIN).isEqualTo(player.getWinStatus());
    }

    @Test
    public void losingTest() throws Exception {
        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(1));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(10));
        
        player.setCommand(PlayerCommand.STAND);
        round.executeAll();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.LOSE).isEqualTo(player.getWinStatus());

    }

    @Test
    public void pushTest() throws Exception {        
        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(1));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(1));
        
        player.setCommand(PlayerCommand.STAND);
        round.executeAll();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.PUSH).isEqualTo(player.getWinStatus());
    }

    @Test
    public void drawTest() throws Exception {
        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(9));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(9));
        
        player.setCommand(PlayerCommand.STAND);
        round.executeAll();


        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.DRAW).isEqualTo(player.getWinStatus());
    }
}