package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

//-- Runs the game. --//

public class MainGame implements Screen {

    private SoBots game;

    private Random random;
    private OrthographicCamera camera;

    private Touch touch;
    private Vector2 touchCoords;

    private float spriteWidth;
    private float spriteHeight;

    private float stateTime, spawnTimer;
    private Target[] robots;
    private Score score;
    private float spawnT;

    private boolean robotSet;
    private boolean hit;

	public MainGame(SoBots game) {
        this.game = game;
        spriteWidth = 104;
        spriteHeight = 150;
        random = new Random();
        createCamera();
        score = new Score(camera, game.batch, new Vector2(game.scrWidth, game.scrHeight));
        touch = new Touch(this, camera);
        Gdx.input.setInputProcessor(touch);
        spawnTimer = 3;
        hit = true;
	}

	@Override
	public void render(float delta) {
        //Colours background black
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
        int currentScore = score.getScore();
        spawnT = Difficulty.getTime(currentScore);
        spawnTimer +=  Gdx.graphics.getDeltaTime();

        //If 2 seconds elapsed since last drawing, re-draw robots
        if(spawnTimer > spawnT && hit) {
            hit = false;
            //int currentScore = score.getScore();
            int level = Difficulty.getDifficulty(currentScore);
            robots = new Target[level];

            //getRobotLoc();
            //Add a for loop here for the desired number of robots

            Vector2[] robotLoc = CoordsGen.genCoords(robots.length, (int) game.scrWidth, (int) game.scrHeight, (int) spriteWidth, (int) spriteHeight);
            robots[0] = new Target("red-bot-sprite.png", camera, game.batch, robotLoc[0]);
            for ( int i=1; i<robots.length; i++)
                robots[i] = new Target("blue-bot.png", camera, game.batch, robotLoc[i]);
            robotSet = true;
            spawnTimer = 0f;
        }
        else if (spawnTimer > spawnT && !hit) endGame();

        stateTime += Gdx.graphics.getDeltaTime();

        if(robotSet)
            for(Target t: robots)
                t.render(stateTime);

        score.render();
		game.batch.end();
	}

    //Sets co-ordinates of player touch.
    public void setCos(Vector2 coords){
        touchCoords = coords;
        Gdx.app.log("expl", "Co-ords - " + touchCoords);
        if(robotSet)
            if (robots[0].getBoundingRectangle().contains(touchCoords)) {
                //if(spawnTimer < spawnT) {
                    Gdx.app.log("expl", "You hit the robot!");
                    score.updateScore(1);
                    robots[0].targetTouched();
                    spawnTimer = spawnT-0.25f;
                    hit =true;
                //}
            }
            else {
                endGame();
            }
    }

    public void endGame() {
        game.setScreen(new MainMenu(game));
        dispose();
    }

    private void createCamera(){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.scrWidth, game.scrHeight);

    }

    @Override
    public void show() {

    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
