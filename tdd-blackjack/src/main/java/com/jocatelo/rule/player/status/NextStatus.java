package com.jocatelo.rule.player.status;

import com.jocatelo.character.Player;

interface NextStatus {
    PlayerStatus next(Player player);
}