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
import com.jocatelo.character.Hands;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;

public enum Rule {
    CLASSIC;

    private static final int BLACKJACK_SCORE = 21;

    public boolean isOver(Round round) {
        boolean isplaying = false;
        Dealer dealer = round.dealer();
        isplaying = dealer.getStatus() == Status.PLAYING;
        for (Player player : round.players()) {
            isplaying = (player.getStatus() == Status.PLAYING);
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

    public void updateScore(User player) {
        final boolean BUST = false;
        final boolean NORMAL = true;

        List<Integer> candidates = generateScoreCandidates(player.getHands());
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

    public void updateStatus(User user) {
        if (user.getScore() > BLACKJACK_SCORE){
            user.setStatus(Status.BUST);
            return;
        }        
        user.setStatus(Status.BLACKJACK);
    }

    public void finalizeStatus(Dealer dealer, List<Player> players) {

        for (Player player : players) {
            if (player.getStatus() == Status.BUST) {
                player.setStatus(Status.LOSE);
            } else if (player.getStatus() == Status.BLACKJACK) {
                if (dealer.getStatus() == Status.BLACKJACK)
                    player.setStatus(Status.DRAW);
                else
                    player.setStatus(Status.WIN);
            } else {
                if (player.getScore() > dealer.getScore())
                    player.setStatus(Status.WIN);
                else if (player.getScore() < dealer.getScore())
                    player.setStatus(Status.LOSE);
                else
                    player.setStatus(Status.DRAW);
            }
        }

    }

    

    public DealerCommand getDealerCommand(Dealer dealer) {
        DealerCommand command = DealerCommand.NONE;

        if (dealer.getScore() <= 16) {
            command = DealerCommand.DRAW;
        } else if (dealer.getScore() >= 17) {
            command = DealerCommand.STAND;
        }

        return command;
    }

    public List<PlayerCommand> getPlayerCommand(Player player, Dealer dealer) {
        List<PlayerCommand> commands = new ArrayList<PlayerCommand>();
        if (player.getScore() < 21) {
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);
            if (dealer.getScore() >= 17) {
                commands.add(PlayerCommand.STAND);
            }
        } else if (player.getScore() >= 21) {
            commands.add(PlayerCommand.STAND);
        }
        return commands;
    }

}
