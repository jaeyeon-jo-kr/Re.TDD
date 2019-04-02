package com.jocatelo.rule;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;

import lombok.ToString;

public enum DealerStatus {

    

    PLAYING("PLAYING"){
        
        public DealerStatus next(Dealer dealer){
            
            if(dealer.getScore() < STAND_MIN_SCORE)
                return this;

            if(dealer.getScore() == BLACKJACK_SCORE
                && dealer.getHands().getCardCount() == 2)
                return BLACKJACK;

            return STANDING;
        }
    },

    STANDING("STANDING"){
        public DealerStatus next(Dealer dealer) throws Exception {
            
            if(dealer.getScore() < STAND_MIN_SCORE)
                throw new IllegalStatusException();

            if(dealer.getScore() == BLACKJACK_SCORE
                && dealer.getHands().getCardCount() == 2)
                return BLACKJACK;
            
            if(dealer.getScore() > BLACKJACK_SCORE)
                return BUST;
                
            return this;
        }
    },
    BLACKJACK("BLACKJACK"){
        public DealerStatus next(Dealer dealer) throws Exception {            
            
            if(!(dealer.getScore() == BLACKJACK_SCORE
                && dealer.getHands().getCardCount() == 2))
                throw new IllegalStatusException();
                
            return BLACKJACK;
        }
    }, 
    BUST("BUST"){
        public DealerStatus next(Dealer dealer) throws Exception {
            if(dealer.getScore() <= BLACKJACK_SCORE)
                throw new IllegalStatusException();
                
            return BUST;
        }
    };

    private final static int BLACKJACK_SCORE = 21;
    private final static int STAND_MIN_SCORE = 17;

    private String symbol;
    
    DealerStatus(String symbol) {
        this.symbol = symbol;
    }

    public abstract DealerStatus next(Dealer dealer) throws Exception;
    
    @Override
    public String toString(){
        return symbol;
    }
}