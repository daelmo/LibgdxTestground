package com.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.Constants;

public class MapSprite extends Actor{
	public Sprite sprite;
	public Texture texture;
	
	public MapSprite(String path){
		texture = new Texture(Gdx.files.internal(path));
		sprite = new Sprite(texture, 0, 0, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
	}
	
	public void draw(Batch batch){
		sprite.draw(batch);
	}
	
	@Override
	public void setPosition(float x, float y){
		sprite.setPosition(x, y);
	}
	
}
