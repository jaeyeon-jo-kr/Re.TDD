package com.jocatelo;


public class Card{

    public enum Number {
        A(1,11),
        N2(2,2),
        N3(3,3),
        N4(4,4),
        N5(5,5),
        N6(6,6),
        N7(7,7),
        N8(8,8),
        N9(9,9),
        N10(10,10),
        J(10,10),
        Q(10,10),
        K(10,10);

        private final int value;
        private final int nextValue;
        
        Number(int value, int nextValue){
            this.value = value;
            this.nextValue = nextValue;
        }

        public int value()
        {
            return this.value;
        }

        public int nextValue()
        {
            return this.nextValue;
        }
    }

    public enum Type{
        DIAMOND, SPADE, HEART, CLOVER;
    }

    private final Number number;
    private final Type type;
    
    private Card(Number number, Type type)
    {
        this.number = number;
        this.type = type;
    }

    public static Card spade(Number number){
        return new Card(number, Type.SPADE);
    }

    public static Card heart(Number number){
        return new Card(number, Type.HEART);
    }

    public static Card diamond(Number number){
        return new Card(number, Type.DIAMOND);
    }
    public static Card clover(Number number){
        return new Card(number, Type.CLOVER);
    }
    public static Card create(Number number, Type type){
        return new Card(number, type);
    }
    

    public int value()
    {
        return this.number.value();
    }

    public int nextValue()
    {
        return this.number.nextValue();
    }

    public Type type()
    {
        return this.type;
    }




    
}