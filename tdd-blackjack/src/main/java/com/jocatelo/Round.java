
package com.jocatelo;

import com.jocatelo.card.CardDeck;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;
import com.jocatelo.rule.player.PlayerCommand;
import com.jocatelo.turn.Turn;
import com.jocatelo.turn.Turns;

import lombok.Getter;

public class Round {    
    private final Turns turns;
    @Getter
    private final CardDeck deck;
    private final GameOption option;
    @Getter
    private final Participants participants;

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

    public Round setAutomaticDistribute(boolean ok){
        option.setAutomaticDistribute(ok);
        return this;
    }

    private void initializeDeck()
    {
        if(option.isShuffle())
            shuffle();

        //players.getPlayers().forEach(player -> player.setDrawer(deck));
        //dealer.setDrawer(deck);
    }    

    public void initialize()
    {
        if(option.isAutomaticGeneratePlayer())  
            participants.createPlayers();

        initializeDeck();
            
        if(option.isAutomaticDistribute())
            distribute();
    }

    private void shuffle() {
        deck.shuffle();
    }

    /**
     * At the round of initial, players must have two cards.
     */
    private void distribute() {
        deck.distributeCard(participants);
    }
    public void executeAll()
    {
        participants.executeAll(deck);
    }    

    public void startTurn()
    {
        turns.newTurn();
        Turn turn = turns.getCurrent();
        turn.start(participants.getPlayers());
    }
    public void updateAllStatus() throws Exception
    {
        participants.updateAllStatus();
    }

    public void endTurn(){
        Turn turn = turns.getCurrent();
        turn.end();
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

    
	
    

}