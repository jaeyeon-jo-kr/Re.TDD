package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jocatelo.Card;
import com.jocatelo.character.Hands;
import com.jocatelo.character.User;

public class ScoreCalculator {

    private static final int BLACKJACK_SCORE = 21;

    private Hands hands;

    private ScoreCalculator(Hands hands)
    {
        this.hands = hands;
    }

    public static ScoreCalculator of(Hands hands)
    {
        return new ScoreCalculator(hands);

    }
    private List<Integer> plusSpecialScore(int score, Optional<Integer> specialValue){
        List<Integer> plus = new ArrayList<>();
        if (specialValue.isPresent()) {
            plus.add(score + specialValue.get());
        }
        return plus;
    }

    private List<Integer> addScore(List<Integer> candidates, Card card) {
        List<Integer> result = new ArrayList<>();
        for (int score : candidates) {
            result.add(score + card.value());
            result.addAll(plusSpecialScore(score, card.specialValue()));
        }
        return result;
    }

    private List<Integer> generateScoreCandidates(Hands hands) {
        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(0);

        for (Card card : hands.getHands()) {
            candidates = addScore(candidates, card);
        }
        return candidates;
    }

    private int getMaxScore(List<Integer> candidates) {
        return candidates.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    public int calculate() {
        final boolean BUST = false;
        final boolean NORMAL = true;

        List<Integer> candidates = generateScoreCandidates(hands);
        Map<Boolean, List<Integer>> groups = candidates.stream()
                .collect(Collectors.partitioningBy(x -> x <= BLACKJACK_SCORE));

        if (!groups.get(NORMAL).isEmpty()) {
            candidates = groups.get(NORMAL);
            return getMaxScore(candidates);            
        }

        candidates = groups.get(BUST);
        return getMaxScore(candidates);
    }
}