package com.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.level.Position;

public class ActorFont extends Actor {
	BitmapFont font;
	Position position = new Position(0, 0);
	float shiftX, shiftY;
	String text;

	public ActorFont(BitmapFont font, String text, int shiftX, int shiftY) {
		this.font = font;
		this.text = text;
		this.setOrigin(shiftX, shiftY);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		font.draw(batch, text, position.getFloatX(), position.getFloatY());

	}

	@Override
	public void act(float delta) {

	}


	public void setPosition(Position position) {
		this.position = position;
	}

}
