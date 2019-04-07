package com.jocatelo.character;

import java.util.Collections;
import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.rule.ScoreCalculator;

import lombok.Getter;

public class Hands {
    private List<Card> hands;
    private ScoreCalculator calculator;
    @Getter
    private int score;

    public Hands(List<Card> hands){
        this.hands = hands;
        this.calculator = ScoreCalculator.of(this);
    }

    public List<Card> getHands() {
        return Collections.unmodifiableList(hands);
    }

    public int getCardCount() {
        return hands.size();
    }

    public void add(Card card) {
        hands.add(card);
        updateScore();
    }

    private void updateScore()
    {
        score = calculator.calculate();
    }
}