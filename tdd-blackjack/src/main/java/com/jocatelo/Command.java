package com.jocatelo;

import com.jocatelo.User.Status;

public enum Command {
    NONE, DRAW, FOLD, HOLD;

    public void execute(Round round, Playable user) {
        if (this == DRAW) {
            round.draw(user);
        } else if (this == FOLD) {
            user.setStatus(Status.FOLD);
        } else if(this == HOLD)
        {
            
        }
    }
}