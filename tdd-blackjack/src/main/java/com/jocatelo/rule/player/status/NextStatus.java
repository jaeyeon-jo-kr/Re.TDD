package com.jocatelo.rule.player.status;

import com.jocatelo.character.Player;

public interface NextStatus {
    public PlayerStatus next (Player player);
}