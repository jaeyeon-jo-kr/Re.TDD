package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;



public class BlackJackTest
{   
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

    @Test
    public void CardStatus()
    {
        CardDeck deck = new CardDeck();
        deck.initialize();        
        
        for(int i = 1; i <= 4; i++)
        {
            for(int j=1;j<=13;j++)
            {
                Card card = deck.popCard();        

                if(j > 10){
                    assertEquals(card.value(), 10);    
                }else {
                    assertEquals(card.value(), j);    
                }

                if(j==1){
                    assertEquals(card.nextValue(), 11);
                }else if(j > 10){
                    assertEquals(card.nextValue(), 10);    
                }else {
                    assertEquals(card.nextValue(), j);    
                }                
                
                assertEquals(card.type(), Card.Type.values()[i-1]);
            }
            
        }
    }

}