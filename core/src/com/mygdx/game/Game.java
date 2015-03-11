package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

//-- Runs the game. --//

public class Game extends ApplicationAdapter {

    private Random random;
    private OrthographicCamera camera;
	private SpriteBatch batch;

    private Touch touch;
    private Vector2 touchCoords;

    private float scrWidth;
    private float scrHeight;
    private float spriteWidth;
    private float spriteHeight;

    private float stateTime, spawnTimer;
    private Target[] robots;
    private Score score;

    private boolean robotSet;

	@Override
	public void create() {
		batch = new SpriteBatch();
        scrWidth = Gdx.graphics.getWidth();
        scrHeight = Gdx.graphics.getHeight();
        spriteWidth = 104;
        spriteHeight = 150;
        random = new Random();
        createCamera();
        score = new Score(camera, batch, new Vector2(scrWidth, scrHeight));
        touch = new Touch(this, camera);
        Gdx.input.setInputProcessor(touch);
        //State currentState = new State();
        //currentState.setState(States.PLAY);
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
        if(spawnTimer > 1.75f) {
            int currentScore = score.getScore();
            int level = Difficulty.getDifficulty(currentScore);
            robots = new Target[level];

            //getRobotLoc();
            //Add a for loop here for the desired number of robots

            Vector2[] robotLoc = CoordsGen.genCoords(robots.length, (int) scrWidth, (int) scrHeight, (int) spriteWidth, (int) spriteHeight);
            robots[0] = new Target("red-bot-sprite.png", camera, batch, robotLoc[0]);
            for ( int i=1; i<robots.length; i++)
                robots[i] = new Target("blue-bot.png", camera, batch, robotLoc[i]);
            spawnTimer = 0f;
            robotSet = true;
        }

        stateTime += Gdx.graphics.getDeltaTime();

        if(robotSet)
            for(Target t: robots)
                t.render(stateTime);

        score.render();
		batch.end();
	}

    //Sets co-ordinates of player touch.
    public void setCos(Vector2 coords){
        touchCoords = coords;
        Gdx.app.log("expl", "Co-ords - " + touchCoords);

        if(robotSet)
            //Gdx.app.log("rect", "Bots Rect - " + t.getBoundingRectangle().x + " " + t.getBoundingRectangle().y);
            if (robots[0].getBoundingRectangle().contains(touchCoords) && spawnTimer<1.5f) {
                Gdx.app.log("expl", "You hit the robot!");
                score.updateScore(1);
                robots[0].targetTouched();
                spawnTimer = 1.5f;
            }
            else if (robots[0].getBoundingRectangle().contains(touchCoords) && spawnTimer>=1.5f) {
                //Do nothing
            }
            else score.setScore(0);

    }

    private void createCamera(){
        camera = new OrthographicCamera(scrWidth, scrHeight);
        camera.setToOrtho(false, scrWidth, scrHeight);

    }
}
