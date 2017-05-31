package com.action;

import com.event.TimeController;
import com.person.Person;

public class Resting implements Action {
	private TimeController timeController;
	private Person person;

	public Resting(final Person person, TimeController timeController){
		this.person = person;
		this.timeController = timeController;
	}


	public void execute(float delta){

	}

	public boolean checkCondition(){
		if(timeController.getCurrentHour() > 6 && timeController.getCurrentHour() <18 ){
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return "is sleeping";
	}

}
