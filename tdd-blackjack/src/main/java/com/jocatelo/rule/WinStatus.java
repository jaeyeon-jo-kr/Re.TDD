package com.jocatelo.rule;

import lombok.Getter;

public enum WinStatus {
    WIN(2.0f), LOSE(0.0f), DRAW(1.0f), PUSH(1.0f), BLACKJACK_WIN(2.5f);

    @Getter
    private float rate;

    WinStatus(float rate) {
        this.rate = rate;
    }
}