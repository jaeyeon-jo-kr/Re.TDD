package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Status;

public class Player implements Playable {

    private User user;
    private String name;

    public Player(User user, String name){
        this.user = user;
        this.name = name;
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
    public int hashCode(){
        return name.hashCode();
    }   

    @Override
    public String name()
    {
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

}