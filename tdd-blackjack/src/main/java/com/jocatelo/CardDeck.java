package com.jocatelo;

import java.util.ArrayDeque;
import java.util.Queue;

import com.jocatelo.Card.Number;
import com.jocatelo.Card.Type;

public class CardDeck {
    final private Queue<Card> cardQueue;
    final private int TOTAL_COUNT = 13*4;
    public CardDeck()
    {
        cardQueue = new ArrayDeque<>(TOTAL_COUNT);
        
    }

    public Card popCard()
    {
        return cardQueue.poll();
    }

	public void initialize() {
        for (Card.Type cardType : Card.Type.values()) {
            for(Card.Number cardNumber:Card.Number.values()){
                Card card = Card.create(cardNumber, cardType);
                cardQueue.add(card);
            }
        }

	}
}