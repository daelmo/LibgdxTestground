package com.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.level.Level;
import java.util.HashMap;

import static com.badlogic.gdx.net.HttpRequestBuilder.json;

public class ObjectCreator {
	Level level;
	Stage stage;
	Group objectGroup;
	HashMap<String,StaticObject> objects;


	public ObjectCreator(Level level, Stage stage) {
		this.level = level;
		this.stage = stage;
		objectGroup = new Group();
		stage.addActor(objectGroup);

		objects = json.fromJson(HashMap.class, StaticObject.class, Gdx.files.internal("data/staticObjects.json"));
	}

	public StaticObject createObject(int x, int y, String type) {
		if (isPlaceable(x, y)) {
			level.setTraversable(x, y, Level.STATIC_OBJECT_ZINDEX, false);
			StaticObject object = objects.get(type).createCopyAt(x,y);
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
