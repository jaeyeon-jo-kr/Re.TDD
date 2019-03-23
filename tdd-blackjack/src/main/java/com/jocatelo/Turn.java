package com.jocatelo;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class Turn {
    private Command list[];
    private int number;
    private final int MAX_PLAYER_NUMBER = 9;

    private Turn() {
        list = new Command[MAX_PLAYER_NUMBER];
    }

    public Command[] commandList() {
        return list;
    }

    public static Turn create(int number) {
        return new Turn();
    }

    public Turn initialize(Playable[] users)
    {
        for(Playable user:users){
            list[user.getIndex()] = Command.NONE;
        }
        return this;
    }

    public Command command(Playable who, Command what) {
        
        return list[who.getIndex()] = what;
    }

    public Command what(Playable who) {
        return list[who.getIndex()];
    }

    public int number() {
        return number;
    }
}