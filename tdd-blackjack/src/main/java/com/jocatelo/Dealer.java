package com.jocatelo;

import java.util.List;
import java.util.ArrayList;

public class Dealer extends Player{
    private List<Card> hands;

    public Dealer(){
        hands = new ArrayList<Card>();

    }
    public void addCard(Card card)
    {
        hands.add(card);

    }

    public int getCardCount()
    {
        return hands.size();
    }
}