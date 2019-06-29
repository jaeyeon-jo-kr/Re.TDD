package com.jocatelo;

import lombok.Data;

@Data
public class GameOption {
    private boolean shuffle;
    private boolean automaticGeneratePlayer;
    //Card is distributed manually
    private boolean automaticDistribute;

    private GameOption(){
        shuffle = true;
        automaticGeneratePlayer = true;
        automaticDistribute = true;
    }
    public static GameOption of(){
        return new GameOption();
    }
}