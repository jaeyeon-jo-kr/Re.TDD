package com.jocatelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

public class Card{

    
    public enum Number {
        A(1, 1, 11),
        N2(2, 2),
        N3(3, 3),
        N4(4, 4),
        N5(5, 5),
        N6(6, 6),
        N7(7, 7),
        N8(8, 8),
        N9(9, 9),
        N10(10, 10),
        J(11, 10),
        Q(12, 10),
        K(13, 10);
        
        private final int value;
        private final int specialValue;
        private final int id;
        public static final int NONE_SPECIAL_VALUE = -1;
        

        Number(int id, int value, int specialValue) {
            this.id = id;
            this.value = value;
            this.specialValue = specialValue;
            
        }

        Number(int id, int value) {
            this.id = id;
            this.value = value;
            this.specialValue = NONE_SPECIAL_VALUE;
        }
    }

    public enum Type{
        DIAMOND("◆"), SPADE("♠"), HEART("♥"), CLOVER("♣");
        private String symbol;
        Type(String symbol)
        {
            this.symbol = symbol;
        }
        @Override
        public String toString()
        {
            return symbol;
        }

    }

    private final Number number;
    private final Type type;
    
    private Card(int id, Type type)
    {
        this.number = Number.values()[id-1];
        this.type = type;
    }

    private Card(Number number, Type type)
    {
        this.number = number;
        this.type = type;
    }


    public static Card spade(int id){
        return new Card(id, Type.SPADE);
    }
    
    public static Card heart(int id){
        return new Card(id, Type.HEART);
    }
    
    public static Card diamond(int id){
        return new Card(id, Type.DIAMOND);
    }

    public static Card clover(int id){
        return new Card(id, Type.CLOVER);
    }

    public static Card create(int id, Type type){        
        return new Card(id, type);
    }
    public static Card of(Number number, Type type){
        return new Card(number, type);
    }

    private List<Integer> plusSpecialScore(int score){
        List<Integer> plus = new ArrayList<>();
        if (this.number.specialValue != Number.NONE_SPECIAL_VALUE)
            plus.add(score + this.number.specialValue);
        return plus;
    }

    public List<Integer> accumulateScore(List<Integer> candidates)
    {
        List<Integer> result = new ArrayList<>();
        for (int score : candidates) {
            result.add(score + this.number.value);
            result.addAll(plusSpecialScore(score));
        }
        return result;
    }
}