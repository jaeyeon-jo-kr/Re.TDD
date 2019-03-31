package com.jocatelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.jocatelo.character.Playable;
import com.jocatelo.character.User;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
public class ScoreTest
{
    @Test
    public void checkScore12() throws Exception {
        Playable user = User.createPlayer("user1");
        user.add(Card.diamond(1));
        user.add(Card.diamond(1));
        user.updateScore();
        assertThat(user.score()).isEqualTo(12);
    }

    @Test
    public void checkScore13() throws Exception {
        Playable user = User.createPlayer("user1");
        user.add(Card.heart(1));
        user.add(Card.heart(1));
        user.add(Card.diamond(1));
        user.updateScore();
        assertThat(user.score()).isEqualTo(13);
    }

    @Test
    public void checkBlackJackScoreWithTwoCards() throws Exception {
        Playable user = User.createPlayer("user1");
        user.add(Card.heart(1));
        user.add(Card.heart(10));
        user.updateScore();
        assertThat(user.score()).isEqualTo(21);
    }

    
    @Test
    public void checkBlackJackScoreWithThreeCards() throws Exception {
        Playable user = User.createPlayer("user1");
        user.add(Card.heart(1));
        user.add(Card.heart(10));
        user.add(Card.heart(10));
        user.updateScore();
        assertThat(user.score()).isEqualTo(21);
    }
}