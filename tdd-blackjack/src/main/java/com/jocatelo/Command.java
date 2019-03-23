package com.jocatelo;

import com.jocatelo.Player.Status;

public enum Command {
    NONE, DRAW, FOLD;

    public void execute(Round round, Player player) {
        if (this == DRAW) {
            round.draw(player);
        } else if (this == FOLD) {
            player.setStatus(Status.FOLD);
        }
    }
}