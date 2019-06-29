package com.jocatelo.rule;

import com.jocatelo.character.Player;

public interface Endable {
    public WinStatus finalyze(Player player);
    public int updateWinningCredit(Player player);
}