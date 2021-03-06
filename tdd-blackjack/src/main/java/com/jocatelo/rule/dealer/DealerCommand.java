package com.jocatelo.rule.dealer;

import com.jocatelo.card.Card;
import com.jocatelo.Drawable;
import com.jocatelo.character.Dealer;
import com.jocatelo.rule.Command;

public enum DealerCommand implements Command {    
    NONE("NONE"){public void execute(Drawable drawable, Dealer dealer){}},            
    DRAW("DRAW"){public void execute(Drawable drawable, Dealer dealer){
        Card card = drawable.popCard();
        dealer.addCard(card);
    }},            
    STAND("STAND"){public void execute(Drawable drawable, Dealer dealer){}};
    private final String symbol;
    DealerCommand(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }

    public abstract void execute(Drawable drawable, Dealer dealer);
    public static DealerCommand getAvailable(Dealer dealer){
        DealerCommand command;

        if (dealer.getScore() <= 16) {
            command = DealerCommand.DRAW;
            return command;
        }
        
        command = DealerCommand.STAND;
        return command;
    }

}