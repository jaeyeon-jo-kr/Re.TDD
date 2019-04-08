package com.jocatelo.character;

import com.jocatelo.rule.ScoreCalculator;

import lombok.Getter;

public class Score
{
    @Getter
    int score = 0;
    
    private Score()
    {
        score = 0;
    }

    public static Score of()
    {
        return new Score();
    }

    public void update(Hands hands)
    {
        ScoreCalculator calculator = ScoreCalculator.of(hands);
        score = calculator.calculate();
    }
    

}