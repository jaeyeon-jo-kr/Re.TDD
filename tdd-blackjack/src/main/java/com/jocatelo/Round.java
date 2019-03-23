
package com.jocatelo;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Player> players;
    private List<Turn> turns;
    private CardDeck deck;
    private Rule rule;
    private Turn current;
    private int bestScore;

    private Round() {
        players = new ArrayList<>(8);
        deck = new CardDeck();
        turns = new ArrayList<>();
        deck.initialize();
        rule = Rule.CLASSIC;
    }

    public static Round round() {
        return new Round();
    }

    public Round setPlayerNumber(int number) {
        if (number >= 2 && number <= 8) {
            Dealer dealer = new Dealer(this);
            players.add(dealer);

            for (int i = 1; i < number; i++) {
                Player player = new Player(this);
                players.add(player);
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
        for (Player player : players) {
            Card card = deck.popCard();
            player.add(card);
            card = deck.popCard();
            player.add(card);
        }
        return this;
    }

    public Turn turn() {
        return current;
    }

    public Round draw(Player player) {
        Card card = deck.popCard();
        player.draw(card);
        return this;
    }

    public Player[] players() {
        return players.toArray(new Player[players.size()]);
    }

    public boolean isOver() {
        return rule.isOver(this);
    }

    public Round start() {
        current = Turn.create(1);
        turns.add(current);
        return this;
    }

    public Round endTurn() {
        for (Player player : players) {
            Command command = current.what(player);
            execute(player, command);
            rule.updateScore(player);
            rule.updateStatus(player);
        }
        if (isOver()) {
            rule.finalizeStatus(players.toArray(new Player[players.size()]));
        } else {
            Turn newTurn = Turn.create(current.number() + 1);
            turns.add(newTurn);
            current = newTurn;
        }
        return this;

    }

    public Round execute(Player player, Command command) {
        command.execute(this, player);
        return this;
    }

    public int bestScore() {
        return rule.bestScore(this.players());
    }

}