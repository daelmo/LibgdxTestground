package com.action;

import com.event.TimeController;
import com.game.Constants;
import com.level.Position;
import com.level.Tile;
import com.person.Person;
import java.util.Random;

public class ActiveScheduler implements Scheduler {
	private static Scheduler scheduler;
	private TimeController timeController;

	public ActiveScheduler(TimeController timeController){
		this.timeController = timeController;
	}

	public Action getAction(Person person) {
		if(timeController.getCurrentHour() > 23 || timeController.getCurrentHour() < 6){
			return new Resting(person, timeController);
		}

		return new Walking(person, Position.getRandomPosition());
	}

	public static Scheduler getScheduler(TimeController timeController){
		if(scheduler == null){
			scheduler = new ActiveScheduler(timeController);
		}
		return scheduler;
	}
}
