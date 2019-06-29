package com.jocatelo;

import com.jocatelo.card.CardInstanceSet;
import com.jocatelo.card.CardRepository;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CardInstanceSetTest {

    @Test
    public void createCardInstanceSet()
    {
        CardRepository repository = CardRepository.of();
        CardInstanceSet instanceSet = CardInstanceSet.create(repository, 3);
        assertEquals(52*3, instanceSet.size());
    }
}
