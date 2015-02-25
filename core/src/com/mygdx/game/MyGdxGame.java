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


public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;
    private Touch touch;

    private float sWidth;
    private float sHeight;

    private List<Target> robots;

    private Vector2 robotPos = new Vector2(0, 0);

    private Random random;

    private float stateTime, spawnTimer = 0;
    private int touchCoordinateX = 0;
    private int touchCoordinateY = 0;
    private Vector3 touchPoint = new Vector3(0, 0, 0);
    private OrthographicCamera camera;

    private BitmapFont font;
    private int score = 0;
    private Vector3 scoreLoc = new Vector3(0, 0, 0);

	
	@Override
	public void create() {
		batch = new SpriteBatch();
        touch = new Touch(this);
        sWidth = Gdx.graphics.getWidth();
        sHeight = Gdx.graphics.getHeight();
        robots = new ArrayList<Target>();
        Gdx.input.setInputProcessor(touch);
        random = new Random();
        font = new BitmapFont();
        font.setScale(10f);
        createCamera();
	}

	@Override
	public void render() {

        //Colours background black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();

        camera.unproject(scoreLoc.set(Gdx.graphics.getWidth()/2-500, Gdx.graphics.getHeight() - 180, 0));
        font.draw(batch, "Score: " + score, scoreLoc.x, scoreLoc.y);

        spawnTimer += Gdx.graphics.getDeltaTime();

        //If 2 seconds elapsed since last drawing, re-draw robots
        if(spawnTimer > 2f) {
            //getRobotLoc();
            //Add a for loop here for the desired number of robots
            robots.add(new Target("robot.png", camera, batch, 100, 100));
            spawnTimer = 0f;
        }

        //For each robot in array; draw to screen
        for(Target t: robots)
            t.render();

        stateTime += Gdx.graphics.getDeltaTime();

		batch.end();
	}

    /*//Sets co-ordinates of player touch.
    public void setCos(int x, int y){
        touchCoordinateX = x;
        touchCoordinateY = y;
        if (Math.abs(touchCoordinateX-((robotSprite.getWidth()/2)))<=100 && Math.abs(touchCoordinateY-((robotSprite.getHeight()/2)))<=100) {
            Gdx.app.log("expl", "X - " + "You Clicked!");
        }
    }*/


    public void createCamera(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
    }

    /*public void getRobotLoc(){
        float minX = robotSprite.getWidth();
        float maxX = Gdx.graphics.getWidth()-robotSprite.getWidth();
        float minY = robotSprite.getHeight();
        float maxY = Gdx.graphics.getHeight()-robotSprite.getHeight();
        float x=random.nextFloat() * (maxX-minX) + minX;
        float y=random.nextFloat() * (maxY - minY) + minY;
        robotPos = new Vector2(x, y);
    }*/
}
