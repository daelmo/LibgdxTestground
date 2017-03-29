package com.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.level.MapSprite;

/**
 *
 * @author fine
 */
public class StaticObject extends Actor{

    String typeID, imgPath;
    boolean isTraversable;
    private MapSprite sprite;
    int posX, posY, zIndex = 2;

    public StaticObject(int posX, int posY, int typeID ) {
	this.posX = posX;
	this.posY = posY;
	switch(typeID){
	    case 1: 
		isTraversable = false;
		sprite = new MapSprite("object/stone.png");	
	}
    }
    
    public void draw(Batch batch, float alpha) {
	sprite.setPosition(posX*64, posY*64);
	sprite.setZIndex(zIndex);
	sprite.draw(batch);
    }
    
}
