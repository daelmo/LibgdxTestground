package com.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;
import com.level.MapSprite;

public class StaticObject extends Actor {

	private int imageWidth, imageHeight;
	private int originX, originY;
	private boolean isTraversable;
	private String spritePath;
	private MapSprite sprite;
	private int posX, posY;
	private int[][] blockedArea;

	public StaticObject createCopyAt(int x, int y){
		StaticObject object = new StaticObject();
		object.spritePath = this.spritePath;
		object.imageWidth = this.imageWidth;
		object.imageHeight = this.imageHeight;
		object.originX = this.originX;
		object.originY = this.originY;
		object.isTraversable = this.isTraversable;
		object.blockedArea = this.blockedArea;
		object.posX = x;
		object.posY = y;
		return object;
	}

	public void draw(Batch batch, float alpha) {
		sprite = new MapSprite(spritePath, imageWidth, imageHeight);
		sprite.setPosition(posX * Constants.TILE_WIDTH, posY * Constants.TILE_HEIGHT);
		sprite.setOrigin(originX,originY);
		sprite.draw(batch);
	}

	public int[][] getBlockedArea(){
		return blockedArea;
	}

}
