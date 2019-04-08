
package com.jocatelo;

import java.util.List;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;
import com.jocatelo.character.PlayerGroup;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.dealer.DealerStatus;
import com.jocatelo.rule.player.Finalyzable;
import com.jocatelo.rule.player.FinalyzerFactory;
import com.jocatelo.rule.player.PlayerCommand;
import com.jocatelo.rule.player.PlayingStatus;
import com.jocatelo.turn.Turns;

public class Round {
    private PlayerGroup players;
    private Dealer dealer;
    private Turns turns;
    private CardDeck deck;
    private Option option;

    private Round() {
        deck = new CardDeck();        
        dealer = Dealer.of();         
        deck.initialize();
        option = Option.of();
        players = PlayerGroup.of();
        turns = Turns.of();
    
    }

    public static Round of() {
        return new Round();
    }

    public Dealer dealer()
    {
        return dealer;
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

    public void startTurn()
    {
        turns.start();
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
        turns.end();
    } 
    public void endGame()
    {
        players.getPlayers().forEach((Player player) -> {            
            Finalyzable finalyzable = FinalyzerFactory.create(player);
            WinStatus winStatus = finalyzable.finalizeStatus(dealer);
            player.setWinStatus(winStatus);
        }
        );
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
            isplaying = (player.getStatus() == PlayingStatus.PLAYING);
        }
        return isplaying;
    }

    public WinStatus getWinStatus(Player player)
    {
        return player.getWinStatus();
    }

	public Object getWinningCredit(Player player) {
        return player.getWinningCredit();
	}

    

}