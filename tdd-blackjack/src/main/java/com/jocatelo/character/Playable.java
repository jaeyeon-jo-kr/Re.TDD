package com.jocatelo.character;

public interface Playable {
    public void execute() throws Exception;
    public void updateScore();
    public void updateStatus() throws Exception; 
}