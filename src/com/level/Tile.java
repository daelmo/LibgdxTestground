package com.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Arrays;

public class Tile extends Actor {

	static final int WIDTH = 64, HEIGHT= 64;
	MapSprite[] spriteList;
	final int posX, posY;
	int zIndex;
	boolean[] isTraversable;

	public Tile(int posX, int posY, int zIndex) {
		this.posX = posX;
		this.posY = posY;
		this.zIndex = zIndex;
		isTraversable = new boolean[zIndex];
		Arrays.fill(isTraversable, Boolean.TRUE);

		MapSprite earthSprite = new MapSprite("floor/earth.png");
		spriteList = new MapSprite[2];
		spriteList[0] = earthSprite;
	}

	@Override
	public void act(float delta) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int i = 0; i < spriteList.length; i++) {
			if (spriteList[i] != null) {
				spriteList[i].setPosition(posX * WIDTH, posY * HEIGHT);
				spriteList[i].setZIndex(zIndex);
				spriteList[i].draw(batch);
			}
		}
	}

	public void setTraversable(int z, boolean bool) {
		isTraversable[z] = bool;
	}

}
