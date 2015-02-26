package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//-- An instance of Target is a sprite. Handles its drawing and position. --//

 public class Target {
    private Texture tTexture;
    private Sprite tSprite;

    private Camera camera;
    private Batch batch;

    private Vector2 spawnLoc;

    public Target(String texture, Camera camera, Batch batch, Vector2 spawnLoc){
        tTexture = new Texture(Gdx.files.internal(texture));
        tSprite = new Sprite(tTexture);

        this.camera = camera;
        this.batch = batch;

        this.spawnLoc = new Vector2(spawnLoc.x, spawnLoc.y);
    }

    //Called in Game's render method.
    public void render(){
        spawnTarget();
    }

    //Draws the sprite to a particular point of screen.
    private void spawnTarget(){
        Vector3 spawnPoint = new Vector3();
        camera.unproject(spawnPoint.set(spawnLoc.x, spawnLoc.y, 0));
        batch.draw(tSprite, spawnPoint.x, spawnPoint.y);
    }

}
