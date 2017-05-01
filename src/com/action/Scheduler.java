package com.action;

import com.person.Person;

public interface Scheduler {
	public abstract Action getAction(Person person);
}
