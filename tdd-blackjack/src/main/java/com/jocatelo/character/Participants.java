package com.jocatelo.character;

import com.jocatelo.Drawable;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.dealer.DealerStatus;
import com.jocatelo.rule.player.win.Finalyzable;
import com.jocatelo.rule.player.win.FinalyzerFactory;
import com.jocatelo.rule.player.status.PlayerStatus;

import lombok.Getter;


public class Participants{
    @Getter
    private PlayerGroup players;
    @Getter
    private final Dealer dealer;

    private Participants()
    {
        dealer = Dealer.of();
        players = PlayerGroup.of();
    } 
    public static Participants of()
    {
        return new Participants();
    }

    public void setPlayerNumber(int number){
        players = PlayerGroup.of(number);
    }

    public void createPlayers()
    {
        players.createPlayers();
    }

    public void executeAll(Drawable drawable)
    {
        players.getPlayers().forEach(player -> player.execute(drawable));
        dealer.execute(drawable);
    }

    public void updateAllStatus() throws Exception
    {
        players.getPlayers().forEach(Player::updateStatus);
        dealer.updateStatus();
    }

    public void endGame()
    {
        for (Player player : players.getPlayers()) {
            Finalyzable finalyzable = FinalyzerFactory.create(player);
            if (finalyzable != null) {
                WinStatus winStatus = finalyzable.finalizeStatus(dealer);
                player.setWinStatus(winStatus);
            }
        }
    }

    public boolean isOver()
    {
        boolean isplaying = dealer.getStatus() == DealerStatus.PLAYING;
        for (Player player : players.getPlayers()) {
            isplaying = (player.getStatus() == PlayerStatus.PLAYING);
        }
        return isplaying;
    }
}