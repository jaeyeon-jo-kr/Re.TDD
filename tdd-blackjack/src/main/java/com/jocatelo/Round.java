
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
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.PlayerStatus;

public class Round implements Drawable {
    private PlayerGroup players;
    private Dealer dealer;
    private List<Turn> turns;
    private CardDeck deck;
    private Rule rule;
    private Turn current;
    private int playerNumber;

    private Round() {
        players = new PlayerGroup(8);
        deck = new CardDeck();
        turns = new ArrayList<>();
        this.dealer = Dealer.of();         
        deck.initialize();
        rule = Rule.CLASSIC;
    }

    public static Round of() {
        return new Round();
    }

    public Dealer dealer()
    {
        return this.dealer;
    }

    public Round setPlayerNumber(int number) throws InvalidValueException {
        this.playerNumber = number;        
        return this;
    }

    public void initialize() throws Exception
    {
        createPlayers();
        shuffle();
        distribute();
    }

    private void createPlayers() throws InvalidValueException
    {
        if (playerNumber >= 1 && playerNumber <= 8) {
            for (int i = 1; i <= playerNumber; i++) {
                String name = "player " + i;
                Player player = Player.of(name);                
                players.add(player);
            }
        }

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
            drawCard(user);
            drawCard(user);
        }
        drawCard(dealer);
        drawCard(dealer);
        return this;
    }

    public Turn turn() {
        return current;
    }   

    
    public boolean isOver() {
        return rule.isOver(this);
    }

    public Round start() {
        current = Turn.create(1).initialize(players());
        turns.add(current);
        return this;
    }

    public Round endTurn() {
        
        for (Player player : players.getPlayers()) {
            PlayerCommand command = current.what(player);
            command.execute(this, player);
            player.updateScore();
            player.updateStatus();
        }

        Turn newTurn = Turn.create(current.number() + 1).initialize(players());
        turns.add(newTurn);
        current = newTurn;
                
        return this;
    } 

    public void endGame()
    {
        if (isOver()) {
            rule.finalizeStatus(dealer, players());
        } 

    }
    public List<Player> players()
    {
        return players.getPlayers();
    }

    
    @Override
    public void drawCard(User user) {
        Card card = deck.popCard();
        Hands hands = user.getHands();
        hands.add(card);
    }

    
    public void setCommand(Player player, PlayerCommand command) {
        current.setPlayerCommand(player, command);
    }

    
    public PlayerCommand getCommand(Player player) {
        return current.what(player);
    }

    

}