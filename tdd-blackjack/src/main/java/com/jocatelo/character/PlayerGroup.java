package com.jocatelo.character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerGroup {
    private List<Player> players;

    public PlayerGroup(int playerNumber){
        players = new ArrayList<>(8);
    }

    public void add(Player player)
    {
        players.add(player);
    }

    public List<Player> getPlayers()
    {
        return Collections.unmodifiableList(players);
    }


}