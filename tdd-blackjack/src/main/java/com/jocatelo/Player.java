package com.jocatelo;

import java.util.List;

import com.jocatelo.User.Status;

public class Player implements Playable {

    private User user;
    private String name;

    public Player(User user, String name){
        this.user = user;
        this.name = name;
    }

    @Override
    public Playable add(Card card) {
        return user.add(card);
    }

    @Override
    public Playable draw(Card card) {
        return user.draw(card);
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
    public Playable setScore(int score) {
        return user.setScore(score);
    }

    @Override
    public Card[] hands() {
        return user.hands();
    }

    @Override
    public Playable stand() {
        return user.stand();
    }

    @Override
    public Playable end(int score) {
        return user.end(score);
    }

    @Override
    public Status status() {
        return user.status();
    }

    @Override
    public Playable setStatus(Status status) {
        return user.setStatus(status);
    }

    @Override
    public User command(Command command) {
        return user.command(command);
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