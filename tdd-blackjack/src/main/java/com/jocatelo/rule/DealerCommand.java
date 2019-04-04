package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Drawable;
import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;

public enum DealerCommand {    
    NONE("NONE"){public void execute(Drawable drawable, Dealer dealer){}},            
    DRAW("DRAW"){public void execute(Drawable drawable, Dealer dealer){
        Card card = drawable.popCard();
        dealer.addCard(card);
    }},            
    STAND("STAND"){public void execute(Drawable drawable, Dealer dealer){}};
    private String symbol;
    DealerCommand(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }

    public abstract void execute(Drawable drawable, Dealer dealer);
    public static DealerCommand getAvailable(Dealer dealer){
        DealerCommand command = DealerCommand.NONE;

        if (dealer.getScore() <= 16) {
            command = DealerCommand.DRAW;
            return command;
        }
        
        command = DealerCommand.STAND;
        return command;
    }

}