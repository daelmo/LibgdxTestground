package com.action;

import com.level.Position;
import com.person.Person;

public class Walking implements Action{
	private Person person;
	private Position goalPosition;

	public Walking(Person person, Position goalPosition) {
		this.person = person;
		this.goalPosition = goalPosition;
	}

	public void execute(){
		float wholeX,wholeY, stepX, stepY;

		wholeX= goalPosition.getFloatX() - person.getPosition().getFloatX();
		wholeY= goalPosition.getFloatY() - person.getPosition().getFloatY();
		stepX = Math.min(wholeX , person.statistic.walkingSpeed);
		stepY = Math.min(wholeY , person.statistic.walkingSpeed);
		person.movePosition(stepX, stepY);
	}

	public boolean checkCondition(){
		if(person.getPosition() == goalPosition) return true;
		else return false;
	}

	@Override
	public String toString() {
		return " is walking";
	}


}
