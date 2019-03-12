package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;



public class BlackJackTest
{
    /**
     * 카드 2장을 각각 딜러와 플레이어에게 주면 딜러와 플레이어는 카드 2장을 가지고 있어야 한다.
     * 플레이어는 2~8명이다.
     */
    @Test
    public void SendTwoCard(){
        List<Player> players = new ArrayList<>(8);

        Dealer dealer = new Dealer();
        players.add(dealer);
        for(int i = 1 ; i < 8; i++)
        {
            Player player = new Player();
            players.add(player);
        }

        CardDeck deck = new CardDeck();
        deck.initialize();


        for(Player player:players)
        {
            Card card = deck.popCard();
            player.addCard(card);
            card = deck.popCard();
            player.addCard(card);
        }
        
        for(Player player:players)
        {
            assertEquals(player.getCardCount(), 2);        
        }
        
    }

}