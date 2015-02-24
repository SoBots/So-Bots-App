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

import java.util.Random;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;

    private Sprite robotSprite;
    private Texture robotTexture;
    private Vector2 robotPos = new Vector2(0, 0);
    private float x=20;
    private float y=40;
    boolean reverseH = false;
    boolean reverseV = false;

    private Random random;
    private Sprite SamRowlands;

    private Texture explosionSheet;
    private TextureRegion[] explosionFrames;
    private Animation explosionAnimation;
    private TextureRegion currentFrame;
    private Sprite currentFrameSprite;
    private boolean explosionHappening = false;
    private Sound explosionSound;

    private final int FRAME_COLS = 5;
    private final int FRAME_ROWS = 3;

    private float stateTime, spawnTimer = 0;
    private int touchCoordinateX = 0;
    private int touchCoordinateY = 0;
    private Vector3 touchPoint = new Vector3(0, 0, 0);
    private OrthographicCamera camera;

    private BitmapFont font;
    private int score = 0;
    private Vector3 scoreLoc = new Vector3(0, 0, 0);

	
	@Override
	public void create () {
		batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        random = new Random();
        font = new BitmapFont();
        font.setScale(10f);
        createRobot();
        createExplosion();
        createCamera();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(255, 255, 255, 255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
		batch.begin();

        camera.unproject(scoreLoc.set(Gdx.graphics.getWidth()/2-500, Gdx.graphics.getHeight() - 180, 0));
        font.draw(batch, "Score: " + score, scoreLoc.x, scoreLoc.y);

        spawnTimer += Gdx.graphics.getDeltaTime();
        if(spawnTimer > 2f) {
            getRobotLoc();
            spawnTimer = 0f;

        }

        spawnRobot();

        stateTime += Gdx.graphics.getDeltaTime();
        if (!explosionAnimation.isAnimationFinished(stateTime) && explosionHappening) {
            currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
            currentFrameSprite = new Sprite(currentFrame);
            camera.unproject(touchPoint.set(touchCoordinateX, touchCoordinateY, 0));
            batch.draw(currentFrameSprite, touchPoint.x, touchPoint.y);
        }

		batch.end();
	}

    public void createExplosion(){
        explosionSheet = new Texture(Gdx.files.internal("explosion.png"));
        TextureRegion[][] textureRegions = TextureRegion.split(explosionSheet, explosionSheet.getWidth()/FRAME_COLS, explosionSheet.getHeight()/FRAME_ROWS);
        explosionFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for(int i = 0; i < FRAME_ROWS; i++)
            for(int j = 0; j < FRAME_COLS; j++)
                explosionFrames[index++] = textureRegions[i][j];
        explosionAnimation = new Animation(0.025f, explosionFrames);

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("boom.mp4"));
    }

    public void createCamera(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);

        //To initialize the camera, we need to construct the camera with the width and height of the graphic area.
        //Then we need to set the  camera position to be smack in the middle of the screen.  The camera uses 3D coordinates,
        //but we are only concerned with the X and Y coordinates for the explosion, so we set the Z position to zero
        // and the x and y coordinates of the camera to the screens midpoint.
        //Now that we have our camera created the way we want it, we can unproject the touch points in the
        //render method to reflect the game world coordinates using a Vector3 class .
        // The touchPoint, which is the instance of the Vector3 class,  can then be used to set the animation location.
    }

    public void createRobot() {
        robotTexture = new Texture(Gdx.files.internal("robot.png"));
        robotSprite = new Sprite(robotTexture);
        //robotSprite.setOrigin();
    }

    public void getRobotLoc(){
        float minX = robotSprite.getWidth();
        float maxX = Gdx.graphics.getWidth()-robotSprite.getWidth();
        float minY = robotSprite.getHeight();
        float maxY = Gdx.graphics.getHeight()-robotSprite.getHeight();
        x=random.nextFloat() * (maxX-minX) + minX;
        y=random.nextFloat() * (maxY - minY) + minY;
        robotPos = new Vector2(x, y);

        Gdx.app.log("coOrds", "X - " + robotPos.x + "\n Y - " + robotPos.y);
    }


    public void spawnRobot(){
        camera.unproject(touchPoint.set(robotPos.x, robotPos.y, 0));
        batch.draw(robotSprite, touchPoint.x, touchPoint.y);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        explosionSound.play();
        touchCoordinateX = screenX;
        touchCoordinateY = screenY;
        stateTime = 0;
        if (Math.abs(touchCoordinateX-(x+30))<=100 && Math.abs(touchCoordinateY-(y+30))<=100) {
            explosionHappening = true;
            Gdx.app.log("expl", "X - " + explosionHappening);

        }
        else explosionHappening = false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
