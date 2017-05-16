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


		if(Math.abs(wholeX) < person.statistic.walkingSpeed){
			stepX = wholeX;
		}else{
			stepX = Math.signum(wholeX) * person.statistic.walkingSpeed;
		}

		if(Math.abs(wholeY) < person.statistic.walkingSpeed){
			stepY = wholeY;
		}else{
			stepY = Math.signum(wholeY) * person.statistic.walkingSpeed;
		}
		person.movePosition(stepX * delta, stepY * delta);
	}

	//becomes true when position is reached
	public boolean checkCondition(){
		return goalPosition.compareTo(person.getPosition());
	}

	@Override
	public String toString() {
		return " is walking";
	}


}
