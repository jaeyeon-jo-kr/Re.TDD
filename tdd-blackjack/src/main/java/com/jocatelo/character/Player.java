package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Turn;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;

public class Player implements Playable {

    private User user;
    private String name;
    private int credit;
    private int bet;

    public Player(User user, String name) {
        this.user = user;
        this.name = name;
        this.credit = 0;
        this.bet = 0;
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
    public void add(Card card) {
        user.add(card);
    }

    @Override
    public void draw(Card card) {
        user.draw(card);
    }

    @Override
    public int getCardCount() {
        return user.getCardCount();
    }

    @Override
    public int score() {
        return user.score();
    }

    @Override
    public void setScore(int score) {
        user.setScore(score);
    }

    @Override
    public Card[] hands() {
        return user.hands();
    }

    @Override
    public void stand() {
        user.stand();
    }

    @Override
    public Status status() {
        return user.status();
    }

    @Override
    public void setStatus(Status status) {
        user.setStatus(status);
    }

    @Override
    public void setCommand(Command command) {
        user.setCommand(command);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setIndex(int index) {
        user.setIndex(index);
    }

    @Override
    public int getIndex() {
        return user.getIndex();
    }

    @Override
    public void updateScore() {
        user.updateScore();
    }

    public void updateStatus() {
        user.getRule().updateStatus(this);
    }

    @Override
    public Command getCommand() {
        return user.getCommand();
    }

    public int getWinningCredit(Dealer dealer) {
        if (dealer.status() != Status.BLACKJACK && status() == Status.BLACKJACK)
            return bet * 3 / 2;
        return 0;
    }

    @Override
    public Rule getRule() {
        return user.getRule();
    }

    @Override
    public void setRule(Rule rule) {
        user.setRule(rule);
    }

    
    public List<Command> getAvailableCommands(Dealer dealer) {
        return getRule().getPlayerCommand(this, dealer);
    }

}