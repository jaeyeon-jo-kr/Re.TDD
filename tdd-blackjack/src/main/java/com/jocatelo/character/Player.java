package com.jocatelo.character;

import java.util.List;

import com.jocatelo.InvalidValueException;
import com.jocatelo.rule.DealerStatus;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.ScoreCalculator;
import com.jocatelo.rule.PlayerStatus;


import lombok.Getter;
import lombok.Setter;

public class Player extends User implements Playable,Commandable {

    @Setter
    private int credit;
    
    @Getter
    private int bet;

    @Getter @Setter
    private Dealer dealer;

    @Getter @Setter
    private PlayerStatus status;

    @Getter @Setter
    private float winningRate;

    private Player(String name) {
        super();        
        this.name = name;
        credit = 0;
        bet = 0;
        winningRate = 0;
        status = PlayerStatus.PLAYING;
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
    public void updateScore() {
        score = ScoreCalculator.calculate(this);
    }

    public void updateStatus() {
        status = status.next(this);
    }

    public int getWinningCredit() {
        return (int)(bet * winningRate);        
    }

    public List<PlayerCommand> getAvailableCommands() {
        return PlayerCommand.getAvailable(this);
    }

    @Override
    public void finalizeStatus() {
        status = status.finalize(this);
    }

}