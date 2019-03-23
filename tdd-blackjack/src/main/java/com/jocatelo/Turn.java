package com.jocatelo;

import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

public class Turn {
    private Map<Player, Command> commandMap;
    private int number;

    private Turn() {
        commandMap = new HashMap<>();
    }

    public Iterator<Entry<Player, Command>> commandList() {
        return commandMap.entrySet().iterator();
    }

    public static Turn create(int number) {
        return new Turn();
    }

    public Command command(Player who, Command what) {
        return commandMap.put(who, what);
    }

    public Command what(Player who) {

        return commandMap.getOrDefault(who, Command.NONE);
    }

    public int number() {
        return number;
    }
}