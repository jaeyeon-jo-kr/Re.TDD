package com.jocatelo.rule.player.status;

import com.jocatelo.character.Player;

public enum PlayerStatus implements NextStatus{
    STAND("STAND") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }
    },
    BUST("BUST") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }
    },
    PLAYING("PLAYING") {
        
        @Override
        public PlayerStatus next(Player player) {
            return new PlayingStatus().next(player);
        }
    },
    BLACKJACK("BLACKJACK") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }      
    },
    SURRENDER("SURRENDER") {
        @Override
        public PlayerStatus next(Player player) {
            return this;
        }    
    };    

    private final String symbol;
    PlayerStatus(String symbol)
    {
        this.symbol = symbol;
    }

    public abstract PlayerStatus next (Player player);
    

	@Override
	public String toString() {
		return symbol;
	}
}