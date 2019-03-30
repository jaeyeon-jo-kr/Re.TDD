package com.jocatelo;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.PlayerCommand;

import org.junit.Test;

public class CommandTest
{
    @Test
    public void availablePlayerCommandsBefore16() throws Exception
    {        
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(20);
        dealer.setScore(16);
        List<PlayerCommand> commands = player.getAvailableCommands(dealer);
        assertArrayEquals(new PlayerCommand[]{PlayerCommand.HIT, PlayerCommand.DOUBLEDOWN, PlayerCommand.SPLIT}, commands.toArray(new PlayerCommand[commands.size()]));

        
    }

    @Test
    public void availablePlayerCommandsAfter17() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(20);
        dealer.setScore(17);
        List<PlayerCommand> commands = player.getAvailableCommands(dealer);
        
        // assertEquals(Arrays.asList(Command.HIT, Command.DOUBLEDOWN, Command.STAND, Command.SPLIT), commands);
        assertTrue(commands.contains(PlayerCommand.HIT));
        assertTrue(commands.contains(PlayerCommand.DOUBLEDOWN));
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertTrue(commands.contains(PlayerCommand.SPLIT));
        assertTrue(commands.size()==4);
    }


    @Test
    public void availablePlayerCommandsAfterBlackJack() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(21);
        dealer.setScore(17);

        List<PlayerCommand> commands = player.getAvailableCommands(dealer);
        
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertTrue(commands.size() == 1);
    }

    @Test
    public void availablePlayerCommandsAfterBlackBurst() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(22);
        dealer.setScore(17);

        List<PlayerCommand> commands = player.getAvailableCommands(dealer);
        
        assertTrue(commands.contains(PlayerCommand.STAND));
        assertTrue(commands.size() == 1);
    }


    @Test
    public void availableDealerCommand() throws Exception
    {
        Dealer dealer = User.createDealer();
        DealerCommand command = dealer.getNextCommand();
        dealer.setScore(16);
        assertTrue(command == DealerCommand.DRAW);

        dealer.setScore(17);
        command = dealer.getNextCommand();
        assertTrue(command == DealerCommand.STAND);

        dealer.setScore(21);        
        command = dealer.getNextCommand();
        assertTrue(command == DealerCommand.STAND);

        dealer.setScore(22);
        command = dealer.getNextCommand();
        assertTrue(command == DealerCommand.STAND);
    }

    
    @Test
    public void executeHit() throws Exception
    {
        
        Round round = Round.of().setPlayerNumber(1);
        round.initialize();
        round.start();

        Player player = round.players().get(0);

        player.setCredit(10000);
        player.bet(1000);
        
        round.setCommand(player, PlayerCommand.HIT);

        assertEquals(2, player.getCardCount());

        round.endTurn();

        assertEquals(3, player.getCardCount());
    }
}