package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jocatelo.card.Card;
import com.jocatelo.character.Hands;

public class ScoreCalculator {

    private static final int BLACKJACK_SCORE = 21;

    private final Hands hands;

    private ScoreCalculator(Hands hands)
    {
        this.hands = hands;
    }

    public static ScoreCalculator of(Hands hands)
    {
        return new ScoreCalculator(hands);
    }

    private List<Integer> generateScoreCandidates(Hands hands) {
        List<Integer> candidates = new ArrayList<>();
        candidates.add(0);

        for (Card card : hands.getHands()) {
            candidates = card.accumulateScore(candidates);
        }
        return candidates;
    }

    private int getMaxScore(List<Integer> candidates) {
        return candidates.stream().max(Comparator.comparing(Integer::valueOf)).orElse(0);
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