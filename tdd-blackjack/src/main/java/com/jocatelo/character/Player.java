package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Drawable;
import com.jocatelo.rule.WinStatus;
import com.jocatelo.rule.player.PlayerCommand;
import com.jocatelo.rule.player.status.PlayerStatus;

import lombok.Getter;
import lombok.Setter;

public class Player extends User implements Playable, Commandable {

    @Setter
    private int credit;

    @Getter
    private int bet;    

    @Getter
    @Setter
    private PlayerStatus status;

    @Getter
    @Setter
    private WinStatus winStatus;

    @Getter @Setter
    private PlayerCommand command;
    
    private Player(String name) {
        super();        
        this.name = name;
        credit = 0;
        bet = 0;
        status = PlayerStatus.PLAYING;
        winStatus = WinStatus.NONE;
    }


    /**
     * create player
     * 
     */
    public static Player of(String name){
        if (name == null || name.equals(Dealer.NAME)) {
            name = "default player";
        }
        return new Player(name);
    }    

    public void bet(int bet) {
        this.bet = bet;
        this.credit = credit - bet;
    }


    @Override
    public int hashCode() {
        return name.hashCode();
    }   

    @Override
    public void updateStatus() {
        status = status.next(this);
    }

    public int getWinningCredit() {
        return (int)(bet * winStatus.getRate());        
    }

    @Override
    public void execute(Drawable drawer){
        command.execute(drawer, this);
    }

    public List<PlayerCommand> getAvailableCommands() {
        return PlayerCommand.getAvailable(this);
    }  

    public void transaction(Drawable drawer, PlayerCommand input)
    {
        Player player = this;
        Thread thread = new Thread(() -> input.execute(drawer, player));
        thread.start();
        
    }
   
}