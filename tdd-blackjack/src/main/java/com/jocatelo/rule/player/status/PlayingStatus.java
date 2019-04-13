package com.jocatelo.rule.player.status;

import com.jocatelo.character.Player;

public class PlayingStatus implements NextStatus {
    private static final int BLACKJACK_SCORE = 21;

    private boolean isBlackJack(Player player){
        return player.getScore() == BLACKJACK_SCORE && player.getHands().getCardCount() == 2;
    }

    private boolean isBust(Player player)
    {
        return player.getScore() > BLACKJACK_SCORE;
    }
    
    private boolean isMaxScore(Player player)
    {
        return player.getScore() == BLACKJACK_SCORE && player.getHands().getCardCount() != 2;
    }
    @Override
    public PlayerStatus next(Player player) {
        if(isBlackJack(player))
            return PlayerStatus.BLACKJACK;            
        if(isMaxScore(player))
            return PlayerStatus.STAND;            
        if(isBust(player))
            return PlayerStatus.BUST;
        return PlayerStatus.PLAYING;
    }
}