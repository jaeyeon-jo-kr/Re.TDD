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
import com.jocatelo.rule.Command;

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
        List<Command> commands = player.getAvailableCommands(dealer);
        assertArrayEquals(new Command[]{Command.HIT, Command.DOUBLEDOWN, Command.SPLIT}, commands.toArray(new Command[commands.size()]));

        
    }

    @Test
    public void availablePlayerCommandsAfter17() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(20);
        dealer.setScore(17);
        List<Command> commands = player.getAvailableCommands(dealer);
        
        // assertEquals(Arrays.asList(Command.HIT, Command.DOUBLEDOWN, Command.STAND, Command.SPLIT), commands);
        assertTrue(commands.contains(Command.HIT));
        assertTrue(commands.contains(Command.DOUBLEDOWN));
        assertTrue(commands.contains(Command.STAND));
        assertTrue(commands.contains(Command.SPLIT));
    }


    @Test
    public void availablePlayerCommandsAfterBlackJack() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(21);
        dealer.setScore(17);

        List<Command> commands = player.getAvailableCommands(dealer);
        
        assertTrue(commands.contains(Command.STAND));
        assertTrue(commands.size() == 1);
    }

    @Test
    public void availablePlayerCommandsAfterBlackBurst() throws Exception
    {
        Player player = User.createPlayer("PLAYER 1");
        Dealer dealer = User.createDealer();

        player.setScore(22);
        dealer.setScore(17);

        List<Command> commands = player.getAvailableCommands(dealer);
        
        assertTrue(commands.contains(Command.STAND));
        assertTrue(commands.size() == 1);
    }


    @Test
    public void availableDealerCommands() throws Exception
    {
        Dealer dealer = User.createDealer();
        List<Command> commands = dealer.getAvailableCommands();
        dealer.setScore(16);
        assertArrayEquals(new Command[]{Command.DRAW},  commands.toArray(new Command[commands.size()]));

        dealer.setScore(17);
        commands = dealer.getAvailableCommands();
        assertArrayEquals(new Command[]{Command.STAND},  commands.toArray(new Command[commands.size()]));

        dealer.setScore(21);        
        commands = dealer.getAvailableCommands();
        assertArrayEquals(new Command[]{Command.STAND},  commands.toArray(new Command[commands.size()]));

        dealer.setScore(22);
        commands = dealer.getAvailableCommands();
        assertArrayEquals(new Command[]{Command.STAND},  commands.toArray(new Command[commands.size()]));
    }
}