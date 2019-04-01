package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Turn;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;

public class Dealer extends User implements Playable {
    
    public static final String NAME = "dealer";

    private Dealer() {}

    /**
     * create Dealer
     */
    public static Dealer of() {
        return new Dealer();
    }

    @Override
    public int hashCode() {
        return NAME.hashCode();
    }

    
    public String name() {
        return NAME;
    }
    
   
    public void updateScore() {
        Rule rule = getRule();
        rule.updateScore(this);
    }

    public void updateStatus() {
        Rule rule = getRule();
        rule.updateStatus(this);
    }

    
    public DealerCommand getNextCommand() {
        return getRule().getDealerCommand(this);
    }
}