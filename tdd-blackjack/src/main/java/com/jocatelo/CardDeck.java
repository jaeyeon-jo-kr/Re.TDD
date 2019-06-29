package com.jocatelo;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Queue;
import java.util.Random;

import com.jocatelo.character.Commandable;
import com.jocatelo.character.Dealer;
import com.jocatelo.character.Participants;
import com.jocatelo.character.PlayerGroup;

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

    public void shuffle() {
        Card[] array = cardQueue.toArray(new Card[cardQueue.size()]);
        Random random = new Random(Calendar.getInstance().getTimeInMillis());

        for (int i = 0; i < TOTAL_COUNT; i++) {
            int randomIndex = Math.abs(random.nextInt()) % cardQueue.size();
        }
        cardQueue.clear();
        cardQueue.addAll(Arrays.asList(array));

    }

    private void distributeToPlayers(PlayerGroup players)
    {
        players.getPlayers().forEach(player -> {
            player.addCard(popCard());
            player.addCard(popCard());
        });
    }

    private void distributeToDealer(Dealer dealer){
        dealer.addCard(popCard());
        dealer.addCard(popCard());
    }   

    @Override
    public void distributeCard(Participants participants) {
        distributeToPlayers(participants.getPlayers());
        distributeToDealer(participants.getDealer());        
    }

    @Override
    public void execute(Commandable player) {

    }
}