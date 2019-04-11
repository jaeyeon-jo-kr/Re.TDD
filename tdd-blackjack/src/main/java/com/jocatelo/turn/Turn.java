package com.jocatelo.turn;

import java.util.HashMap;
import java.util.Map;

import com.jocatelo.character.Commandable;
import com.jocatelo.rule.Command;

public class Turn {
    private final Map<Commandable, Command> userCommands;    

    private Turn()
    {
        userCommands = new HashMap<>();
    }
    public static Turn of()
    {
        return new Turn();
    }    
    public void start()
    {
        
    }

    

    public void end()
    {
        
    }
    public void add(Commandable commandable, Command command){
        userCommands.put(commandable, command);
    }
   
}