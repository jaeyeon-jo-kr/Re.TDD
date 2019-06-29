package com.jocatelo.turn;

import java.util.HashMap;
import java.util.Map;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.Command;

import lombok.Setter;

public class PlayingPhase implements Runnable{
    private final Map<Commandable, Command> userCommands;
    @Setter
    private PlayerGroup group;
    boolean isRunning = false;

    private PlayingPhase()
    {
        userCommands = new HashMap<>();
    }

    public static PlayingPhase of()
    {
        return new PlayingPhase();
    }
    @Override
    public void run() {
        isRunning = true;

        while(isRunning && !isAllCommandAccepted())
        {
            /** Wait for player command. */
        }

    }

    public void stop()
    {
        isRunning = false;
    }

    private boolean isAllCommandAccepted()
    {
        return group.getPlayers().size() == userCommands.size();
    }

    public void add(Commandable commandable, Command command){
        userCommands.put(commandable, command);
    }

    
}