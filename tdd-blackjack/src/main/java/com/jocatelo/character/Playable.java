package com.jocatelo.character;

import com.jocatelo.Card;
import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;


public interface Playable {
    public void add(Card card);    
    public int getCardCount();
    public int score();
    public void setScore(int score);
    public void updateScore();
    public Card[] hands();
    public void stand();    
    public Status status();
    public void setStatus(Status status);
    public void updateStatus();
    public String name();
    public void setIndex(int index);
    public int getIndex();
    public Rule getRule();
    public void setRule(Rule rule);    
}