package com.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class GameInputProcessor implements InputProcessor {

	public boolean keyUpPressed = false;
	public boolean keyDownPressed = false;
	public boolean keyLeftPressed = false;
	public boolean keyRightPressed = false;
	public boolean keySpacePressed = false;
	private GameScreen screen;

	public GameInputProcessor(GameScreen screen) {
		this.screen = screen;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.LEFT:
				keyLeftPressed = true;
				return true;
			case Input.Keys.RIGHT:
				keyRightPressed = true;
				return true;
			case Input.Keys.DOWN:
				keyDownPressed = true;
				return true;
			case Input.Keys.UP:
				keyUpPressed = true;
				return true;
			case Input.Keys.SPACE:
				screen.toggleGameState();
				return true;
		}		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.LEFT:
				keyLeftPressed = false;
				return true;
			case Input.Keys.RIGHT:
				keyRightPressed = false;
				return true;
			case Input.Keys.DOWN:
				keyDownPressed = false;
				return true;
			case Input.Keys.UP:
				keyUpPressed = false;
				return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
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
		switch (amount) {
			case 1:
				screen.viewport.zoomIn();
				return true;
			case -1:
				screen.viewport.zoomOut();
				return true;
		}
		return false;
	}
	
}