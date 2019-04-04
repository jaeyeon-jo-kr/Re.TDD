package com.jocatelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.PlayerCommand;

import lombok.Getter;
import lombok.Setter;

public class Turn {
    private final Map<Commandable, PlayerCommand> userCommands;
    @Getter @Setter
    private DealerCommand dealerCommand;

    @Getter
    private int number;
    private final int MAX_PLAYER_NUMBER = 9;

    private Dealer dealer;
    private PlayerGroup players;

    private Turn() {
        userCommands = new HashMap<>();
    }    


    public static Turn first(Dealer dealer, PlayerGroup players) {
        Turn first = new Turn();
        first.number = 1;
        first.dealer = dealer;
        first.players = players;

        return first;
    }

    public static Turn nextTurn(Turn previous)
    {
        Turn next = new Turn();
        next.number = previous.number + 1;
        return next;
    }

    public Turn initialize(List<Player> players)
    {        
        for(Player player:players){
            userCommands.put(player, PlayerCommand.NONE);
        }
        return this;
    }    
    
    public PlayerCommand getPlayerCommand(Commandable who) {
        return userCommands.get(who);
    }
    
    public boolean isFirst()
    {
        return number == 1;
    }
    public void end()
    {
        dealerCommand = dealer.getCommand();
        players.getPlayers().forEach(player -> userCommands.put(player, player.getCommand()));
        
    }
    
}