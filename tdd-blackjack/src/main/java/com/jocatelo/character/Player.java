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
    @Getter
    @Setter
    private Dealer dealer;

    @Getter
    @Setter
    private PlayerStatus status;

    private Player(String name) {
        super();        
        this.name = name;
        this.credit = 0;
        this.bet = 0;
        this.status = PlayerStatus.PLAYING;
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
        if (dealer.getStatus() != DealerStatus.BLACKJACK 
            && status == PlayerStatus.BLACKJACK)
            return bet * 3 / 2;
        return 0;
    }

    public List<PlayerCommand> getAvailableCommands() {
        return PlayerCommand.getAvailable(this);
    }

    @Override
    public void finalizeStatus() {

    }

}