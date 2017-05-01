package com.action;

import com.level.Position;
import com.person.Person;

public class ActiveScheduler implements Scheduler {

	public Action getAction(Person person) {
  		Position position = new Position(40,40);
		return new Action(person, position);
	}
}
