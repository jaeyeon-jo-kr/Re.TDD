package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;

public enum Rule {
    CLASSIC;

    public boolean isOver(Round round) {
        for (Playable player : round.users()) {
            if (player.status() == Status.PLAYING) {
                return false;
            }
        }
        return true;
    }

    public Playable updateScore(Playable player) {
        int aceCount = 0;
        int score = 0;

        for (Card card : player.hands()) {
            if (card.value() == 1) {
                aceCount += 1;
            } else {
                score += card.value();
            }
        }
        for (int i = 0; i < aceCount; i++) {
            if (score + 11 <= 21) {
                score += 11;
            } else {
                score += 1;
            }
        }
        player.setScore(score);
        return player;
    }

    public Playable updateStatus(Playable player) {
        if (player.score() > 21)
            player.setStatus(Status.BUST);
        else if (player.score() == 21)
            player.setStatus(Status.BLACKJACK);
        return player;
    }

    public void finalizeStatus(Dealer dealer, List<Player> players) {
        
        for (Playable player : players) {
            if (player.status() == Status.BUST) {
                player.setStatus(Status.LOSE);
            } else if (player.status() == Status.BLACKJACK) {
                if(dealer.status() == Status.BLACKJACK)
                    player.setStatus(Status.DRAW);
                else
                    player.setStatus(Status.WIN);                                
            } else {
                if (player.score() > dealer.score()) 
                    player.setStatus(Status.WIN);
                else if (player.score() < dealer.score()) 
                    player.setStatus(Status.LOSE);
                else 
                    player.setStatus(Status.DRAW);                
            }
        }

    }

    public int bestScore(Playable[] players) {
        int bestScore = 0;
        for (Playable player : players) {
            if (player.score() <= 21)
                bestScore = Math.max(bestScore, player.score());
        }
        return bestScore;

    }

    public DealerCommand getDealerCommand(Dealer dealer)
    {
        DealerCommand command = DealerCommand.NONE;

        if(dealer.score()<=16){
            command = DealerCommand.DRAW;
        }else if(dealer.score() >= 17){
            command = DealerCommand.STAND;
        }

        return command;
    }

    public List<PlayerCommand> getPlayerCommand(Player player, Dealer dealer)
    {
        List<PlayerCommand> commands = new ArrayList<PlayerCommand>();
        if(player.score() < 21){
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);
            if(dealer.score()>=17){
                commands.add(PlayerCommand.STAND);    
            }
        }
        else if(player.score()>=21){
            commands.add(PlayerCommand.STAND);
        }
        return commands;
    }

}
