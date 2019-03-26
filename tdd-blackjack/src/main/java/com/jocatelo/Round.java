
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

public class Round {
    private List<Player> players;
    private Dealer dealer;
    private List<Playable> users;
    private List<Turn> turns;
    private CardDeck deck;
    private Rule rule;
    private Turn current;
    private int bestScore;
    

    private Round() {
        players = new ArrayList<>(8);
        users = new ArrayList<>(8);
        deck = new CardDeck();
        turns = new ArrayList<>();
        this.dealer = User.createDealer(this);            
        users.add(dealer);
        dealer.setIndex(0);
        deck.initialize();
        rule = Rule.CLASSIC;
    }

    public static Round create() {
        return new Round();
    }

    public Dealer dealer()
    {
        return this.dealer;
    }

    public Round setPlayerNumber(int number) throws InvalidValueException {
        
        if (number >= 1 && number <= 8) {
            for (int i = 1; i <= number; i++) {
                String name = "player " + i;
                Player player = User.createPlayer(this, name);                
                players.add(player);
                users.add(player);
                player.setIndex(i);
                
            }
        }
        return this;
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
            rule.updateScore(user);
            rule.updateStatus(user);
        }
        if (isOver()) {
            rule.finalizeStatus(dealer, players());
        } else {
            Turn newTurn = Turn.create(current.number() + 1).initialize(users());
            turns.add(newTurn);
            current = newTurn;
        }        
        return this;
    }    

    public int bestScore() {
        return rule.bestScore(this.users());
    }

    public Player[] players()
    {
        return players.toArray(new Player[players.size()]);
    }

}