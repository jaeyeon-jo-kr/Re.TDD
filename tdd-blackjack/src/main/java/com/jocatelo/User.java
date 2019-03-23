package com.jocatelo;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import javax.net.ssl.SSLEngineResult.Status;

import java.util.ArrayList;

public final class User implements Playable {
    private List<Card> hands;
    private Status status;
    private int score;
    private Round round;
    private int index;

    public enum Status {
        WIN, LOSE, DRAW, READY, STAND, BUST, PLAYING, BLACKJACK, FOLD, PUSH
    }

    private User(Round round) {
        hands = new ArrayList<Card>();
        status = Status.PLAYING;
        this.round = round;
    }

    /**
     * create Dealer
     */
    public static Dealer dealer(Round round){
        return new Dealer(new User(round));
    }

    /**
     * create player
     */
    public static Optional<Player> player(Round round, String name){                
        Player player = null;
        if(name != null || name != Dealer.NAME){
            player = new Player(new User(round), name);
        }
        return Optional.ofNullable(player);
    }

    @Override
    public Playable add(Card card) {
        if (status == Status.PLAYING) {
            hands.add(card);
        }
        return this;
    }

    @Override
    public Playable draw(Card card) {
        return this.add(card);
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
    public Playable setScore(int score) {
        this.score = score;
        return this;
    }
    @Override
    public Card[] hands() {
        return hands.toArray(new Card[hands.size()]);
    }

    @Override
    public Playable stand() {
        this.status = Status.STAND;
        return this;
    }

    @Override
    public Playable end(int score) {
        return this;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public Playable setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public User command(Command command) {
        round.turn().command(this, command);
        return this;
    }

    @Override
    public String name()
    {
        return "";
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