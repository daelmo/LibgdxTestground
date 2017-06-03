package com.action;

import com.level.Position;
import com.person.Person;
import com.person.ViewDirection;

public class Walking implements Action{
	private Person person;
	private Position goalPosition;

	public Walking(Person person, Position goalPosition) {
		this.person = person;
		this.goalPosition = goalPosition;
	}

	public void execute(float delta){
		float[] steps = calculateStep();

		calculateViewDirection();

		//set position to new position
		person.movePosition(steps[0] * delta, steps[1] * delta);
	}

	//becomes true when position is reached
	public boolean checkCondition(){
		return goalPosition.compareTo(person.getPosition());
	}

	@Override
	public String toString() {
		return " is walking";
	}


	private float[] calculateStep(){
		float wholeX,wholeY, stepX, stepY;
		double distance;
		float necessarySteps;
		wholeX= goalPosition.getFloatX() - person.getPosition().getFloatX();
		wholeY= goalPosition.getFloatY() - person.getPosition().getFloatY();

		distance = Math.sqrt( wholeX*wholeX + wholeY*wholeY);

		if(distance >= person.statistic.walkingSpeed){
			necessarySteps = Math.round(distance/person.statistic.walkingSpeed);
			stepX = (wholeX/necessarySteps);
			stepY = (wholeY/necessarySteps);
		}else{
			stepX = wholeX;
			stepY = wholeY;
		}
		return new float[]{stepX,stepY};
	}


	//sets Viewdirection for calculated step
	private void calculateViewDirection(){
		float deltaX, deltaY, slope;
		deltaX = goalPosition.getFloatX() - person.getPosition().getFloatX();
		deltaY = goalPosition.getFloatY() - person.getPosition().getFloatY();

		slope = deltaY/deltaX;

		if(deltaY >0){
			person.setViewDirection(ViewDirection.top);
		}else{
			person.setViewDirection(ViewDirection.down);
		}

		if(deltaX > 0 && slope < 1 && slope > -1 ){
			person.setViewDirection(ViewDirection.right);
		}
		if(deltaX < 0 && slope < 1 && slope > -1 ){
			person.setViewDirection(ViewDirection.left);
		}
	}
}
