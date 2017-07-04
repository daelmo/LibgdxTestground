package com.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;
import com.level.MapSprite;

public class StaticObject extends Actor{

	private int imageWidth, imageHeight;
	private int originX, originY;
	private int offsetX, offsetY;
	private boolean isTraversable;
	private String spritePath;
	private MapSprite sprite;
	private int posX, posY;
	private int[][] blockedArea;

	public StaticObject createDeepCopyAt(int X, int Y){
		StaticObject object = new StaticObject();
		object.spritePath = this.spritePath;
		object.imageWidth = this.imageWidth;
		object.imageHeight = this.imageHeight;
		object.originX = this.originX;
		object.originY = this.originY;
		object.offsetX = this.offsetX;
		object.offsetY = this.offsetY;
		object.isTraversable = this.isTraversable;
		object.blockedArea = this.blockedArea;
		object.posX = X;
		object.posY = Y;
		return object;
	}

	public void draw(Batch batch, float alpha) {
		sprite = new MapSprite(spritePath, imageWidth, imageHeight);
		sprite.setPosition(posX * Constants.TILE_WIDTH + offsetX, posY * Constants.TILE_HEIGHT + offsetY);
		sprite.setOrigin(originX,originY);
		sprite.draw(batch);
	}

	public int[][] getBlockedArea(){
		return blockedArea;
	}

}
