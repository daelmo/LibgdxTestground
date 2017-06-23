package com.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.Constants;
import com.object.StaticObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Level {
	private static Level instance = null;
	public Group[] objectGroup ;
	public Tile[][] traversableMap;

	protected Level() {
		objectGroup= new Group[Constants.LEVEL_HEIGHT];
		Arrays.fill(objectGroup, new Group());

		generateTraversableMap();
		generateFloor();
	}

	public static Level getInstance() {
		if (instance == null) {
			instance = new Level();
		}
		return instance;
	}

	public void addToStage(Stage stage) {
		for(int i=objectGroup.length - 1; i >= 0;i--){
			stage.addActor(objectGroup[i]);
		}
	}

	public boolean isTraversable(int x, int y) {
		//check if position is in Level
		if (!isInLevel(x,y)) {
			return false;
		}

		Tile t = traversableMap[x][y];
		for (boolean traversable : t.isTraversable) {
			if (traversable == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isInLevel(int x, int y) {
		if (x >= Constants.LEVEL_WIDTH || y >= Constants.LEVEL_HEIGHT || x < 0 || y < 0) {
			return false;
		}
		return true;
	}

	public boolean isInLevel(float x, float y) {
		if (x > Constants.LEVEL_WIDTH* Constants.TILE_WIDTH ||
			y > Constants.LEVEL_HEIGHT* Constants.TILE_HEIGHT ||
			x < 0 || y < 0) {
			return false;
		}
		return true;
	}

	public void setTraversable(int x, int y, int z, boolean bool) {
		traversableMap[x][y].setTraversable(z, bool);
	}

	
	private void generateTraversableMap(){
		traversableMap = new Tile[Constants.LEVEL_WIDTH][Constants.LEVEL_HEIGHT];
		for (int x = 0; x < Constants.LEVEL_WIDTH; x++) {
			for (int y = 0; y < Constants.LEVEL_HEIGHT; y++) {
				traversableMap[x][y] = new Tile(x,y);
			}
		}
	}

	//generate Floor Actor for whole level area
	private void generateFloor(){
		for (int x = 0; x < Constants.LEVEL_WIDTH; x++) {
			for (int y = 0; y < Constants.LEVEL_HEIGHT; y++) {
				objectGroup[y].addActor(new Floor(x, y));
			}
		}
	}

	public void printTraversableMap(){
		for(int y= Constants.LEVEL_HEIGHT -1; y>0;y--){
			for (int x=0; x<Constants.LEVEL_WIDTH;x++){
				if(isTraversable(x,y)){
					System.out.print("O");
				}else{
					System.out.print("-");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
