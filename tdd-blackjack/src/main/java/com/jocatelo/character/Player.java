package com.jocatelo.character;

import java.util.List;

import com.jocatelo.InvalidValueException;
import com.jocatelo.rule.DealerStatus;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.ScoreCalculator;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.PlayingStatus;

import lombok.Getter;
import lombok.Setter;

public class Player extends User implements Playable, Commandable {

    @Setter
    private int credit;

    @Getter
    private int bet;

    @Getter
    @Setter
    private Dealer dealer;

    @Getter
    @Setter
    private PlayingStatus status;

    @Getter
    @Setter
    private WinStatus winStatus;

    @Getter @Setter
    private PlayerCommand command;
    
    private Player(String name) {
        super();        
        this.name = name;
        credit = 0;
        bet = 0;
        status = PlayingStatus.PLAYING;
    }

    /**
     * create player
     * 
     * @throws InvalidValueException
     */
    public static Player of(String name) throws InvalidValueException {
        if (name == null || name == Dealer.NAME) {
            throw new InvalidValueException();
        }
        return new Player(name);
    }    

    public void bet(int bet) {
        this.bet = bet;
        this.credit = credit - bet;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }   

    @Override
    public void updateStatus() {
        status = status.next(this);
    }

    public int getWinningCredit() {
        return (int)(bet * winStatus.getRate());        
    }

    public void execute(){
        command.execute(drawer, this);
    }

    public List<PlayerCommand> getAvailableCommands() {
        return PlayerCommand.getAvailable(this);
    }    
   
}