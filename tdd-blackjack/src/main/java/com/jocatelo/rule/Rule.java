package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jocatelo.Card;
import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;

public enum Rule {
    CLASSIC;

    private static final int BLACKJACK_SCORE = 21;

    public boolean isOver(Round round) {
        boolean isplaying = false;

        for (Playable player : round.users()) {
            isplaying = (player.status() == Status.PLAYING);
        }
        return isplaying;
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

    private List<Integer> generateScoreCandidates(Playable player) {
        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(0);

        for (Card card : player.hands()) {
            candidates = addScore(candidates, card);
        }
        return candidates;
    }

    private int getMaxScore(List<Integer> candidates) {
        return candidates.stream().max(Comparator.comparing(Integer::valueOf)).get();
    }

    public void updateScore(Playable player) {
        final boolean BUST = false;
        final boolean NORMAL = true;

        List<Integer> candidates = generateScoreCandidates(player);
        Map<Boolean, List<Integer>> groups = candidates.stream()
                .collect(Collectors.partitioningBy(x -> x <= BLACKJACK_SCORE));

        if (!groups.get(NORMAL).isEmpty()) {
            candidates = groups.get(NORMAL);
            player.setScore(getMaxScore(candidates));
            return;
        }

        candidates = groups.get(BUST);
        player.setScore(getMaxScore(candidates));
    }

    public void setMaxScore(Integer maxScore, int score) {
        maxScore = Math.max(maxScore, score);

    }

    public Playable updateStatus(Playable player) {
        if (player.score() > BLACKJACK_SCORE)
            player.setStatus(Status.BUST);
        else if (player.score() == BLACKJACK_SCORE)
            player.setStatus(Status.BLACKJACK);
        return player;
    }

    public void finalizeStatus(Dealer dealer, List<Player> players) {

        for (Playable player : players) {
            if (player.status() == Status.BUST) {
                player.setStatus(Status.LOSE);
            } else if (player.status() == Status.BLACKJACK) {
                if (dealer.status() == Status.BLACKJACK)
                    player.setStatus(Status.DRAW);
                else
                    player.setStatus(Status.WIN);
            } else {
                if (player.score() > dealer.score())
                    player.setStatus(Status.WIN);
                else if (player.score() < dealer.score())
                    player.setStatus(Status.LOSE);
                else
                    player.setStatus(Status.DRAW);
            }
        }

    }

    public int bestScore(Playable[] players) {
        int bestScore = 0;
        for (Playable player : players) {
            if (player.score() <= 21)
                bestScore = Math.max(bestScore, player.score());
        }
        return bestScore;

    }

    public DealerCommand getDealerCommand(Dealer dealer) {
        DealerCommand command = DealerCommand.NONE;

        if (dealer.score() <= 16) {
            command = DealerCommand.DRAW;
        } else if (dealer.score() >= 17) {
            command = DealerCommand.STAND;
        }

        return command;
    }

    public List<PlayerCommand> getPlayerCommand(Player player, Dealer dealer) {
        List<PlayerCommand> commands = new ArrayList<PlayerCommand>();
        if (player.score() < 21) {
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);
            if (dealer.score() >= 17) {
                commands.add(PlayerCommand.STAND);
            }
        } else if (player.score() >= 21) {
            commands.add(PlayerCommand.STAND);
        }
        return commands;
    }

}
