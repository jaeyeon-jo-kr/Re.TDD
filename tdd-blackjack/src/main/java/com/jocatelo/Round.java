
package com.jocatelo;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.DealerStatus;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.PlayerStatus;

public class Round {
    private PlayerGroup players;
    private Dealer dealer;
    private List<Turn> turns;
    private CardDeck deck;    
    private Turn current;    
    private Option option;

    private Round() {
        deck = new CardDeck();
        turns = new ArrayList<>();
        this.dealer = Dealer.of();         
        deck.initialize();
        option = Option.of();
        players = PlayerGroup.of();
        current = null;
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

    private void initializeDeck()
    {
        if(option.isShuffle())
            shuffle();

        players.getPlayers().forEach(player -> player.setDrawer(deck));
        dealer.setDrawer(deck);
    }    

    public void initialize() throws Exception
    {
        if(option.isAutomaticGeneratePlayer())  
            players.createPlayers();

        initializeDeck();
            
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
        players.getPlayers().stream().forEach(player -> {
            player.addCard(deck.popCard());
            player.addCard(deck.popCard());
        });        
        
        dealer.addCard(deck.popCard());
        dealer.addCard(deck.popCard());
        return this;
    }

    public Turn turn() {
        return current;
    }   

    
    
    public void startTurn()
    {
        if(current == null)
        {
            current = Turn.first(dealer, players);
            turns.add(current);
            return;
        }
        
        current = Turn.nextTurn(current);
        turns.add(current);
        
    }

    public void executeAll()
    {
        players.getPlayers().forEach(player -> player.execute());
        dealer.execute();
    }    

    public void updateAllStatus() throws Exception
    {
        players.getPlayers().forEach(player -> player.updateStatus());
        dealer.updateStatus();
    }

    public void endTurn(){
        current.end();        
    } 
    public void endGame()
    {
        players.getPlayers().forEach(player -> player.finalizeStatus());
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
        player.setCommand(command);
    }

    public PlayerCommand getCommand(Player player) {
        return player.getCommand();
    }
    public boolean isOver() {
        
        boolean isplaying = dealer.getStatus() == DealerStatus.PLAYING;
        for (Player player : players()) {
            isplaying = (player.getStatus() == PlayerStatus.PLAYING);
        }
        return isplaying;
    }

    

}