package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;

public interface Commandable {
    public void setCommand(Player player, PlayerCommand command);
    public PlayerCommand getCommand(Player player);
}