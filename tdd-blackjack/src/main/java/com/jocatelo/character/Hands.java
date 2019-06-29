package com.jocatelo.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jocatelo.card.Card;
import com.jocatelo.rule.ScoreCalculator;

import lombok.Getter;

public class Hands {
    class Score {
        @Getter
        int score;

        Score() {
            score = 0;
        }
        void update(Hands hands) {
            ScoreCalculator calculator = ScoreCalculator.of(hands);
            score = calculator.calculate();
        }
    }

    private final List<Card> hands;

    private Hands() {
        this.hands = new ArrayList<>();
    }

    public static Hands of() {
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

    int getScore()
    {
        Score score = new Score();
        score.update(this);
        return score.getScore();
    }

}