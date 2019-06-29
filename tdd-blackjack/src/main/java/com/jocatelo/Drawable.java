package com.jocatelo;

import com.jocatelo.card.Card;
import com.jocatelo.character.Participants;

public interface Drawable {
    Card popCard();
    void distributeCard(Participants particpants);
}