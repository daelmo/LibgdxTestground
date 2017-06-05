package com.person;

import com.action.ActiveScheduler;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.TimeController;
import com.game.ActorFont;
import com.level.Position;


public class PersonBuilder {
	private NameGenerator nameGen = new NameGenerator();
	private TimeController timeController;
	private Stage stage;
	private BitmapFont font;

	public PersonBuilder(TimeController timeController, Stage stage, BitmapFont font){
		this.timeController = timeController;
		this.font = font;
		this.stage = stage;
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
		person.setScheduler(ActiveScheduler.getScheduler(timeController));

		stage.addActor(person);
		stage.addActor(person.getPrintName());
	}
}
