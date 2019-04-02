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
}
