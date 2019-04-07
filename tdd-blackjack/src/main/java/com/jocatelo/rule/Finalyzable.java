package com.jocatelo.rule;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public interface Finalyzable {
    public WinStatus finalizeStatus (Dealer dealer);    
}
