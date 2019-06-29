package com.jocatelo;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.dealer.DealerCommand;
import com.jocatelo.rule.player.PlayerCommand;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Test;

public class CommandTest {
    
    @Test
    public void availablePlayerCommands() throws Exception {
        Player player = Player.of("PLAYER 1");
        Dealer dealer = Dealer.of();

        player.addCard(Card.heart(10));
        player.addCard(Card.heart(10));
        

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(7));
        
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
        
        player.addCard(Card.heart(10));
        player.addCard(Card.heart(1));
        

        dealer.addCard(Card.clover(10));
        dealer.addCard(Card.clover(7));
        

        List<PlayerCommand> commands = player.getAvailableCommands();        
        
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertEquals(commands.size(),1);
    }

    @Test
    public void availablePlayerCommandsAfterBurst() throws Exception
    {
        Player player = Player.of("PLAYER 1");
        Dealer dealer = Dealer.of();

        player.addCard(Card.heart(10));
        player.addCard(Card.heart(10));
        player.addCard(Card.heart(2));
        

        dealer.addCard(Card.heart(10));        
        dealer.addCard(Card.heart(7));
        

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
        assertThat(command, equalTo(DealerCommand.STAND));
    }
    
    @Test
    public void executeHit() throws Exception
    {
        
        Round round = Round.of();
        round.initialize();
        
        round.startTurn();

        Participants participants = round.getParticipants();
        PlayerGroup players = participants.getPlayers();

        Player player = players.get(0);

        player.setCredit(10000);
        player.bet(1000);        
        
        player.setCommand(PlayerCommand.HIT);

        Hands hands = player.getHands();
        assertEquals(2, hands.getCardCount());

        round.executeAll();
        round.updateAllStatus();        
        round.endTurn();

        assertEquals(3, hands.getCardCount());
    }
}