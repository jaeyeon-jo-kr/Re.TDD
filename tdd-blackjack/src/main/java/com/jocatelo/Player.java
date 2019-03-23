package com.jocatelo;

import java.util.List;
import java.util.PriorityQueue;

import javax.net.ssl.SSLEngineResult.Status;

import java.util.ArrayList;

public class Player implements Commandable {
    protected List<Card> hands;
    protected Status status;
    protected int score;
    protected Round round;

    public enum Status {
        WIN, LOSE, READY, STAND, BUST, PLAYING, BLACKJACK, FOLD
    }

    public Player(Round round) {
        hands = new ArrayList<Card>();
        status = Status.PLAYING;
        this.round = round;
    }

    public Player add(Card card) {
        if (status == Status.PLAYING) {
            hands.add(card);
        }
        return this;
    }

    public Player draw(Card card) {
        return this.add(card);
    }

    public int getCardCount() {
        return hands.size();
    }

    public int score() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public List<Card> hands() {
        return hands;
    }

    public Player stand() {
        this.status = Status.STAND;
        return this;
    }

    public Player end(int score) {

        return this;
    }

    public Status status() {
        return status;
    }

    public Player setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public Player command(Command command) {
        round.turn().command(this, command);
        return this;
    }

}