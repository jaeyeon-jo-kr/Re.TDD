package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.jocatelo.Card;
import com.jocatelo.Round;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;

public enum Rule {
    CLASSIC;

    private static final int BLACKJACK_SCORE = 21;

    public boolean isOver(Round round) {
        boolean isplaying = false;
        Dealer dealer = round.dealer();
        isplaying = dealer.getStatus() == Status.PLAYING;
        for (Player player : round.players()) {
            isplaying = (player.getStatus() == Status.PLAYING);
        }
        return isplaying;
    }
    
    public void updateStatus(User user) {
        if (user.getScore() > BLACKJACK_SCORE){
            user.setStatus(Status.BUST);
            return;
        }        
        user.setStatus(Status.BLACKJACK);
    }

    public void finalizeStatus(Dealer dealer, List<Player> players) {

        for (Player player : players) {
            if (player.getStatus() == Status.BUST) {
                player.setStatus(Status.LOSE);
            } else if (player.getStatus() == Status.BLACKJACK) {
                if (dealer.getStatus() == Status.BLACKJACK)
                    player.setStatus(Status.DRAW);
                else
                    player.setStatus(Status.WIN);
            } else {
                if (player.getScore() > dealer.getScore())
                    player.setStatus(Status.WIN);
                else if (player.getScore() < dealer.getScore())
                    player.setStatus(Status.LOSE);
                else
                    player.setStatus(Status.DRAW);
            }
        }

    }

    

    public DealerCommand getDealerCommand(Dealer dealer) {
        DealerCommand command = DealerCommand.NONE;

        if (dealer.getScore() <= 16) {
            command = DealerCommand.DRAW;
        } else if (dealer.getScore() >= 17) {
            command = DealerCommand.STAND;
        }

        return command;
    }

    public List<PlayerCommand> getPlayerCommand(Player player, Dealer dealer) {
        List<PlayerCommand> commands = new ArrayList<PlayerCommand>();
        if (player.getScore() < 21) {
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);
            if (dealer.getScore() >= 17) {
                commands.add(PlayerCommand.STAND);
            }
        } else if (player.getScore() >= 21) {
            commands.add(PlayerCommand.STAND);
        }
        return commands;
    }

}
