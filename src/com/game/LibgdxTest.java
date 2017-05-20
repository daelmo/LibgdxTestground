package com.game;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Season;
import com.event.TimeController;
import com.level.Level;
import com.object.ObjectCreator;
import com.person.Person;
import com.person.PersonBuilder;
import com.person.PersonGrowth;
import gui.TimeBox;

import java.io.*;
import java.util.Properties;

public class LibgdxTest extends Game {
	public RayHandler rayHandler;
	public World world;

	private BitmapFont font;
	private TimeBox timeBox;


	private Level level;
	public GameInputProcessor gameInputProcessor = new GameInputProcessor(this);
	public InputMultiplexer multiplexer = new InputMultiplexer(gameInputProcessor);
	public customScreenViewport viewport = new customScreenViewport();
	public Stage stage ;
	public ObjectCreator objectCreator;
	public OrthographicCamera camera;

	//start settings
	public TimeController timeController;
	public boolean gamePaused = false;

	// read settings file
	Properties prop = new Properties();
	InputStream input = null;

	@Override
	public void create() {
		Box2D.init();
		font = new BitmapFont();
		stage = new Stage(viewport);
		world = new World(new Vector2(0, -98f), true);
		rayHandler = new RayHandler(world);
		rayHandler.setShadows(true);


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
		camera.update();

		timeController  = new TimeController(rayHandler, Season.spring);

		//build Input interface
		Gdx.input.setInputProcessor(multiplexer);

		//build stage
		viewport.setStage(stage);

		//build Level
		int size= Integer.parseInt(prop.getProperty("level_size"));
		level = Level.getInstance(size, size);
		level.addToStage(stage);
		objectCreator = new ObjectCreator(level, stage);
		PersonBuilder personBuilder = new PersonBuilder(timeController, stage, font);

		initGUI();
		// To test

		Person Person1 = personBuilder.generateActivePerson(PersonGrowth.ADULT);
		Person Person2 = personBuilder.generateActivePerson(PersonGrowth.ADULT);
		level.PersonGroup.addActor(Person1);
		level.PersonGroup.addActor(Person2);

		objectCreator.createObject(3, 3, 1);
		objectCreator.createObject(8, 3, 1);
		objectCreator.createObject(3, 8, 1);
		objectCreator.createObject(3, 7, 1);

	}

	@Override
	public void render() {
		handlePressedKeys();

		world.step(Gdx.graphics.getDeltaTime(), 3, 3);
		if (gamePaused == false) {
			stage.act();
			timeController.addTime(Gdx.graphics.getDeltaTime());
		}

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();


		camera.update();
	}

	@Override
	public void dispose() {
		rayHandler.dispose();
		stage.dispose();
		font.dispose();
		world.dispose();
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

	//continuous keys only
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


	public void toggleGameState() {
		if (gamePaused == false) {
			pause();
		} else if (gamePaused == true) {
			resume();
		}
	}

	public void initGUI(){
		timeBox = new TimeBox(timeController, stage, font);
	}

}
