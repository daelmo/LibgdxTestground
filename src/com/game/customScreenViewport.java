package com.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class customScreenViewport extends ScreenViewport {
	Stage stage;
	OrthographicCamera camera;
	static final float CAMERA_ZOOM_MIN = 1.0f;
	static final float CAMERA_ZOOM_MAX = 2.0f;
	static final int TRANSL_DIST = 3;
	
	public void setStage(Stage stage){
		this.stage = stage;
		this.camera = (OrthographicCamera) stage.getCamera();

	}
	
	public void zoomOut() {
		camera.zoom = MathUtils.clamp(camera.zoom - 0.05f, CAMERA_ZOOM_MIN, CAMERA_ZOOM_MAX);
	}

	public void zoomIn() {
		camera.zoom = MathUtils.clamp(camera.zoom + 0.05f, CAMERA_ZOOM_MIN, CAMERA_ZOOM_MAX);
	}

	public void translateLeft() {
		stage.getCamera().translate(-TRANSL_DIST, 0, 0);
	}

	public void translateRight() {
		stage.getCamera().translate(TRANSL_DIST, 0, 0);
	}

	public void translateUp() {
		stage.getCamera().translate(0, TRANSL_DIST, 0);
	}

	public void translateDown() {
		stage.getCamera().translate(0, -TRANSL_DIST, 0);
	}

}
