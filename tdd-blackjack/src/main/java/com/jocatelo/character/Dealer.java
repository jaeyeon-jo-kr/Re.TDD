package com.jocatelo.character;

import com.jocatelo.Card;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Status;

public class Dealer implements Playable {
    private User user;
    public static final String NAME = "dealer";

    public Dealer(User user){
        this.user = user;
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
    public void command(Command command) {
        user.command(command);
    }
    @Override
    public int hashCode()
    {
        return NAME.hashCode();
    }

    @Override
    public String name()
    {
        return NAME;
    }

    @Override
    public void setIndex(int index) {
        user.setIndex(index);
    }

    @Override
    public int getIndex() {
        return user.getIndex();
    }
}