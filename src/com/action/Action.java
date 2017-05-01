package com.action;

import com.level.Position;
import com.person.Person;

public class Action {
	public String name;
	private Person person;
	private Position position;

	public Action(Person person, Position position) {
		this.person = person;
		this.position = position;
	}

	public void execute() {

	}

	@Override
	public String toString() {
		return "walking";
	}


}
