package com.jocatelo;

import java.util.List;

import com.jocatelo.User.Status;

public interface Playable extends Commandable {
    public Playable add(Card card);
    public Playable draw(Card card) ;
    public int getCardCount();
    public int score();
    public Playable setScore(int score);
    public Card[] hands();
    public Playable stand();
    public Playable end(int score);
    public Status status();
    public Playable setStatus(Status status);
    public String name();
    public void setIndex(int index);
    public int getIndex();


    @Override
    public Playable command(Command command);

}