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
import com.jocatelo.rule.PlayerCommand;

public class Turn {
    private List<PlayerCommand> playerCommands;
    
    private int number;
    private final int MAX_PLAYER_NUMBER = 9;

    private Turn() {
        playerCommands = new ArrayList<>(MAX_PLAYER_NUMBER);
    }    


    public static Turn create(int number) {
        return new Turn();
    }

    public Turn initialize(List<Player> players)
    {        
        for(Player player:players){
            playerCommands.add(player.getIndex()-1, PlayerCommand.NONE);
        }
        return this;
    }

    public void add(Playable who, PlayerCommand what) {        
        playerCommands.add(who.getIndex()-1,what);
    }

    public PlayerCommand what(Playable who) {
        return playerCommands.get(who.getIndex()-1);
    }

    public int number() {
        return number;
    }
}