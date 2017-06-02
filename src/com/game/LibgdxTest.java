package com.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class LibgdxTest extends Game {


	public BitmapFont font;


	@Override
	public void create() {
		font = new BitmapFont();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		this.setScreen(new GameScreen(this));

	}

	@Override
	public void render() {
		super.render();
	}

	public void dispose() {
		font.dispose();
	}



}
