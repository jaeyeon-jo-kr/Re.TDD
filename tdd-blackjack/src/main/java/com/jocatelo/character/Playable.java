package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Card;
import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.Status;


public interface Playable {
    public void updateScore();
    public void updateStatus();    
}