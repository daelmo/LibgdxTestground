package com.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.Constants;
import com.level.Level;

import java.util.HashMap;

import static com.badlogic.gdx.net.HttpRequestBuilder.json;

public class ObjectCreator {
	Level level;
	Stage stage;
	HashMap<String, StaticObject> templateObjects;


	public ObjectCreator(Level level, Stage stage) {
		this.level = level;
		this.stage = stage;
		templateObjects = json.fromJson(HashMap.class, StaticObject.class, Gdx.files.internal("data/staticObjects.json"));

		if(templateObjects == null){
			Gdx.app.debug(this.getClass().getName(), "templateObjects could not be load from file   ");
		}
	}

	//creates object of type at given position
	public void createObject(int x, int y, String type) {
		if (x > Constants.LEVEL_WIDTH || y > Constants.LEVEL_HEIGHT || x < 0 || y < 0) {
			Gdx.app.debug(this.getClass().getName(),type + " "  + x + ", "+ y +": assigned object position is out of level area");
			return;
		}

		if (templateObjects.get(type) == null){
			Gdx.app.debug(this.getClass().getName(),type + "is not a viable type for an object");
			return;
		}

		if (isPlaceable(x, y)) {
			level.setTraversable(x, y, Constants.STATIC_OBJECT_ZINDEX, false);
			StaticObject object = templateObjects.get(type).createCopyAt(x, y);
			level.objectGroup.addActor(object);
		} else {
			Gdx.app.debug(this.getClass().getName(), "position " + x + ", " + y + " is already taken");
		}
	}

	//checks if object is placeable at given position
	private boolean isPlaceable(int x, int y) {
		if (x > Constants.LEVEL_WIDTH || y > Constants.LEVEL_HEIGHT || x < 0 || y < 0) {
			Gdx.app.debug(this.getClass().getName(), x + ", "+ y +": position is out of level area");
			return false;
		}

		if (level.isTraversable(x, y)) {
			return true;
		} else {
			return false;
		}
	}

}
