package com.jocatelo;

import com.jocatelo.Player.Status;

public enum Rule {
    CLASSIC;

    public boolean isOver(Round round) {
        for (Player player : round.players()) {
            if (player.status() == Status.PLAYING) {
                return false;
            }
        }
        return true;
    }

    public Player updateScore(Player player) {
        int aceCount = 0;
        int score = 0;

        for (Card card : player.hands()) {
            if (card.value() == 1) {
                aceCount += 1;
            } else {
                score += card.value();
            }
        }
        for (int i = 0; i < aceCount; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }
        player.setScore(score);
        return player;
    }

    public Player updateStatus(Player player) {
        if (player.score() > 21)
            player.setStatus(Status.BUST);
        else if (player.score() == 21)
            player.setStatus(Status.BLACKJACK);
        return player;
    }

    public void finalizeStatus(Player[] players) {

        int bestScore = bestScore(players);
        for (Player player : players) {
            if (player.status() == Status.BUST) {
                player.setStatus(Status.LOSE);
            } else if (player.status() == Status.BLACKJACK) {
                player.setStatus(Status.WIN);
            } else {
                if (player.score() == bestScore) {
                    player.setStatus(Status.WIN);
                } else {
                    player.setStatus(Status.LOSE);
                }
            }
        }

    }

    public int bestScore(Player[] players) {
        int bestScore = 0;
        for (Player player : players) {
            if (player.score() <= 21)
                bestScore = Math.max(bestScore, player.score());
        }
        return bestScore;

    }

}
