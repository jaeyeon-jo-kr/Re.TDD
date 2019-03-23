
package com.jocatelo;

import java.util.Scanner;

public class Game {
    Scanner scaner = new Scanner(System.in);
    public int ShowMenu(){
        System.out.println("==== Select what you want ====");
        System.out.println("1. New Game");
        System.out.println("2. Exit");


        switch(Integer.parseInt(scaner.nextLine())){
            case 1:
                ShowGame();
            case 2:
                return 1;
            default:
            break;
        }
        
        return 0;
    }

    public void ShowSelectPeople(){
        System.out.println("How many people?");
        int nPlayer = Integer.parseInt(scaner.nextLine());
        System.out.println(" => " + nPlayer);
    }

    public void ShowGame(){
        System.out.println("Loading...");

        // TODO: INIT Variables for the game
        // TODO: INIT players
        // TODO: Run game loop : Show result and get user input
    }
}