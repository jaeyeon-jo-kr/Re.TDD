package com.jocatelo.card;

import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

public class CardInstanceSet {
    private HashMap<Integer, CardInstance> cardMap;
    private CardInstanceSet(final CardRepository repository, final int set)
    {
        cardMap = new HashMap<>();

        Random random = new Random();
        random.ints(1,set*13);
    }
    public static CardInstanceSet create(final CardRepository repository, final int set)
    {
        return new CardInstanceSet(repository, set);
    }

    public int size()
    {
        return 52*3;
    }
}
