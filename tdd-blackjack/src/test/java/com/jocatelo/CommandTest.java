package com.jocatelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.PlayerCommand;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

public class CommandTest {
    
    @Test
    public void availablePlayerCommands() throws Exception {
        Player player = Player.of("PLAYER 1");
        Dealer dealer = Dealer.of();
        player.setDealer(dealer);

        player.addCard(Card.heart(10));
        player.addCard(Card.heart(10));
        player.updateScore();

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(7));
        dealer.updateScore();
        List<PlayerCommand> commands = player.getAvailableCommands();

        assertThat("Player can be HIT, SPLIT, DOUBLEDOWN", commands,
                IsCollectionContaining.hasItems(PlayerCommand.HIT,
        PlayerCommand.DOUBLEDOWN, PlayerCommand.SPLIT));
    }


    @Test
    public void availablePlayerCommandsAfterBlackJack() throws Exception
    {
        Player player = Player.of("PLAYER 1");
        Dealer dealer = Dealer.of();
        player.setDealer(dealer);
        
        player.addCard(Card.heart(10));
        player.addCard(Card.heart(1));
        player.updateScore();
        dealer.setScore(17);

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(7));
        dealer.updateScore();

        List<PlayerCommand> commands = player.getAvailableCommands();        
        
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertTrue(commands.size() == 1);
    }

    @Test
    public void availablePlayerCommandsAfterBurst() throws Exception
    {
        Player player = Player.of("PLAYER 1");
        Dealer dealer = Dealer.of();
        player.setDealer(dealer);

        player.addCard(Card.heart(10));
        player.addCard(Card.heart(10));
        player.addCard(Card.heart(2));
        player.updateScore();

        dealer.addCard(Card.heart(10));        
        dealer.addCard(Card.heart(7));
        dealer.updateScore();

        List<PlayerCommand> commands = player.getAvailableCommands();
        
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertTrue(commands.size() == 1);
    }

    @Test
    public void DealerStandCommand() throws Exception
    {
        Dealer dealer = Dealer.of();
        DealerCommand command = dealer.getNextCommand();
        dealer.addCard(Card.heart(10));        
        dealer.addCard(Card.heart(7));
        command = dealer.getNextCommand();
        assertTrue(command == DealerCommand.STAND);
    }
    
    @Test
    public void executeHit() throws Exception
    {
        
        Round round = Round.of().setPlayerNumber(1);
        round.initialize();
        
        round.startTurn();

        Player player = round.players().get(0);

        player.setCredit(10000);
        player.bet(1000);
        
        round.setCommand(player, PlayerCommand.HIT);

        Hands hands = player.getHands();
        assertEquals(2, hands.getCardCount());

        round.executeAll();
        round.updateAllStatus();
        round.updateAllScore();
        round.endTurn();

        assertEquals(3, hands.getCardCount());
    }
}