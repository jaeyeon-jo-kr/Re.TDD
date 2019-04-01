package com.jocatelo.character;

public interface Playable {
    public void updateScore();
    public void updateStatus() throws Exception; 
    public void finalizeStatus();   
}