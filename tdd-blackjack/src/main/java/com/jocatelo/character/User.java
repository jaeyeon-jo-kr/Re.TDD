package com.jocatelo.character;

import java.util.ArrayList;

import com.jocatelo.Card;
import com.jocatelo.Drawable;
import com.jocatelo.Round;

import lombok.Data;
@Data
public abstract class User {
    protected Hands hands;    
    protected int score;
    protected String name;
    protected Drawable drawer; 

    protected User() {
        hands = Hands.of();
    }
    
    public void addCard(Card card)
    {
        hands.add(card);
    }

    public int getScore()
    {
        return hands.getScore();
    }
}