package com.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.level.Position;

public class ActorFont extends Actor {
	BitmapFont font;
	Position position = new Position(0, 0);
	Object t;

	public ActorFont(BitmapFont font, Object t, int shiftX, int shiftY) {
		this.font = font;
		this.t = t;
		this.setOrigin(shiftX, shiftY);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		font.draw(batch, t.toString(), position.getFloatX(), position.getFloatY());

	}

	@Override
	public void act(float delta) {
	}


	public void setPosition(Position position) {
		this.position = position;
	}

}
