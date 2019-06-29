package com.jocatelo;

import com.jocatelo.card.Card;
import com.jocatelo.card.CardRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardRepositoryTest {
    private CardRepository repository;

    @Before
    public void initialize()
    {
        repository = CardRepository.of();
    }

    @Test
    public void createCards()
    {
        assertEquals(52, repository.getCardCount());
    }


    @Test
    public void createSpade()
    {
        Card card = repository.getCard(Card.Type.SPADE, Card.Number.A);
        assertTrue(card.isSpade());
    }

    @Test
    public void createHeart()
    {
        Card card = repository.getCard(Card.Type.HEART, Card.Number.A);
        assertTrue(card.isHeart());
    }

    @Test
    public void createDiamond()
    {
        Card card = repository.getCard(Card.Type.DIAMOND, Card.Number.A);
        assertTrue(card.isDiamond());
    }

    @Test
    public void createClover()
    {
        Card card = repository.getCard(Card.Type.CLOVER, Card.Number.A);
        assertTrue(card.isClover());
    }

}
