package com.jocatelo.character;

import com.jocatelo.Drawable;

public interface Playable {
    void execute(Drawable drawable) throws Exception;
    void updateStatus() throws Exception;
}