package com.jocatelo;

import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Player {
    protected List<Card> hands;

    public Player(){
        hands = new ArrayList<Card>();
    }
    public void draw(Card card)
    {
        hands.add(card);

    }

    public int getCardCount()
    {
        return hands.size();
    }

    public int score()
    {
        
        return calculateScore();
    }

    private int calculateScore()
    {
        int aceCount = 0;

        int score = 0;

        for(Card card:hands){
            if(card.value() == 1){
                aceCount += 1;
            }else{
                score += card.value();
            }
        }

        for(int i=0;i<aceCount;i++)
        {
            if(score + 11 <=21)
            {
                score += 11;
            }else{
                score += 1;
            }
        }
        return score;
    }
}