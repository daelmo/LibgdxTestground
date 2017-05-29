package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MenuScreen implements Screen{
	final LibgdxTest main;

	public OrthographicCamera camera;
	public MenuInputProcessor menuInputProcessor;
	public customScreenViewport viewport;
	public Stage stage;

	public MenuScreen(final LibgdxTest main){
		this.main = main;

		this.menuInputProcessor = new MenuInputProcessor(this);
		Gdx.input.setInputProcessor(menuInputProcessor);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(10, 10 * (h / w));
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		viewport= new customScreenViewport();
		stage = new Stage(viewport);
		viewport.setStage(stage);
	}



	public void render(float delta){

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		camera.update();

	}


	@Override
	public void dispose() {
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
	public void show() {

	}

	@Override
	public void hide() {

	}

}
