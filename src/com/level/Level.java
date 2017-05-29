package com.level;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.Constants;
import com.object.StaticObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
	private static Level instance = null;

	public Group floorGroup = new Group();
	public Group personGroup = new Group();
	public Group objectGroup = new Group();
	public Tile[][] traversableMap;
	public HashMap<StaticObject,ArrayList<StaticObject>> objectPlacement = new HashMap<>();

	protected Level() {
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
		stage.addActor(floorGroup);
		stage.addActor(objectGroup);
		stage.addActor(personGroup);
	}

	public void removeFromStage() {
		personGroup.remove();
		objectGroup.remove();
		floorGroup.remove();
	}

	public boolean isTraversable(int x, int y) {
		Tile t = traversableMap[x][y];
		for (boolean traversable : t.isTraversable) {
			if (traversable == false) {
				return false;
			}
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

	private void generateFloor(){
		for (int x = 0; x < Constants.LEVEL_WIDTH; x++) {
			for (int y = 0; y < Constants.LEVEL_HEIGHT; y++) {
				floorGroup.addActor(new Floor(x, y));
			}
		}
	}
}
