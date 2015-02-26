package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

//-- Draws and updates players score to screen. --//

public class Score {

    private Camera camera;
    private Batch batch;

    private BitmapFont font;
    private int score;
    private Vector2 scoreLoc;
    private Vector3 scoreSpawn;

    public Score(Camera camera, Batch batch, Vector2 scoreLoc){
        this.camera = camera;
        this.batch = batch;

        font = new BitmapFont();
        font.setScale(5f);
        score = 0;
        this.scoreLoc = scoreLoc;
        scoreSpawn = new Vector3();
    }

    //Called in Game's render method.
    public void render(){
        displayScore();
    }

    //Updates score.
    public void updateScore(int points){
        score += points;
    }

    //Draws the score on pre-determined point of screen.
    private void displayScore(){
        camera.unproject(scoreSpawn.set(scoreLoc.x, scoreLoc.y, 0));
        font.draw(batch, "Score: " + score, scoreSpawn.x, scoreSpawn.y);
    }
}
