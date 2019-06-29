package com.jocatelo.card;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private static final int NONE_SPECIAL_VALUE = -1;

    public boolean isSpade() {
        return isType(Type.SPADE);
    }


    public boolean isHeart() {
        return isType(Type.HEART);
    }

    public boolean isDiamond() {
        return isType(Type.DIAMOND);
    }

    public boolean isClover() {
        return isType(Type.CLOVER);
    }

    public boolean isType(Card.Type type) {
        return this.type == type;
    }
    public boolean isNumber(Number number)
    {
        return this.number == number;
    }

    public enum Number {
        A(1, 11),
        N2(2),
        N3(3),
        N4(4),
        N5(5),
        N6(6),
        N7(7),
        N8(8),
        N9(9),
        N10(10),
        J(10),
        Q(10),
        K(10);

        private final int value;
        private final int specialValue;


        Number(final int value, final int specialValue) {
            this.value = value;
            this.specialValue = specialValue;
        }

        Number(final int value) {
            this(value, NONE_SPECIAL_VALUE);
        }
    }

    public enum Type {
        SPADE("♠"), DIAMOND("◆"), CLOVER("♣"), HEART("♥");
        final private String symbol;

        Type(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }

    }

    private final Number number;
    private final Type type;

    private Card(int id, Type type) {
        this.number = Number.values()[id - 1];
        this.type = type;
    }

    private Card(Number number, Type type) {
        this.number = number;
        this.type = type;
    }


    public static Card spade(int number) {
        return new Card(number, Type.SPADE);
    }

    public static Card heart(int number) {
        return new Card(number, Type.HEART);
    }

    public static Card diamond(int number) {
        return new Card(number, Type.DIAMOND);
    }

    public static Card clover(int number) {
        return new Card(number, Type.CLOVER);
    }

    public static Card of(Number number, Type type) {
        return new Card(number, type);
    }

    private List<Integer> plusSpecialScore(int score) {
        List<Integer> plus = new ArrayList<>();
        if (this.number.specialValue != NONE_SPECIAL_VALUE)
            plus.add(score + this.number.specialValue);
        return plus;
    }

    public List<Integer> accumulateScore(List<Integer> candidates) {
        List<Integer> result = new ArrayList<>();
        for (int score : candidates) {
            result.add(score + this.number.value);
            result.addAll(plusSpecialScore(score));
        }
        return result;
    }
}