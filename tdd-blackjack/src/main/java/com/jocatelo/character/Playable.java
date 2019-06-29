package com.jocatelo.character;

import com.jocatelo.Drawable;

interface Playable {
    void execute(Drawable drawable) throws Exception;
    void updateStatus() throws Exception;
}