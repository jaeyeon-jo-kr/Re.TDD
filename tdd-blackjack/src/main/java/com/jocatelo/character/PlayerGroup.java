package com.jocatelo.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class PlayerGroup {
    private final List<Player> players;
    private final int number;

    private PlayerGroup(int playerNumber) {
        players = new ArrayList<>(8);
        number = playerNumber;
    }

    static public PlayerGroup of(int number) {
        assert (number >= 1 && number <= 8);
        return new PlayerGroup(number);
    }

    static public PlayerGroup of() {
        int DEFAULT_PLAYER_NUMBER = 1;
        return new PlayerGroup(DEFAULT_PLAYER_NUMBER);
    }

    public void add(Player player) {
        players.add(player);
    }

    public Player get(int index)
    {
        return players.get(0);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    void createPlayers(){
        IntStream.rangeClosed(1, number).forEach(i -> players.add(Player.of("player " + i)));
    }

}