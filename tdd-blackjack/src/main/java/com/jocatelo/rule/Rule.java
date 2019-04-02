package com.jocatelo.rule;

import java.util.List;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public enum Rule {
    CLASSIC;

    private static final int BLACKJACK_SCORE = 21;

    public boolean isOver(Round round) {
        Dealer dealer = round.dealer();
        boolean isplaying = dealer.getStatus() == DealerStatus.PLAYING;
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
