package com.level;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * @author fine
 */
public class Level {

	private static Level instance = null;
	public Tile[][] GameMap;
	public Group MapGroup = new Group();

	public static final int FLOOR_ZINDEX = 0;
	public static final int FURNITURE_ZINDEX = 1;
	public static final int OBJECT_ZINDEX = 2;
	public Group PersonGroup = new Group();

	protected Level(int size) {
		GameMap = new Tile[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Tile t = new Tile(x, y, 3);
				GameMap[x][y] = t;
				MapGroup.addActor(t);
			}
		}
	}

	public static Level getInstance(int size) {
		if (instance == null) {
			instance = new Level(size);
		}
		return instance;
	}

	public void addToStage(Stage stage) {
		stage.addActor(MapGroup);
		stage.addActor(PersonGroup);
	}

	public void removeFromStage() {
		MapGroup.remove();
		PersonGroup.remove();
	}

	public boolean isTraversable(int x, int y) {
		Tile t = GameMap[x][y];
		for (boolean traversable : t.isTraversable) {
			if (traversable == false) {
				return false;
			}
		}
		return true;
	}

	public void setTraversable(int x, int y, int z, boolean bool) {
		GameMap[x][y].setTraversable(z, bool);
	}
}
