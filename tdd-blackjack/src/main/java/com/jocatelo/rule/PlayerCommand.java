package com.jocatelo.rule;

import java.util.ArrayList;
import com.jocatelo.Round;
import com.jocatelo.character.Player;


public enum PlayerCommand {
    NONE("NONE"){
        public void execute(Round round, Player player){

        }
    },     
    SURRENDER("SURRENDER"){
        public void execute(Round round, Player player){
            player.setStatus(Status.SURRENDER);
        }
    },    
    HIT("HIT"){
        public void execute(Round round, Player player){
            round.drawCard(player);
        }
    },
    STAND("STAND"){
        public void execute(Round round, Player player){
        }
    },
    DOUBLEDOWN("DOUBLEDOWN"){
        public void execute(Round round, Player player){
        }
    }, 
    SPLIT("SPLIT"){
        public void execute(Round round, Player player){
    }};
    private String symbol;
    PlayerCommand(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }    
    public abstract void execute(Round round, Player player);
}