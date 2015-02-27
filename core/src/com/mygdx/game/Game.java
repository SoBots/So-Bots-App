package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//-- Runs the game. --//

public class Game extends ApplicationAdapter {

    private Random random;
    private OrthographicCamera camera;
	private SpriteBatch batch;

    private Touch touch;
    private Vector2 touchCoords = new Vector2(0, 0);

    private float sWidth;
    private float sHeight;

    private float stateTime, spawnTimer = 0;
    private List<Target> robots;
    private Score score;

	@Override
	public void create() {
		batch = new SpriteBatch();
        touch = new Touch(this);
        sWidth = Gdx.graphics.getWidth();
        sHeight = Gdx.graphics.getHeight();
        robots = new ArrayList<Target>();
        Gdx.input.setInputProcessor(touch);
        random = new Random();
        createCamera();
        score = new Score(camera, batch, new Vector2(sWidth/2, sHeight - 150));
	}

	@Override
	public void render() {
        //Colours background black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();

        spawnTimer += Gdx.graphics.getDeltaTime();

        //If 2 seconds elapsed since last drawing, re-draw robots
        if(spawnTimer > 2f) {
            //getRobotLoc();
            //Add a for loop here for the desired number of robots
            robots.add(new Target("red-bot.png", camera, batch, new Vector2(150, 150)));
            spawnTimer = 0f;
        }

        //For each robot in array; draw to screen
        for(Target t: robots)
            t.render();

        score.render();

        stateTime += Gdx.graphics.getDeltaTime();
		batch.end();
	}

    //Sets co-ordinates of player touch.
    public void setCos(Vector2 coords){
        touchCoords = new Vector2(coords.x, coords.y);
        //if (t.getBoundingRectangle().contains(touchCoords))
        //    Gdx.app.log("expl", "You hit the robot!");
        //if (Math.abs(touchCoordinateX-((robotSprite.getWidth()/2)))<=100 && Math.abs(touchCoordinateY-((robotSprite.getHeight()/2)))<=100) {
        //   Gdx.app.log("expl", "X - " + "You Clicked!");
        //}
    }


    private void createCamera(){
        camera = new OrthographicCamera(sWidth, sHeight);
        camera.setToOrtho(false, sWidth, sHeight);
    }
}
