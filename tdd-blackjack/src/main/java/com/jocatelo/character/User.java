package com.jocatelo.character;

import java.util.ArrayList;

import com.jocatelo.Card;
import com.jocatelo.Round;

import lombok.Data;
@Data
public abstract class User {
    protected Hands hands;    
    protected int score;
    protected String name;    

    protected User() {
        hands = new Hands(new ArrayList<Card>());
        
    }
    
    public void addCard(Card card)
    {
        hands.add(card);
    }
}