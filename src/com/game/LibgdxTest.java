package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Timer;
import com.level.Level;
import com.object.ObjectCreator;
import com.person.Person;

public class LibgdxTest extends ApplicationAdapter {

    private BitmapFont font;
    private Level level;
    public Stage stage;
    public GameInputProcessor gameInputProcessor;
    public UIInputProcessor uiInputProcessor;
    public InputMultiplexer multiplexer;
    public customScreenViewport viewport;
    public ObjectCreator objectCreator;
    public boolean gamePaused = false;
    public OrthographicCamera camera;
    public Timer timer = new Timer();

    @Override
    public void create() {
	//build camera settings
	float w = Gdx.graphics.getWidth();
	float h = Gdx.graphics.getHeight();
	camera = new OrthographicCamera(30, 30 * (h / w));
	camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
	viewport = new customScreenViewport();
	camera.update();

	//build Input interface
	gameInputProcessor = new GameInputProcessor(this);
	uiInputProcessor = new UIInputProcessor();
	multiplexer = new InputMultiplexer(gameInputProcessor, uiInputProcessor);
	Gdx.input.setInputProcessor(multiplexer);

	//build font
	font = new BitmapFont();

	//build stage
	stage = new Stage(viewport);
	viewport.setStage(stage);

	//build Level
	level = Level.getInstance();
	level.addToStage(stage);
	
	objectCreator = new ObjectCreator(level, stage);
	
	// To test
	Person Person1 = new Person(stage);
	level.PersonGroup.addActor(Person1);
	
	objectCreator.createObject(3, 3, 1);
	objectCreator.createObject(8, 3, 1);
	objectCreator.createObject(3, 8, 1);
	objectCreator.createObject(3, 7, 1);
	
	

    }

    @Override
    public void render() {
	handlePressedKeys();
	handleTimer(Gdx.graphics.getDeltaTime());

	Gdx.gl.glClearColor(0, 0, 0, 0);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	stage.draw();
	camera.update();
    }

    @Override
    public void dispose() {
	stage.dispose();
	font.dispose();
    }

    @Override
    public void resize(int width, int height) {
	viewport.update(width, height);
    }

    @Override
    public void pause() {
	gamePaused = true;
    }

    @Override
    public void resume() {
	gamePaused = false;
    }

    //contiuous keys only
    private void handlePressedKeys() {
	if (gameInputProcessor.keyLeftPressed) {
	    viewport.translateLeft();
	}
	if (gameInputProcessor.keyRightPressed) {
	    viewport.translateRight();
	}
	if (gameInputProcessor.keyDownPressed) {
	    viewport.translateDown();
	}
	if (gameInputProcessor.keyUpPressed) {
	    viewport.translateUp();
	}
    }

    private void handleTimer(float deltaTime) {
	if (gamePaused == false) {
	    stage.act();
	    timer.addTime(deltaTime);
	}
    }

    public void toggleGameState() {
	if (gamePaused == false) {
	    pause();
	} else if (gamePaused == true) {
	    resume();
	}
    }

}
