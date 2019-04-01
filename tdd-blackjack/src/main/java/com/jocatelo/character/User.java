package com.jocatelo.character;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

import com.jocatelo.Card;
import com.jocatelo.InvalidValueException;
import com.jocatelo.Round;
import com.jocatelo.Turn;
import com.jocatelo.rule.PlayerCommand;
import com.jocatelo.rule.Rule;
import com.jocatelo.rule.PlayerStatus;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
@Data
public abstract class User {
    protected Hands hands;
    
    protected int score;        
    protected Rule rule;
    protected String name;

    protected User() {
        hands = new Hands(new ArrayList<Card>());
        rule = Rule.CLASSIC;
    }    
}