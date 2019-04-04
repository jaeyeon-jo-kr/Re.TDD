
package com.jocatelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.DelayQueue;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.character.User;
import com.jocatelo.rule.DealerCommand;
import com.jocatelo.rule.DealerStatus;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.PlayerStatus;

public class Round {
    private PlayerGroup players;
    private Dealer dealer;
    private List<Turn> turns;
    private CardDeck deck;
    private Rule rule;
    private Turn current;    
    private Option option;

    private Round() {
        deck = new CardDeck();
        turns = new ArrayList<>();
        this.dealer = Dealer.of();         
        deck.initialize();
        rule = Rule.CLASSIC;
        option = Option.of();
        players = PlayerGroup.of();
    }

    public static Round of() {
        return new Round();
    }

    public Dealer dealer()
    {
        return this.dealer;
    }

    public Round setPlayerGeneration(boolean ok){
        option.setAutomaticGeneratePlayer(ok);
        return this;
    }

    public Round setAutomaticDistribute(boolean ok){
        option.setAutomaticDistribute(ok);
        return this;
    }
    public Round setPlayerNumber(int number)
    {
        players = PlayerGroup.of(number);
        return this;
    }

    public void initialize() throws Exception
    {
        if(option.isAutomaticGeneratePlayer())  
            players.createPlayers();

        if(option.isShuffle())
            shuffle();
            
        if(option.isAutomaticDistribute())
            distribute();

        players.getPlayers().forEach(player -> player.setDealer(dealer));
    }

    public Round shuffle() {
        deck.shuffle();
        return this;
    }

    /**
     * At the round of initial, players must have two cards.
     */
    public Round distribute() {
        for (User user : players.getPlayers()) {
            user.addCard(deck.popCard());
            user.addCard(deck.popCard());
        }
        dealer.addCard(deck.popCard());
        dealer.addCard(deck.popCard());
        return this;
    }

    public Turn turn() {
        return current;
    }   

    
    public Round start() {
        current = Turn.create(1).initialize(players());
        turns.add(current);
        return this;
    }
    public void startTurn()
    {
        Turn newTurn = Turn.create(current.number() + 1).initialize(players());
        turns.add(newTurn);
        current = newTurn;
    }

    public void endTurn() throws Exception{
        
        for (Player player : players.getPlayers()) {
            PlayerCommand command = current.what(player);
            command.execute(deck, player);
            player.updateScore();
            player.updateStatus();
        }
        DealerCommand command = DealerCommand.getAvailable(dealer);
        command.execute(deck, dealer);
        dealer.updateScore();
        dealer.updateStatus();
    } 
    public void endGame()
    {
        players.getPlayers().stream().forEach(player -> player.finalizeStatus());
    }
    public List<Player> players()
    {
        return players.getPlayers();
    }

    public Player getPlayer(int index)
    {
        return players.getPlayers().get(index);
    }
    
    

    public void setCommand(Player player, PlayerCommand command) {
        current.setPlayerCommand(player, command);
    }

    
    public PlayerCommand getCommand(Player player) {
        return current.what(player);
    }

    public boolean isOver() {
        
        boolean isplaying = dealer.getStatus() == DealerStatus.PLAYING;
        for (Player player : players()) {
            isplaying = (player.getStatus() == PlayerStatus.PLAYING);
        }
        return isplaying;
    }

    

}