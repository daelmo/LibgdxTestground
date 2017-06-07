package com.action;

import com.event.TimeController;
import com.game.Constants;
import com.level.Level;
import com.level.Position;
import com.level.Tile;
import com.person.Person;
import java.util.Random;

public class ActiveScheduler implements Scheduler {
	private TimeController timeController;
	private Level level;

	public ActiveScheduler(TimeController timeController, Level level){
		this.level = level;
		this.timeController = timeController;
	}

	//get next Action for Person
	public Action getAction(Person person) {
		if(timeController.getCurrentHour() > 23 || timeController.getCurrentHour() < 6){
			return new Resting(person, timeController);
		}

		return new Walking(person, Position.getRandomPosition(), level);
	}

}
