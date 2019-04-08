package com.jocatelo.rule.player;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public enum PlayingStatus {

    STAND("STAND") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }
    },
    BUST("BUST") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }
    },
    PLAYING("PLAYING") {

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
        public PlayingStatus next(Player player) {
            if(isBlackJack(player))
                return BLACKJACK;            
            if(isMaxScore(player))
                return STAND;            
            if(isBust(player))
                return BUST;
            return this;
        }
    },
    BLACKJACK("BLACKJACK") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }      
    },
    SURRENDER("SURRENDER") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }    
    };
    
    private static final int BLACKJACK_SCORE = 21;

    private String symbol;
    PlayingStatus(String symbol)
    {
        this.symbol = symbol;
    }

    public abstract PlayingStatus next (Player player);
    

	@Override
	public String toString() {
		return symbol;
	}
}