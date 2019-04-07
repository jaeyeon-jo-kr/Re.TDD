package com.jocatelo;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.PlayerStatus;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

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

        assertThat(PlayerStatus.WIN).isEqualTo(player.getStatus());

        
        
    }
}