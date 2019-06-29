package com.jocatelo.rule.player.win;

import com.jocatelo.character.Dealer;
import com.jocatelo.rule.WinStatus;

public interface Finalyzable {
    WinStatus finalizeStatus(Dealer dealer);
}
