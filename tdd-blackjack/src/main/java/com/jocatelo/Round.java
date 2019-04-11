
package com.jocatelo;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.player.PlayerCommand;
import com.jocatelo.turn.Turns;

import lombok.Getter;

public class Round {    
    private Turns turns;
    private CardDeck deck;
    private GameOption option;
    @Getter
    private Participants participants;

    private Round() {
        deck = new CardDeck();        
        participants = Participants.of();
        deck.initialize();
        option = GameOption.of();        
        turns = Turns.of();
    }

    public static Round of() {
        return new Round();
    }

    public Dealer dealer()
    {
        return participants.getDealer();
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
        participants.setPlayerNumber(number);
        return this;
    }

    private void initializeDeck()
    {
        if(option.isShuffle())
            shuffle();

        //players.getPlayers().forEach(player -> player.setDrawer(deck));
        //dealer.setDrawer(deck);
    }    

    public void initialize() throws Exception
    {
        if(option.isAutomaticGeneratePlayer())  
            participants.createPlayers();

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
        deck.distributeCard(participants);
        return this;
    }
    public void executeAll()
    {
        participants.executeAll(deck);
    }    

    public void startTurn()
    {
        turns.start();
    }
    public void updateAllStatus() throws Exception
    {
        participants.updateAllStatus();
    }

    public void endTurn(){
        turns.end();
    } 
    public void endGame()
    {
        participants.endGame();
    }    

    public void setCommand(Player player, PlayerCommand command) {
        player.setCommand(command);
    }

    public PlayerCommand getCommand(Player player) {
        return player.getCommand();
    }
    public boolean isOver() {        
        return participants.isOver();
    }

    public WinStatus getWinStatus(Player player)
    {
        return player.getWinStatus();
    }

	public Object getWinningCredit(Player player) {
        return player.getWinningCredit();
	}

    

}