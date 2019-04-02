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
        Round round = Round.of().setPlayerNumber(1);
        round.initialize();
        round.start();

        Player player = round.getPlayer(0);
        Dealer dealer = round.dealer();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(10));

        player.addCard(Card.clover(10));
        player.addCard(Card.clover(1));
        
        round.setCommand(player, PlayerCommand.STAND);
        round.endTurn();
        round.endGame();

        assertThat(player.getStatus()).isEqualTo(PlayerStatus.WIN);

        
        
    }
}