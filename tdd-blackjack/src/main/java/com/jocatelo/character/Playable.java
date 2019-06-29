package com.jocatelo.character;

import com.jocatelo.Drawable;

interface Playable {
    void execute(Drawable drawable);
    void updateStatus() throws Exception;
}