package com.jocatelo;

import org.checkerframework.checker.units.qual.degrees;

public class Card{

    public enum Number {
        A(1,1,11),
        N2(2, 2,2),
        N3(3, 3,3),
        N4(4, 4,4),
        N5(5, 5,5),
        N6(6,6,6),
        N7(7,7,7),
        N8(8,8,8),
        N9(9,9,9),
        N10(10,10,10),
        J(11,10,10),
        Q(12,10,10),
        K(13,10,10);

        private final int value;
        private final int specialValue;
        private final int id;
        
        Number(int id, int value, int specialValue){
            this.id = id;
            this.value = value;
            this.specialValue = specialValue;
        }
      

        public int value()
        {
            return value;
        }

        public int specialValue()
        {
            return specialValue;
        }

        public int id()
        {
            return id;
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

    @Deprecated
    public static Card spade(Number number){
        return new Card(number, Type.SPADE);
    }

    @Deprecated
    public static Card heart(Number number){
        return new Card(number, Type.HEART);
    }

    @Deprecated
    public static Card diamond(Number number){
        return new Card(number, Type.DIAMOND);
    }
    @Deprecated
    public static Card clover(Number number){
        return new Card(number, Type.CLOVER);
    }
    @Deprecated
    public static Card create(Number number, Type type){
        return new Card(number, type);
    }
    

    public int value()
    {
        return this.number.value();
    }

    public int specialValue()
    {
        return this.number.specialValue();
    }

    public Type type()
    {
        return this.type;
    }




    
}