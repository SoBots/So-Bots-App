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
    private String scoreText;
    private Vector2 approxLoc, scoreLoc, scoreDimen;
    private Vector3 scoreSpawn;

    public Score(Camera camera, Batch batch, Vector2 screenSize){
        this.camera = camera;
        this.batch = batch;

        font = new BitmapFont();
        font.setScale(5f);
        score = 0;
        approxLoc = new Vector2(screenSize.x/2F, screenSize.y);
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

    public void setScore(int points) { score = points;}

    //Returns current score value.
    public int getScore() { return score; }

    //Returns the width and height of the current score text.
    public Vector2 getScoreDimensions() { return scoreDimen; }

    //Draws the score to screen.
    private void displayScore(){
        positionScore();
        camera.unproject(scoreSpawn.set(scoreLoc.x, scoreLoc.y, 0));
        font.draw(batch, scoreText, scoreSpawn.x, scoreSpawn.y);
    }

    //Positions score bottom center of screen.
    private void positionScore(){
        scoreText = String.format("Score: %d", score);
        scoreDimen = new Vector2(font.getBounds(scoreText).width/2F, font.getBounds(scoreText).height);
        scoreLoc = new Vector2(approxLoc.x - scoreDimen.x, approxLoc.y - scoreDimen.y);
    }
}
