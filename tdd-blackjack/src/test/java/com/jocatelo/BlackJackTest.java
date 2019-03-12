package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;


public class BlackJackTest
{
    /**
     * 카드 2장을 각각 딜러와 플레이어에게 주면 딜러와 플레이어는 카드 2장을 가지고 있어야 한다.
     */
    @Test
    public void SendTwoCard(){
        Dealer dealer = new Dealer();
        Player player = new Player();
        
        CardDeck deck = new CardDeck();
        deck.initialize();

        Card card = deck.popCard();
        dealer.addCard(card);
        card = deck.popCard();
        dealer.addCard(card);


        card = deck.popCard();
        player.addCard(card);
        card = deck.popCard();
        player.addCard(card);
        
        assertEquals(player.getCardCount(), 2);
        assertEquals(dealer.getCardCount(), 2);
    }


}