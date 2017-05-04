package com.action;

import com.level.Position;
import com.person.Person;

import java.util.Random;

public class ActiveScheduler implements Scheduler {

	public Walking getAction(Person person) {
		Random random = new Random();
		int x = random.nextInt( 64*10 - 32) +1;
		int y = random.nextInt(64*10 - 32) +1;
  		Position position = new Position(x,y);
		return new Walking(person, position);
	}
}
