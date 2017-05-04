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

	public void execute(float delta){
		float wholeX,wholeY, stepX, stepY;

		wholeX= goalPosition.getFloatX() - person.getPosition().getFloatX();
		wholeY= goalPosition.getFloatY() - person.getPosition().getFloatY();
		System.out.println( wholeX + " " + wholeY);
		stepX = Math.signum(wholeX) * person.statistic.walkingSpeed;
		stepY = Math.signum(wholeY) * person.statistic.walkingSpeed;
		person.movePosition(stepX * delta, stepY * delta);
	}

	public boolean checkCondition(){
		if(goalPosition.compareTo(person.getPosition())) return true;
		else return false;
	}

	@Override
	public String toString() {
		return " is walking";
	}


}
