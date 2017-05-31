package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.event.TimeController;
import javafx.stage.Stage;

import java.util.*;

public class NameGenerator {
	private ArrayList<String> femalePersonNames;
	private ArrayList<String> malePersonNames;
	private ListIterator<String> femalePersonNameIterator;
	private ListIterator<String> malePersonNameIterator;

	public NameGenerator() {
		JsonValue json = new JsonReader().parse(Gdx.files.internal("data/names.json"));
		JsonValue.JsonIterator it = json.iterator();
		femalePersonNames = new ArrayList<>(Arrays.asList(it.next().asStringArray()));
		malePersonNames = new ArrayList<>(Arrays.asList(it.next().asStringArray()));
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
