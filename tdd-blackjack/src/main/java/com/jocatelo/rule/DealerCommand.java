package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;

public enum DealerCommand {    
    NONE("NONE"){public void execute(Round round, Dealer dealer){}},            
    DRAW("DRAW"){public void execute(Round round, Dealer dealer){}},            
    STAND("STAND"){public void execute(Round round, Dealer dealer){}};
    private String symbol;
    DealerCommand(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }

    public abstract void execute(Round round, Dealer dealer);
    

}