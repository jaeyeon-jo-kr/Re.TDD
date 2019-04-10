package com.jocatelo.character;

import com.jocatelo.Drawable;

public interface Playable {
    public void execute(Drawable drawable) throws Exception;    
    public void updateStatus() throws Exception; 
}