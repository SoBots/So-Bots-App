package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//-- Handles all input in the game. --//

public class Touch extends InputAdapter {

    private Game game;
    private Camera camera;
    private Vector3 touchPoint;

    public Touch(Game game, Camera camera){
        this.game = game;
        this.camera = camera;
    }

    @Override
    //Gets the x/y co-ordinates on screen of player touch.
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPoint = new Vector3();
        camera.unproject(touchPoint.set(screenX, screenY, 0));
        game.setCos(new Vector2(touchPoint.x, touchPoint.y));
        return true;
    }
}