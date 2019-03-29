package com.jocatelo.character;

import java.util.List;

import com.jocatelo.Turn;
import com.jocatelo.rule.Command;

public interface Commandable {
    public void setCommand(Command command);
    public Command getCommand();
}