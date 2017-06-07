package com.action;

import com.level.Position;
import com.person.Person;
import com.person.ViewDirection;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Walking implements Action{
	private Person person;
	private Position currentGoalPosition;
	private List<Position> plannedRoute;
	private ListIterator<Position> iterator;

	public Walking(Person person, Position goalPosition) {
		this.person = person;
		this.plannedRoute = calculatePlannedRoute(goalPosition);
		this.iterator = plannedRoute.listIterator();
		this.currentGoalPosition = iterator.next();
	}

	public void execute(float delta){
		if(currentGoalPosition.compareTo(person.getPosition())){
			currentGoalPosition = iterator.next();
		}


		float[] steps = calculateStep(currentGoalPosition);
		calculateViewDirection(currentGoalPosition);

		//set position to new position
		person.movePosition(steps[0] * delta, steps[1] * delta);
	}

	//becomes true when position is reached
	public boolean checkCondition(){
		return currentGoalPosition.compareTo(person.getPosition()) && !iterator.hasNext();
	}

	@Override
	public String toString() {
		return " is walking";
	}


	private float[] calculateStep(Position currentGoalPosition){
		float wholeX,wholeY, stepX, stepY;
		double distance;
		float necessarySteps;
		wholeX= currentGoalPosition.getFloatX() - person.getPosition().getFloatX();
		wholeY= currentGoalPosition.getFloatY() - person.getPosition().getFloatY();

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


	//sets ViewDirection for calculated step
	private void calculateViewDirection(Position currentGoalPosition){
		float deltaX, deltaY, slope;
		deltaX = currentGoalPosition.getFloatX() - person.getPosition().getFloatX();
		deltaY = currentGoalPosition.getFloatY() - person.getPosition().getFloatY();

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

	public List<Position> getPlannedRoute() {
		return plannedRoute;
	}

	public List<Position> calculatePlannedRoute(Position goalPosition) {
		List<Position> list = new LinkedList<>();
		list.add(goalPosition);
		list.add(Position.getRandomPosition());
		return list;
	}

	private int calculateHeuristicFor(Position position, Position goalPosition){
		int deltaX = Math.abs(goalPosition.getIntX() - position.getIntX());
		int deltaY = Math.abs(goalPosition.getIntY() - position.getIntY());
		int straight = Math.max(deltaY,deltaX) - Math.min(deltaY, deltaX);
		int diagonal = Math.min(deltaX, deltaY);
		return straight + diagonal;
	}
}
