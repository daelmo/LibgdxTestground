package com.person;

import com.action.ActiveScheduler;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.TimeController;
import com.game.ActorFont;
import com.level.Level;
import com.level.Position;


public class PersonBuilder {
	private NameGenerator nameGen = new NameGenerator();
	private TimeController timeController;
	private Stage stage;
	private BitmapFont font;
	private Level level;

	public PersonBuilder(TimeController timeController, Stage stage, BitmapFont font, Level level){
		this.timeController = timeController;
		this.font = font;
		this.stage = stage;
		this.level = level;
	}

	public void generateActivePerson(PersonGrowth growth){
		Person person = new Person(stage, growth);

		String name= nameGen.generatePersonName(Gender.MALE);
		person.setName(name);
		person.setPrintName(new ActorFont(font, name, -5, 5));

		person.setPersonBody(new PersonBody(growth));
		person.setStatistic(new Statistic(person));
		person.setGender(Gender.MALE);

		person.setPosition(Position.getRandomPosition());
		person.setBirthday(timeController.generateBirthday(growth));
		person.setScheduler(new ActiveScheduler(timeController, level));

		stage.addActor(person);
		stage.addActor(person.getPrintName());
	}
}
