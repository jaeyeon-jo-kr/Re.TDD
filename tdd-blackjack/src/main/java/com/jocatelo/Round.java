
package com.jocatelo;

import java.util.ArrayList;
import java.util.List;

public class Round
{
    private List<Player> players;
    private CardDeck deck;
    
    private Round()
    {
        players = new ArrayList<>(8);
        deck = new CardDeck();
        deck.initialize();
        

    }
    public static Round round()
    {
        return new Round();
    }

    public Round setPlayerNumber(int number)
    {
        if(number >=2  && number <=8)
        {
            Dealer dealer = new Dealer();
            players.add(dealer);

            for(int i = 1 ; i < 8; i++)
            {
                Player player = new Player();
                players.add(player);
            }
        }

        return this;
    }

    public Round shuffle()
    {
        deck.shuffle();
        return this;
    }

    /**
     * At the round of initial, players must have two cards.
     */
    public Round distribute()
    {
        for(Player player:players)
        {
            Card card = deck.popCard();
            player.add(card);
            card = deck.popCard();
            player.add(card);
        }

        return this;
    }

    public Round draw(Player player)
    {
        Card card = deck.popCard();        
        player.draw(card);
        return this;
    }

    public Player[] getPlayer()
    {
        return players.toArray(new Player[players.size()]);
    }


}