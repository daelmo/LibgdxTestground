package com.person;

import com.action.ActiveScheduler;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.TimeController;
import com.game.ActorFont;
import com.level.Level;
import com.level.Position;
import com.gui.PersonBody;


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
		Person person = new Person(stage, growth, font);
		person.setBirthday(timeController.generateBirthday(growth));
		person.setScheduler(ActiveScheduler.getScheduler(timeController));
		String name= nameGen.generatePersonName(Gender.MALE);
		person.setName(name);
		person.setPersonBody(new PersonBody(growth));
		person.setStatistic(new Statistic(person));
		person.setGender(Gender.MALE);
		person.printName = new ActorFont(font, name, -5, 5);
		person.addText(person.printName);
		person.setPosition(Position.getRandomPosition());
		stage.addActor(person);
	}
}
