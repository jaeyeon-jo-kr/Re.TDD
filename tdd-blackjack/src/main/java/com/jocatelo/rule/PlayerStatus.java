package com.jocatelo.rule;

import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public enum PlayerStatus {

    WIN("WIN"){
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }

        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
        
    },
    LOSE("LOSE") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }

        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
    },
    DRAW("DRAW") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }

        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
    },    
    STAND("STAND") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }

        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }

    },
    BUST("BUST") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }

        @Override
        public PlayerStatus finalize(Player player) {
            return LOSE;
        }
    },
    PLAYING("PLAYING") {
        @Override
        public PlayerStatus next(Player player) {
            if(player.getScore() == BLACKJACK_SCORE && player.getHands().getCardCount() == 2)
                return BLACKJACK;

            if(player.getScore() == BLACKJACK_SCORE)
                return STAND;
            
            if(player.getScore() > BLACKJACK_SCORE)
                return BUST;

            return this;
        }
        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
    },
    BLACKJACK("BLACKJACK") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }
        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
    },
    SURRENDER("SURRENDER") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }
        @Override
        public PlayerStatus finalize(Player player) {            
            return this;
        }
    },
    PUSH("PUSH") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }
        @Override
        public PlayerStatus finalize(Player player) {
            return this;
        }
    };

    
    private static final int BLACKJACK_SCORE = 21;

    private String symbol;
    PlayerStatus(String symbol)
    {
        this.symbol = symbol;
    }

    public abstract PlayerStatus next (Player player);
    public abstract PlayerStatus finalize (Player player);

	@Override
	public String toString() {
		return symbol;
	}

    
    
}