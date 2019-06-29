package com.jocatelo;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Participants;

public interface Drawable {
    public Card popCard();
    public void distributeCard(Participants particpants);
    public void execute(Commandable player);
}