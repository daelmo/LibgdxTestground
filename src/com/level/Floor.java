package com.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;

public class Floor extends Actor {
	public final int posX, posY;
	public MapSprite sprite;

	public Floor(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		sprite = new MapSprite(
				"floor/earth.png",
				Constants.TILE_WIDTH,
				Constants.TILE_HEIGHT);
		sprite.setPosition(
				posX * Constants.TILE_WIDTH,
				posY * Constants.TILE_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
			sprite.draw(batch);
	}

}
