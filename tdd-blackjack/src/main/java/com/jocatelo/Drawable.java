package com.jocatelo;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Participants;
import com.jocatelo.character.Player;

public interface Drawable {
    public Card popCard();
    public void distributeCard(Participants particpants);
    public void execute(Commandable player);
}