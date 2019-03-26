package com.jocatelo.character;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import com.jocatelo.Card;
import com.jocatelo.InvalidValueException;
import com.jocatelo.Round;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Status;

import java.util.ArrayList;

public final class User implements Playable {
    private List<Card> hands;
    private Status status;
    private int score;
    private Round round;
    private int index;

    private User(Round round) {
        hands = new ArrayList<Card>();
        status = Status.PLAYING;
        this.round = round;
    }

    /**
     * create Dealer
     */
    public static Dealer createDealer(Round round){
        return new Dealer(new User(round));
    }

    /**
     * create player
     * 
     * @throws InvalidValueException
     */
    public static Player createPlayer(Round round, String name) throws InvalidValueException {
        
        if(name == null || name == Dealer.NAME){
            throw new InvalidValueException();
        }
        return new Player(new User(round), name);
    }

    @Override
    public void add(Card card) {
        if (status == Status.PLAYING) {
            hands.add(card);
        }
    }

    @Override
    public void draw(Card card) {
        add(card);
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
    public void command(Command command) {
        round.turn().command(this, command);
    }

    @Override
    public String name()
    {
        return "user";
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

}