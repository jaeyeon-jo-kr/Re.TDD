package com.jocatelo.turn;

import java.util.HashMap;
import java.util.Map;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.Command;

public class Turn {    
    private PlayingPhase cycle;
    
    private Turn()
    {   
        cycle = PlayingPhase.of();
    }
    public static Turn of()
    {
        return new Turn();
    }    
    public void start(PlayerGroup playerGroup)
    {
        cycle.setGroup(playerGroup);
        Thread playingPhaseThread = new Thread(cycle);
        playingPhaseThread.start();
    }

    public void end()
    {
        cycle.stop();
    }
    
    public void add(Commandable commandable, Command command){
        cycle.add(commandable, command);
    }
   
}