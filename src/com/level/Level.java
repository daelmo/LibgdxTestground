package com.level;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Level {
	private static Level instance = null;
	public Tile[][] GameMap;
	public Group MapGroup = new Group();
	public final int LEVEL_HEIGHT;
	public final int LEVEL_WIDTH;
	public static final int FLOOR_ZINDEX = 0;
	public static final int STATIC_OBJECT_ZINDEX = 1;
	public static final int DYNAMIC_OBJECT_ZINDEX = 2;
	public static final int BACKGROUND_ZINDEX = 3;
	public Group PersonGroup = new Group();

	protected Level(int xSize, int ySize) {
		LEVEL_WIDTH = xSize;
		LEVEL_HEIGHT = ySize;

		GameMap = new Tile[LEVEL_WIDTH][LEVEL_HEIGHT];
		for (int x = 0; x < LEVEL_WIDTH; x++) {
			for (int y = 0; y < LEVEL_HEIGHT; y++) {
				Tile t = new Tile(x, y, BACKGROUND_ZINDEX);
				GameMap[x][y] = t;
				MapGroup.addActor(t);
			}
		}
	}

	public static Level getInstance(int xSize, int ySize) {
		if (instance == null) {
			instance = new Level(xSize, ySize);
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
