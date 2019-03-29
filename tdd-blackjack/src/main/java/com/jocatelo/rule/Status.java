package com.jocatelo.rule;

public enum Status {
    WIN("WIN"),
    LOSE("LOSE"),
    DRAW("DRAW"),
    READY("READY"),
    STAND("STAND"),
    BUST("BUST"),
    PLAYING("PLAYING"),
    BLACKJACK("BLACKJACK"),
    SURRENDER("SURRENDER"), 
    PUSH("PUSH");

    private String symbol;
    Status(String symbol)
    {
        this.symbol = symbol;
    }

	@Override
	public String toString() {
		return this.symbol;
	}

    
    
}