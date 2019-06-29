package com.jocatelo.rule.player;

import java.util.ArrayList;
import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Drawable;
import com.jocatelo.character.Player;
import com.jocatelo.rule.Command;
import com.jocatelo.rule.player.status.*;


public enum PlayerCommand implements Command {
    NONE("NONE"){
        public void execute(Drawable drawable, Player player) {
        }
    },
    
    SURRENDER("SURRENDER"){
        public void execute(Drawable drawable, Player player) {
            player.setStatus(PlayerStatus.SURRENDER);
        }
    },    
    HIT("HIT"){

		@Override
		public void execute(Drawable drawable, Player player) {
            Card card = drawable.popCard();
            player.addCard(card);
		}
    },
    STAND("STAND"){

        @Override
        public void execute(Drawable drawable, Player player) {

        }
        
    },
    DOUBLEDOWN("DOUBLEDOWN"){

        @Override
        public void execute(Drawable drawable, Player player) {

        }
        
    }, 
    SPLIT("SPLIT"){
        
        @Override
        public void execute(Drawable drawable, Player player) {

        }
    };
    private final String symbol;
    PlayerCommand(String symbol){
        this.symbol = symbol;
    }

    @Override public String toString(){
        return symbol;
    }    
    public abstract void execute(Drawable drawable, Player player);
    public static List<PlayerCommand> getAvailable(Player player){
        List<PlayerCommand> commands = new ArrayList<>();
        
        if (player.getScore() < 21) {
            commands.add(PlayerCommand.HIT);
            commands.add(PlayerCommand.DOUBLEDOWN);
            commands.add(PlayerCommand.SPLIT);
            return commands;
        }
        
        commands.add(PlayerCommand.STAND);
        
        return commands;
    }
}