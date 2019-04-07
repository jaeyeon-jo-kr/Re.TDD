package com.jocatelo.rule;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.rule.dealer.DealerStatus;

public abstract class FinalyzerFactory {

    static public Finalyzable create(Player player)
    {
        PlayingStatus status = player.getStatus();
        switch(status)
        {
            case STAND:
            return (Dealer dealer)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK || 
                player.getScore() < dealer.getScore()){
                    
                    return WinStatus.LOSE;
                }
    
                if(dealer.getScore() < player.getScore())
                {
                    
                    return WinStatus.WIN;
                }                    
                
                return WinStatus.DRAW;
            };
            case BUST:
            return (Dealer dealer)->
            {
                
                return WinStatus.LOSE;
            };
            case PLAYING:
            return (Dealer dealer)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK || 
                player.getScore() < dealer.getScore()){
                    
                    return WinStatus.LOSE;
                }
    
                if(dealer.getScore() < player.getScore())
                {
                    
                    return WinStatus.WIN;
                }                    

                return WinStatus.DRAW;
            };
            case BLACKJACK:
            return (Dealer dealer)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK)
                {
                    return WinStatus.PUSH;
                }

                return WinStatus.BLACKJACK_WIN;
            };
            case SURRENDER:
            return (Dealer dealer)->
            {

                return WinStatus.LOSE;
            };
            default:
            break;
        }
        return null;
    }

    

}