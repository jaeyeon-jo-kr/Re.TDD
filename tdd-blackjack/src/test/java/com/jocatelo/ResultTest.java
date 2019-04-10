package com.jocatelo;

import static org.assertj.core.api.Assertions.assertThat;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.player.PlayerCommand;

import org.junit.Test;

public class ResultTest {

    @Test
    public void winningTest() throws Exception {
        Round round = Round.of().setPlayerNumber(1).setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();

        Player player = round.getPlayer(0);
        Dealer dealer = round.dealer();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(10));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(1));
        
        player.setCommand(PlayerCommand.STAND);
        player.execute();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.BLACKJACK_WIN).isEqualTo(round.getWinStatus(player));
    }

    @Test
    public void losingTest() throws Exception {
        Round round = Round.of().setPlayerNumber(1).setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();

        Player player = round.getPlayer(0);
        Dealer dealer = round.dealer();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(1));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(10));
        
        player.setCommand(PlayerCommand.STAND);
        player.execute();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.LOSE).isEqualTo(round.getWinStatus(player));

    }

    @Test
    public void pushTest() throws Exception {
        Round round = Round.of().setPlayerNumber(1).setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();

        Player player = round.getPlayer(0);
        Dealer dealer = round.dealer();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(1));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(1));
        
        player.setCommand(PlayerCommand.STAND);
        player.execute();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.PUSH).isEqualTo(round.getWinStatus(player));
    }

    @Test
    public void drawTest() throws Exception {
        Round round = Round.of().setPlayerNumber(1).setAutomaticDistribute(false);
        round.initialize();
        round.startTurn();

        Player player = round.getPlayer(0);
        Dealer dealer = round.dealer();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(9));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(9));
        
        player.setCommand(PlayerCommand.STAND);
        player.execute();

        round.updateAllStatus();        
        round.endTurn();
        round.endGame();

        assertThat(WinStatus.DRAW).isEqualTo(round.getWinStatus(player));
    }
}