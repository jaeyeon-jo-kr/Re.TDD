package com.jocatelo.rule.player;

import com.jocatelo.character.Dealer;
import com.jocatelo.rule.WinStatus;

public interface Finalyzable {
    public WinStatus finalizeStatus (Dealer dealer);    
}
