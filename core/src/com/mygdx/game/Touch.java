package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

/**
 * Created by samrowlands on 25/02/15.
 *
 * Handles all input in the game.
 */

public class Touch extends InputAdapter {

    private MyGdxGame game;

    public Touch(MyGdxGame game){
        this.game = game;
    }

    @Override
    //Gets the x/y co-ordinates on screen of player touch.
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //game.setCos(screenX, screenY);
        return true;
    }
}