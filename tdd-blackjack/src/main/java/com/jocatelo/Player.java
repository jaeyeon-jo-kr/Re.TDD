package com.jocatelo;

import java.util.List;
import java.util.PriorityQueue;

import javax.net.ssl.SSLEngineResult.Status;

import java.util.ArrayList;


public class Player {
    protected List<Card> hands;
    protected Status status;
    protected int score;

    public enum Status{
        WIN,
        LOSE,
        READY,
        STAND,
        BUST,
        PLAYING,
        BLACKJACK,
        FOLD
    }

    public Player(){
        hands = new ArrayList<Card>();
        status = Status.PLAYING;
    }

    public Player add(Card card)
    {
        if(status == Status.PLAYING)
        {
            hands.add(card);
            score = calculateScore();
        }
        return this;
    }
    public Player draw(Card card)
    {
        return this.add(card);
    }

    public int getCardCount()
    {
        return hands.size();
    }

    public int score()
    {
        return score;
    }

    public Player stand()
    {
        this.status = Status.STAND;
        return this;
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

        if(score > 21 )
            status = Status.BUST;
        else if (score == 21)
            status = Status.BLACKJACK;
        return score;
    }

    public Status status()
    {
        return status;
    }
}