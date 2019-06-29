package com.jocatelo;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Participants;

public interface Drawable {
    Card popCard();
    void distributeCard(Participants particpants);
    void execute(Commandable player);
}