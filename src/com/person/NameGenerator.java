package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.event.TimeController;
import javafx.stage.Stage;

import java.util.*;

public class NameGenerator {
	private List<String> femalePersonNames;
	private List<String> malePersonNames;
	private ListIterator<String> femalePersonNameIterator;
	private ListIterator<String> malePersonNameIterator;

	public NameGenerator() {
		JsonValue json = new JsonReader().parse(Gdx.files.internal("data/names.json"));
		JsonValue.JsonIterator it = json.iterator();
		String[] fpNames = json.get("femalePersonNames").asStringArray();
		femalePersonNames = Arrays.asList(fpNames);
		String[] mpNames = json.get("malePersonNames").asStringArray();
		malePersonNames = Arrays.asList(mpNames);
		Collections.shuffle(femalePersonNames);
		Collections.shuffle(malePersonNames);
		femalePersonNameIterator = femalePersonNames.listIterator();
		malePersonNameIterator = malePersonNames.listIterator();
	}


	public String generatePersonName(Gender gender) {
		String name;
		if(gender == Gender.FEMALE){
			if(!femalePersonNameIterator.hasNext()){femalePersonNameIterator = femalePersonNames.listIterator();}
			name = femalePersonNameIterator.next();
		}else {
			if(!malePersonNameIterator.hasNext()){malePersonNameIterator = malePersonNames.listIterator();}
			name = malePersonNameIterator.next();
		}
		return name;
	}
}
