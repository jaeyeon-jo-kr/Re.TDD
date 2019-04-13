package com.jocatelo.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jocatelo.Card;

public class Hands {
    private List<Card> hands;

    private Hands(){
        this.hands = new ArrayList<>();        
    }

    public static Hands of()
    {
        return new Hands();
    }

    public List<Card> getHands() {
        return Collections.unmodifiableList(hands);
    }

    public int getCardCount() {
        return hands.size();
    }

    public void add(Card card) {
        hands.add(card);        
    }
    
}