package com.jocatelo.card;


import java.util.HashMap;
import java.util.Map;

public class CardRepository {
    private Map<Integer, Card> cardMap = new HashMap<>();
    private static CardRepository repository;

    private CardRepository() {
        int id = 1;

        for (var type : Card.Type.values()) {
            for (var number : Card.Number.values()) {
                cardMap.put(id++, Card.of(number, type));
            }
        }
    }

    public static CardRepository of() {
        if (repository == null)
            repository = new CardRepository();
        return repository;
    }

    public int getCardCount() {
        return cardMap.size();
    }

    public Card getCard(Card.Type type, Card.Number number) {
        return cardMap.values().stream()
                .filter(card -> card.isType(type))
                .filter(card -> card.isNumber(number))
                .findFirst()
                .orElse(null);
    }

    public Card getCard(int id)
    {
        return cardMap.getOrDefault(id, null);
    }
}
