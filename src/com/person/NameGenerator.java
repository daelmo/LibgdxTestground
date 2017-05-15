package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.event.TimeController;
import javafx.stage.Stage;

import java.util.Random;

public class NameGenerator {
	private String[] femalePersonNames;
	private String[] malePersonNames;

	public NameGenerator() {
		JsonValue json = new JsonReader().parse(Gdx.files.internal("data/names.json"));
		JsonValue.JsonIterator it = json.iterator();
		femalePersonNames = it.next().asStringArray();
		malePersonNames = it.next().asStringArray();
	}


	public String generatePersonName(Gender gender) {
		String name;
		Random ran = new Random();
		int ranInt = ran.nextInt(femalePersonNames.length);
		if(gender == Gender.FEMALE){
			name = femalePersonNames[ranInt];
		} else {
			name = malePersonNames[ranInt];
		}
		return name;
	}
}
