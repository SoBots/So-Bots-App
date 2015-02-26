package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

//-- Handles all input in the game. --//

public class Touch extends InputAdapter {

    private Game game;

    public Touch(Game game){
        this.game = game;
    }

    @Override
    //Gets the x/y co-ordinates on screen of player touch.
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setCos(new Vector2(screenX, screenY));
        return true;
    }
}