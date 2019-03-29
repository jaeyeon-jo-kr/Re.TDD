
package com.jocatelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.DelayQueue;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Playable;
import com.jocatelo.character.Player;
import com.jocatelo.character.User;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;

public class Round {
    private final List<Player> players;
    private Dealer dealer;
    private List<Playable> users;
    private List<Turn> turns;
    private CardDeck deck;
    private Rule rule;
    private Turn current;
    private int playerNumber;

    

    private Round() {
        players = new ArrayList<>(8);
        users = new ArrayList<>(8);
        deck = new CardDeck();
        turns = new ArrayList<>();
        this.dealer = User.createDealer();            
        users.add(dealer);
        dealer.setIndex(0);
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
        distribute();
        shuffle();
    }

    private void createPlayers() throws InvalidValueException
    {
        if (playerNumber >= 1 && playerNumber <= 8) {
            for (int i = 1; i <= playerNumber; i++) {
                String name = "player " + i;
                Player player = User.createPlayer(name);                
                players.add(i-1, player);
                users.add(player);
                player.setIndex(i);
                
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
        for (Playable user : users) {
            Card card = deck.popCard();
            user.add(card);
            card = deck.popCard();
            user.add(card);
        }
        return this;
    }

    public Turn turn() {
        return current;
    }

    public Round draw(Playable user) {
        Card card = deck.popCard();
        user.draw(card);
        return this;
    }

    public Playable[] users() {
        return users.toArray(new Playable[users.size()]);
    }

    public boolean isOver() {
        return rule.isOver(this);
    }

    public Round start() {
        current = Turn.create(1).initialize(users());
        turns.add(current);
        return this;
    }

    public Round endTurn() {
        for (Playable user : users) {
            Command command = current.what(user);
            command.execute(this, user);
            user.updateScore();
            user.updateStatus();
        }

        Turn newTurn = Turn.create(current.number() + 1).initialize(users());
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
        return players;
    }

}