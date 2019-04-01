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
        isplaying = dealer.getStatus() == DealerStatus.PLAYING;
        for (Player player : round.players()) {
            isplaying = (player.getStatus() == PlayerStatus.PLAYING);
        }
        return isplaying;
    }

    public void finalizeStatus(Dealer dealer, List<Player> players) {

        for (Player player : players) {
            if (player.getStatus() == PlayerStatus.BUST) {
                player.setStatus(PlayerStatus.LOSE);
            } else if (player.getStatus() == PlayerStatus.BLACKJACK) {
                if (dealer.getStatus() == DealerStatus.BLACKJACK)
                    player.setStatus(PlayerStatus.DRAW);
                else
                    player.setStatus(PlayerStatus.WIN);
            } else {
                if (player.getScore() > dealer.getScore())
                    player.setStatus(PlayerStatus.WIN);
                else if (player.getScore() < dealer.getScore())
                    player.setStatus(PlayerStatus.LOSE);
                else
                    player.setStatus(PlayerStatus.DRAW);
            }
        }

    }

}
