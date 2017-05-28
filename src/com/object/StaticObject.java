package com.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.level.MapSprite;

public class StaticObject extends Actor {

	private int sizeX, sizeY;
	private int originX, originY;
	private boolean isTraversable;
	private String spritePath;
	private MapSprite sprite;
	private int posX, posY;
	private int zIndex = 2;


	public StaticObject createCopyAt(int x, int y){
		StaticObject object = new StaticObject();
		object.spritePath = this.spritePath;
		object.sizeX = this.sizeX;
		object.sizeY = this.sizeY;
		object.originX = this.originX;
		object.originY = this.originY;
		object.isTraversable = this.isTraversable;
		object.posX = x;
		object.posY = y;
		return object;
	}



	public void draw(Batch batch, float alpha) {
		sprite = new MapSprite(spritePath);
		sprite.setPosition(posX * 64, posY * 64);
		sprite.setOrigin(originX,originY);
		sprite.setZIndex(zIndex);
		sprite.draw(batch);
	}

	public void setPosition( int x,int y){
		this.posX = x;
		this.posY = y;
	}

}
