package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Season;
import com.event.TimeController;
import com.level.Level;
import com.object.ObjectCreator;
import com.person.Person;
import com.person.PersonGrowth;

import java.io.*;
import java.util.Properties;

public class LibgdxTest extends ApplicationAdapter {

	private BitmapFont font;
	private Level level;
	public Stage stage;
	public GameInputProcessor gameInputProcessor;
	public UIInputProcessor uiInputProcessor;
	public InputMultiplexer multiplexer;
	public customScreenViewport viewport;
	public ObjectCreator objectCreator;
	public OrthographicCamera camera;

	//start settings
	public TimeController timeController = new TimeController(Season.spring);
	public boolean gamePaused = false;

	// read settings file
	Properties prop = new Properties();
	InputStream input = null;

	@Override
	public void create() {
		//load settings file
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//build camera settings
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		int viewportSize = Integer.parseInt(prop.getProperty("viewport_size"));
		camera = new OrthographicCamera( viewportSize, viewportSize * (h / w));
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
		level = Level.getInstance(Integer.parseInt(prop.getProperty("level_size")));
		level.addToStage(stage);
		objectCreator = new ObjectCreator(level, stage);


		// To test
		Person Person1 = Person.generatePerson(stage, timeController, PersonGrowth.ADULT);
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
		stage.act();
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
			timeController.addTime(deltaTime);
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
