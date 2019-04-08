package com.jocatelo.turn;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.character.Commandable;
import com.jocatelo.rule.Command;

public class Turns {
    
    private List<Turn> turns;
    private Turn current;

    private Turns() {
        turns = new ArrayList<>();
        current = Turn.of();
    }

    public static Turns of(){
        return new Turns();
    }

    public void start()
    {
        current = Turn.of();
    }

    public void end()
    {
        turns.add(current);
    }

    public void log(Commandable commandable, Command command){
        current.add(commandable, command);
        
    }


    
    
}