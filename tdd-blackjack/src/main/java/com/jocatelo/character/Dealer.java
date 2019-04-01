package com.jocatelo.character;

import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.ScoreCalculator;

public class Dealer extends User implements Playable, Commandable {
    
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
        score = ScoreCalculator.calculate(this);
    }

    public void updateStatus() {
        Rule rule = getRule();
        rule.updateStatus(this);
    }

    
    public DealerCommand getNextCommand() {
        return getRule().getDealerCommand(this);
    }
}