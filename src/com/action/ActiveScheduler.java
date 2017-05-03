package com.action;

import com.level.Position;
import com.person.Person;

import java.util.Random;

public class ActiveScheduler implements Scheduler {

	public Walking getAction(Person person) {
		int x,y;
		Random random = new Random();
		x = random.nextInt( 64*10) +1;
		y = random.nextInt(64*10) +1;
  		Position position = new Position(x,y);
		return new Walking(person, position);
	}
}
