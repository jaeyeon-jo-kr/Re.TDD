package com.jocatelo.rule;

import com.jocatelo.character.Player;

public interface Endable {
    WinStatus finalyze(Player player);
    int updateWinningCredit(Player player);
}