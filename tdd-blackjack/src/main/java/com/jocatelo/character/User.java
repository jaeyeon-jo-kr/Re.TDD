package com.jocatelo.character;

import java.util.ArrayList;

import com.jocatelo.Card;
import com.jocatelo.rule.Rule;

import lombok.Data;
@Data
public abstract class User {
    protected Hands hands;    
    protected int score;        
    protected Rule rule;
    protected String name;

    protected User() {
        hands = new Hands(new ArrayList<Card>());
        rule = Rule.CLASSIC;
    }
    
    public void addCard(Card card)
    {
        hands.add(card);
    }
}