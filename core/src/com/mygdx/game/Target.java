package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//-- An instance of Target is a sprite. Handles its drawing and position. --//

 public class Target {
    private Texture tTexture;
    private Sprite tSprite;

    private Camera camera;
    private Batch batch;

    private Vector2 passedLoc;
    private Vector3 spawnPoint;

    public Target(String texture, Camera camera, Batch batch, Vector2 passedLoc){
        tTexture = new Texture(Gdx.files.internal(texture));
        tSprite = new Sprite(tTexture);

        this.camera = camera;
        this.batch = batch;

        this.passedLoc = passedLoc;
    }

    //Called in Game's render method.
    public void render() { spawnTarget(); }

    //Returns the dimensions of target.
    public Rectangle getBoundingRectangle() { return tSprite.getBoundingRectangle(); }

    //Called if player touches this target.
    public void targetTouched() {
        playAnim();
    }

    //Draws the sprite to a particular point of screen.
    private void spawnTarget(){
        spawnPoint = new Vector3();
        camera.unproject(spawnPoint.set(passedLoc.x, passedLoc.y, 0));
        tSprite.setPosition(spawnPoint.x, spawnPoint.y);
        tSprite.draw(batch);
    }

    //Splits the passed in texture into each frame for animation.
    private void createAnim(){

    }

    //Plays the created animation.
    private void playAnim(){

    }
}
