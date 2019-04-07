package com.jocatelo.rule;

import com.jocatelo.character.Dealer;
import com.jocatelo.character.Player;

public interface Finalyzable {
    public WinStatus finalize (Dealer dealer, Player player);
}
