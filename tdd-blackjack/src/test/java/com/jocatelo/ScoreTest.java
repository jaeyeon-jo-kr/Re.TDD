package com.jocatelo;


import com.jocatelo.card.Card;
import com.jocatelo.character.Hands;
import com.jocatelo.character.Player;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
public class ScoreTest
{
    @Test
    public void checkScore12() {
        Player player = Player.of("user1");
        Hands hands = player.getHands();
        hands.add(Card.diamond(1));
        hands.add(Card.diamond(1));
        
        assertThat(player.getScore()).isEqualTo(12);
    }

    @Test
    public void checkScore13() {
        Player player = Player.of("user1");
        Hands hands = player.getHands();
        hands.add(Card.heart(1));
        hands.add(Card.heart(1));
        hands.add(Card.diamond(1));

        assertThat(player.getScore()).isEqualTo(13);
    }

    @Test
    public void checkBlackJackScoreWithTwoCards() {
        Player player = Player.of("user1");
        Hands hands = player.getHands();

        hands.add(Card.heart(1));
        hands.add(Card.heart(10));
        
        assertThat(player.getScore()).isEqualTo(21);
    }

    
    @Test
    public void checkBlackJackScoreWithThreeCards() {
        Player player = Player.of("user1");
        Hands hands = player.getHands();

        hands.add(Card.heart(1));
        hands.add(Card.heart(10));
        hands.add(Card.heart(10));
        
        assertThat(player.getScore()).isEqualTo(21);
    }
}