package com.jocatelo;

import lombok.Data;

@Data
public class Option {
    private boolean shuffle;
    private boolean automaticGeneratePlayer;
    //Card is distributed manually
    private boolean automaticDistribute;

    private Option(){
        shuffle = true;
        automaticGeneratePlayer = true;
        automaticDistribute = true;
    }
    public static Option of(){
        return new Option();
    }
}