package com.jocatelo.character;

import java.util.Collections;
import java.util.List;

import com.jocatelo.Card;

public class Hands {
    private List<Card> hands;

    public Hands(List<Card> hands){
        this.hands = hands;
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