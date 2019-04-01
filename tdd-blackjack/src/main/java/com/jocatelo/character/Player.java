package com.jocatelo.character;

import java.util.List;

import com.jocatelo.InvalidValueException;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.ScoreCalculator;
import com.jocatelo.rule.Status;

import lombok.Getter;
import lombok.Setter;

public class Player extends User implements Playable,Commandable {

    @Setter
    private int credit;
    @Getter
    private int bet;
    @Setter
    private Dealer dealer;

    private Player(String name) {
        super();        
        this.name = name;
        this.credit = 0;
        this.bet = 0;
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
        rule.updateStatus(this);
    }

    public int getWinningCredit() {
        if (dealer.getStatus() != Status.BLACKJACK 
            && getStatus() == Status.BLACKJACK)
            return bet * 3 / 2;
        return 0;
    }

    public List<PlayerCommand> getAvailableCommands() {
        return rule.getPlayerCommand(this, dealer);
    }

    @Override
    public void finalizeStatus() {

    }

}