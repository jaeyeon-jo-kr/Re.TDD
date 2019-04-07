package com.jocatelo.character;

import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.DealerStatus;
import com.jocatelo.rule.ScoreCalculator;

import lombok.Getter;
import lombok.Setter;

public class Dealer extends User implements Playable, Commandable {
    
    public static final String NAME = "dealer";
    @Getter    
    private DealerStatus status;
    @Getter @Setter
    private DealerCommand command;
    private Dealer() {
        name = NAME;
        status = DealerStatus.PLAYING;
    }

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
    
    public void updateStatus() throws Exception {
        status = status.next(this);
    }

    
    public DealerCommand getNextCommand() {
        return DealerCommand.getAvailable(this);
    }

    @Override
    public void execute() {
        DealerCommand command = DealerCommand.getAvailable(this);
        command.execute(drawer, this);
    }
}