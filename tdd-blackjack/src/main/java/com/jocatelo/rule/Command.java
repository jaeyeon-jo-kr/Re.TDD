package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;


public enum Command {


    NONE("NONE"){public void execute(Round round, Playable user){}}, 
    DRAW("DRAW"){public void execute(Round round, Playable user){round.draw(user);}},    
    SURRENDER("SURRENDER"){public void execute(Round round, Playable user){user.setStatus(Status.SURRENDER);}},
    HOLD("HOLD"){public void execute(Round round, Playable user){}},
    HIT("HIT"){public void execute(Round round, Playable user){}},
    STAND("STAND"){public void execute(Round round, Playable user){}},
    DOUBLEDOWN("DOUBLEDOWN"){public void execute(Round round, Playable user){}}, 
    SPLIT("SPLIT"){public void execute(Round round, Playable user){}};
    private String symbol;
    Command(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }

    public abstract void execute(Round round, Playable user);
    public static List<Command> available(Dealer dealer)
    {
        List<Command> command = new ArrayList<Command>();
        

        return command;        
    }

}