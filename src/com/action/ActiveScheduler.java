package com.action;

import com.game.Constants;
import com.level.Position;
import com.level.Tile;
import com.person.Person;
import java.util.Random;

public class ActiveScheduler implements Scheduler {
	private static Scheduler scheduler;

	public Walking getAction(Person person) {
		return new Walking(person, Position.getRandomPosition());
	}

	public static Scheduler getScheduler(){
		if(scheduler == null){
			scheduler = new ActiveScheduler();
		}
		return scheduler;
	}
}
