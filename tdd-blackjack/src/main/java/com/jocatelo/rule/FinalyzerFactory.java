package com.jocatelo.rule;

import java.util.function.Function;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public abstract class FinalyzerFactory {

    static public Finalyzable create(PlayingStatus status)
    {
        
        switch(status)
        {
            case STAND:
            return (Dealer dealer, Player player)->
            {
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
            };
            case BUST:
            return (Dealer dealer, Player player)->
            {
                player.setWinningRate(0.0f);
                return WinStatus.LOSE;
            };
            case PLAYING:
            return (Dealer dealer, Player player)->
            {
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
            };
            case BLACKJACK:
            return (Dealer dealer, Player player)->
            {
                if(dealer.getStatus() == DealerStatus.BLACKJACK)
                {
                    player.setWinningRate(1.0f);
                    return WinStatus.PUSH;
                }
                player.setWinningRate(2.5f);
                return WinStatus.WIN;
            };
            case SURRENDER:
            return (Dealer dealer, Player player)->
            {
                player.setWinningRate(0.0f);
                return WinStatus.LOSE;
            };
            default:
            break;
        }
        return null;
    }

}