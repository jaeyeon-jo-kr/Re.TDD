package com.jocatelo.character;

import com.jocatelo.Card;

import lombok.Data;
@Data
public abstract class User {
    
    protected Hands hands;    
    protected String name;
    

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