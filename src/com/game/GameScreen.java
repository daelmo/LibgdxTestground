package com.game;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Season;
import com.event.TimeController;
import com.gui.TimeBox;
import com.level.Level;
import com.object.ObjectCreator;
import com.person.Person;
import com.person.PersonBuilder;
import com.person.PersonGrowth;

public class GameScreen implements Screen {
	final LibgdxTest main;

	public World world;
	public RayHandler rayHandler;

	public TimeController timeController;
	public boolean gamePaused = false;
	private Level level;
	public Stage stage;
	public ObjectCreator objectCreator;
	public PersonBuilder personBuilder;


	public OrthographicCamera camera;
	public GameInputProcessor gameInputProcessor;
	public customScreenViewport viewport;

	public TimeBox timeBox;



	public GameScreen(final LibgdxTest main) {
		this.main = main;

		this.gameInputProcessor = new GameInputProcessor(this);
		Gdx.input.setInputProcessor(gameInputProcessor);

		//build camera settings
		viewport= new customScreenViewport();
		stage = new Stage(viewport);
		viewport.setStage(stage);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(10, 10 * (h / w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		level = Level.getInstance();
		level.addToStage(stage);
		world = new World(new Vector2(0, -98f), true);
		rayHandler = new RayHandler(world);
		timeController = new TimeController(rayHandler, Season.spring);
		objectCreator = new ObjectCreator(level, stage);
		personBuilder = new PersonBuilder(timeController, stage, main.font);

		//initGui
		timeBox = new TimeBox(timeController, stage, main.font);


		//init Scenario
		Person Person1 = personBuilder.generateActivePerson(PersonGrowth.ADULT);
		Person Person2 = personBuilder.generateActivePerson(PersonGrowth.ADULT);
		level.personGroup.addActor(Person1);
		level.personGroup.addActor(Person2);

		objectCreator.createObject(3, 3, "stone");
		objectCreator.createObject(8, 3, "stone");
		objectCreator.createObject(3, 8, "stone");
		objectCreator.createObject(3, 7, "bush");
	}



	public void render(float delta) {
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

	public void dispose() {
		rayHandler.dispose();
		stage.dispose();
		world.dispose();
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

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}


}
