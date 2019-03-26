package com.jocatelo.rule;

import com.jocatelo.Round;
import com.jocatelo.character.Playable;


public enum Command {
    NONE("NONE"){public void execute(Round round, Playable user){}}, 
    DRAW("DRAW"){public void execute(Round round, Playable user){round.draw(user);}},    
    FOLD("FOLD"){public void execute(Round round, Playable user){user.setStatus(Status.FOLD);}},
    HOLD("HOLD"){public void execute(Round round, Playable user){}};
    private String symbol;
    Command(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }

    public abstract void execute(Round round, Playable user);
}