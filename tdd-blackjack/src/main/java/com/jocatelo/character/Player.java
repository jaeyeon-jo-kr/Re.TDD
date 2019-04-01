package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.InvalidValueException;
import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;

public class Player extends User implements Playable {


    private int credit;
    private int bet;
    private PlayerCommand command;

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

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void bet(int bet) {
        this.bet = bet;
        this.credit = credit - bet;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }


    public String name() {
        return name;
    }

    @Override
    public void updateScore() {
        rule.updateScore(this);
    }

    public void updateStatus() {
        rule.updateStatus(this);
    }

    public int getWinningCredit(Dealer dealer) {
        if (dealer.getStatus() != Status.BLACKJACK 
            && getStatus() == Status.BLACKJACK)
            return bet * 3 / 2;
        return 0;
    }

    public List<PlayerCommand> getAvailableCommands(Dealer dealer) {
        return getRule().getPlayerCommand(this, dealer);
    }

}