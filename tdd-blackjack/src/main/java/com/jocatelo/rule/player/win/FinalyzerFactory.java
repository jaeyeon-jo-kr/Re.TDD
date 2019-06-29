package com.jocatelo.rule.player.win;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.dealer.DealerStatus;
import com.jocatelo.rule.player.status.PlayerStatus;

public abstract class FinalyzerFactory {

    
    static public Finalyzable create(Player player)
    {
        
        PlayerStatus status = player.getStatus();
        switch(status)
        {
            case STAND:
            case PLAYING:
            return (Dealer dealer)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK || 
                player.getScore() < dealer.getScore())
                    return WinStatus.LOSE;
    
                if(dealer.getScore() < player.getScore())
                    return WinStatus.WIN;
                return WinStatus.DRAW;
            };

            case BUST:
            return (Dealer dealer)->
            {
                return WinStatus.LOSE;
            };

            case BLACKJACK:
            return (Dealer dealer)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK)
                    return WinStatus.PUSH;
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