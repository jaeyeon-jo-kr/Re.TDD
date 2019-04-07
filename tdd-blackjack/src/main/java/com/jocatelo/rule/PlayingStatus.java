package com.jocatelo.rule;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public enum PlayingStatus {

    STAND("STAND") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }
        @Override
        public WinStatus finalize(Player player) {
            Dealer dealer = player.getDealer();
            if(dealer.getStatus() == DealerStatus.BLACKJACK || 
            player.getScore() < dealer.getScore()){
                player.setWinningRate(0.0f);
                return WinStatus.LOSE;
            }

            if(dealer.getScore() < player.getScore())
            {
                player.setWinningRate(2.0f);
                return WinStatus.WIN;
            }

            player.setWinningRate(1.0f);
            return WinStatus.DRAW;
        }

    },
    BUST("BUST") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }

        @Override
        public WinStatus finalize(Player player) {
            player.setWinningRate(0.0f);
            return WinStatus.LOSE;
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
        @Override
        public WinStatus finalize(Player player) {
            return STAND.finalize(player);
        }
    },
    BLACKJACK("BLACKJACK") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }
        @Override
        public WinStatus finalize(Player player) {
            Dealer dealer = player.getDealer();
            if(dealer.getStatus() == DealerStatus.BLACKJACK)
            {
                player.setWinningRate(1.0f);
                return WinStatus.PUSH;
            }

            player.setWinningRate(2.5f);
            return WinStatus.WIN;
        }
    },
    SURRENDER("SURRENDER") {
        @Override
        public PlayingStatus next(Player player) {
            return this;
        }
        @Override
        public WinStatus finalize(Player player) {
            player.setWinningRate(0.0f);
            return WinStatus.LOSE;
        }
    };
    
    private static final int BLACKJACK_SCORE = 21;

    private String symbol;
    PlayingStatus(String symbol)
    {
        this.symbol = symbol;
    }

    public abstract PlayingStatus next (Player player);
    public abstract WinStatus finalize (Player player);

	@Override
	public String toString() {
		return symbol;
	}

    
    
}