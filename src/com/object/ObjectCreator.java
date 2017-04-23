package com.object;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.level.Level;
import com.object.StaticObject;

/**
 * @author fine
 */
public class ObjectCreator {
	Level level;
	Stage stage;
	Group objectGroup;

	public ObjectCreator(Level level, Stage stage) {
		this.level = level;
		this.stage = stage;
		objectGroup = new Group();
		stage.addActor(objectGroup);
	}


	public StaticObject createObject(int x, int y, int typeID) {
		if (isPlaceable(x, y)) {
			StaticObject object = new StaticObject(x, y, typeID);
			level.setTraversable(x, y, 1, false);
			objectGroup.addActor(object);
			return object;
		} else {
			return null;
		}


	}

	private boolean isPlaceable(int x, int y) {
		if (level.isTraversable(x, y)) {
			return true;
		} else {
			return false;
		}
	}

}
