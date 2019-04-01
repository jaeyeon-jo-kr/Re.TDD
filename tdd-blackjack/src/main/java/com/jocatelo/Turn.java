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

import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.PlayerCommand;

public class Turn {
    private final Map<Playable, PlayerCommand> userCommands;
    private DealerCommand dealerCommands;
    
    private int number;
    private final int MAX_PLAYER_NUMBER = 9;

    private Turn() {
        userCommands = new HashMap<>();
    }    


    public static Turn create(int number) {
        return new Turn();
    }

    public Turn initialize(List<Player> players)
    {        
        for(Player player:players){
            userCommands.put(player, PlayerCommand.NONE);
        }
        return this;
    }

    public void add(Playable who, PlayerCommand what) {        
        userCommands.put(who,what);
    }

    public PlayerCommand what(Playable who) {
        return userCommands.get(who);
    }

    public int number() {
        return number;
    }
}