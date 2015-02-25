package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by samrowlands on 25/02/15.
 *
 *
 */
public class Target
{
    private Texture tTexture;
    private Sprite tSprite;

    private Camera camera;
    private Batch batch;

    private float spawnX, spawnY;

    public Target(String texture, Camera camera, Batch batch, float x, float y){
        tTexture = new Texture(Gdx.files.internal(texture));
        tSprite = new Sprite(tTexture);

        this.camera = camera;
        this.batch = batch;

        spawnX = x;
        spawnY = y;
    }

    public void render(){
        spawnRobot();
    }

    public void spawnRobot(){
        Vector3 spawnPoint = new Vector3();
        camera.unproject(spawnPoint.set(spawnX, spawnY, 0));
        batch.draw(tSprite, spawnPoint.x, spawnPoint.y);
    }

}
