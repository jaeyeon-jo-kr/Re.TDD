package com.jocatelo.character;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import com.jocatelo.Card;
import com.jocatelo.InvalidValueException;
import com.jocatelo.Round;
import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;

import java.util.ArrayList;

public final class User implements Playable {
    private List<Card> hands;
    private Status status;
    private int score;
    private int index;
    private PlayerCommand command;
    private Rule rule;

    private User() {
        hands = new ArrayList<Card>();
        status = Status.PLAYING;
        rule = Rule.CLASSIC;
    }

    /**
     * create Dealer
     */
    public static Dealer createDealer() {
        return new Dealer(new User());
    }

    /**
     * create player
     * 
     * @throws InvalidValueException
     */
    public static Player createPlayer(String name) throws InvalidValueException {

        if (name == null || name == Dealer.NAME) {
            throw new InvalidValueException();
        }
        return new Player(new User(), name);
    }

    @Override
    public void add(Card card) {
        if (status == Status.PLAYING) {
            hands.add(card);
        }
    }    

    @Override
    public int getCardCount() {
        return hands.size();
    }

    @Override
    public int score() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public Card[] hands() {
        return hands.toArray(new Card[hands.size()]);
    }

    @Override
    public void stand() {
        this.status = Status.STAND;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
    @Override
    public String name() {
        return "user";
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }
    
    @Override
    public Rule getRule() {
        return rule;
    }

    @Override
    public void setRule(Rule rule) {
        this.rule =rule;
    }

    @Override
    public void updateScore() {
        rule.updateScore(this);
    }

    @Override
    public void updateStatus()
    {
        rule.updateStatus(this);
    }
}