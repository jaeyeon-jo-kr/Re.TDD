package com.jocatelo;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import com.jocatelo.Player.Status;

import java.util.ArrayList;



public class BlackJackTest
{   
    @Test
    public void sendTwoCard(){
        Round round = Round.round().setPlayerNumber(8).shuffle().distribute();
        
        for(Player player:round.getPlayer())
        {
            assertEquals(player.getCardCount(), 2);        
        }

        for(Player player:round.getPlayer()){
            if(player.status == Status.PLAYING){
                round.draw(player);
            }
        }

        for(Player player:round.getPlayer()){
            assertTrue((player.score() == 21 && player.status() == Status.BLACKJACK) ||
                (player.score() > 21 && player.status() == Status.BUST) ||
                (player.score() < 21 && player.status() == Status.PLAYING)
            );
        }

        
    }

    @Test
    public void cardStatus()
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

    @Test
    public void checkBlackJackStatus()
    {
        CardDeck deck = new CardDeck();
        deck.initialize();

        Card card1 = Card.clover(Card.Number.N10);
        Card card2 = Card.clover(Card.Number.N2);

        Player player = new Player();
        player.draw(card1);
        player.draw(card2);

        assertEquals(player.score(), 12);

        Player player2 = new Player();
        player2.draw(Card.clover(Card.Number.A));
        player2.draw(Card.clover(Card.Number.N8));

        assertEquals(player2.score(), 19);

        Player player3 = new Player();
        player3.draw(Card.clover(Card.Number.A));
        player3.draw(Card.clover(Card.Number.N10));

        assertEquals(player3.score(), 21);

        Player player4 = new Player();
        player4.draw(Card.clover(Card.Number.N3));
        player4.draw(Card.clover(Card.Number.K));
        player4.draw(Card.clover(Card.Number.J));

        assertEquals(player4.score(), 23);
        

        
    }



}