package com.jocatelo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Queue;
import java.util.Random;

public class CardDeck implements Drawable {
    final private Queue<Card> cardQueue;
    final private int TOTAL_COUNT = 13 * 4;

    public CardDeck() {
        cardQueue = new ArrayDeque<>(TOTAL_COUNT);
    }

    @Override
    public Card popCard() {
        return cardQueue.poll();
    }

    public void initialize() {

        for (Card.Type cardType : Card.Type.values()) {
            for (Card.Number cardNumber : Card.Number.values()) {
                Card card = Card.of(cardNumber, cardType);
                cardQueue.add(card);
            }
        }        
    }

    public void shuffle(){
        Card array[] = cardQueue.toArray(new Card[cardQueue.size()]);
        Random random = new Random(Calendar.getInstance().getTimeInMillis());                
        
        for(int i=0;i<TOTAL_COUNT;i++){
            int randomIndex = Math.abs(random.nextInt()) % cardQueue.size();            
            swapCard(array[i], array[randomIndex]);
        }
        cardQueue.clear();
        cardQueue.addAll(Arrays.asList(array));
        
    }

    private void swapCard(Card first, Card second){
        Card temp = first;
        first = second;
        second = temp;
    }
}