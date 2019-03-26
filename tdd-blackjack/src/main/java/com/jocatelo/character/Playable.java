package com.jocatelo.character;

import com.jocatelo.Card;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Status;


public interface Playable extends Commandable {
    public void add(Card card);
    public void draw(Card card) ;
    public int getCardCount();
    public int score();
    public void setScore(int score);
    public Card[] hands();
    public void stand();    
    public Status status();
    public void setStatus(Status status);
    public String name();
    public void setIndex(int index);
    public int getIndex();


    @Override
    public void command(Command command);

}