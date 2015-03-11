package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//-- An instance of Target is a sprite. Handles its drawing and position. --//

 public class Target {
    private TextureRegion tCurFrame;
    private TextureRegion[] tFrames;
    private Animation tAnimation;
    private Sprite tSprite;

    private float stateTime;

    private Camera camera;
    private Batch batch;

    private Vector2 passedLoc;
    private Vector3 spawnPoint;

    public Target(String texture, Camera camera, Batch batch, Vector2 passedLoc){
        createAnim(texture);

        this.camera = camera;
        this.batch = batch;

        this.passedLoc = passedLoc;
    }

    //Called in Game's render method.
    public void render(float stateTime) {
        spawnTarget();
        this.stateTime = stateTime;
    }

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
    private void createAnim(String texture){
        Texture tTexture = new Texture(Gdx.files.internal(texture));
        int FRAME_COLS = 2;
        int FRAME_ROWS = 1;
        TextureRegion[][] textureRegions = TextureRegion.split(tTexture, tTexture.getWidth()/FRAME_COLS, tTexture.getHeight()/FRAME_ROWS);
        tFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for(int i = 0; i < FRAME_ROWS; i++)
            for(int j = 0; j < FRAME_COLS; j++)
                tFrames[index++] = textureRegions[i][j];
        tAnimation = new Animation(0.0125f, tFrames);
        tSprite = new Sprite(tFrames[0]);
    }

    //Plays the created animation.
    private void playAnim(){
            tCurFrame = tAnimation.getKeyFrame(stateTime, false);
            tSprite = new Sprite(tCurFrame);
    }
}
