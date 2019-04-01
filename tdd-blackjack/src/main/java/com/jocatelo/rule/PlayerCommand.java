package com.jocatelo.rule;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Round;
import com.jocatelo.character.Player;


public enum PlayerCommand {
    NONE("NONE"){
        public void execute(Round round, Player player){

        }
    },     
    SURRENDER("SURRENDER"){
        public void execute(Round round, Player player){
            player.setStatus(PlayerStatus.SURRENDER);
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
    public static List<PlayerCommand> getAvailable(Player player){
        List<PlayerCommand> commands = new ArrayList<PlayerCommand>();
        
        if (player.getScore() < 21) {
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);            
        } else if (player.getScore() >= 21) {
            commands.add(PlayerCommand.STAND);
        }
        return commands;
    }
}