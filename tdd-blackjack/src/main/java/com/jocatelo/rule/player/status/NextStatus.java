package com.jocatelo.rule.player.status;

import com.jocatelo.character.Player;

public interface NextStatus {
    PlayerStatus next(Player player);
}