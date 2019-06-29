package com.jocatelo.turn;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.character.Commandable;
import com.jocatelo.rule.Command;

import lombok.Getter;

public class Turns {
    
    private final List<Turn> turns;
    @Getter
    private Turn current;

    private Turns() {
        turns = new ArrayList<>();
        current = Turn.of();
    }

    public static Turns of(){
        return new Turns();
    }

    public void newTurn()
    {
        current = Turn.of();
        turns.add(current);
    }

    public void log(Commandable commandable, Command command){
        current.add(commandable, command);
        
    }
}