package com.action;

import com.level.Level;
import com.level.Position;
import com.person.Person;
import com.person.ViewDirection;
import javafx.util.Pair;

import java.util.*;

public class Walking implements Action{
	private Person person;
	private Position currentGoalPosition;
	private List<Position> plannedRoute;
	private ListIterator<Position> iterator;
	private Level level;
	private Map<Pair<Integer,Integer>, Node> uncheckedNodes;
	private Map<Pair<Integer,Integer>, Node> checkedNodes ;

	public Walking(Person person, Position finalGoalPosition, Level level) {
		this.person = person;
		this.level = level;
		this.plannedRoute = calculatePlannedRoute(finalGoalPosition);
		this.iterator = plannedRoute.listIterator();
		this.currentGoalPosition = iterator.next();
		this.uncheckedNodes = new HashMap<>();
		this.checkedNodes = new HashMap<>();
		this.uncheckedNodes.put(new Pair(0,0), new Node(0,0,0));
		//System.out.println(Collections.min(uncheckedNodes.values())  + " 2");
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
		return list;
	}

	private int calculateHeuristic(Position position, Position goalPosition){
		int deltaX = Math.abs(goalPosition.getIntX() - position.getIntX());
		int deltaY = Math.abs(goalPosition.getIntY() - position.getIntY());
		int straight = Math.max(deltaY,deltaX) - Math.min(deltaY, deltaX);
		int diagonal = Math.min(deltaX, deltaY);
		return straight + diagonal;
	}

	private List<Node> checkNode(Node node){
		int positionX, positionY;
		int[][] deltaNeighbourPositions =
				{{0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1}, {-1,0}, {-1, 1} };
		for (int[] deltaPosition : deltaNeighbourPositions){
			positionX = node.originX+deltaPosition[0];
			positionY = node.originY+deltaPosition[1];
			if(!level.isInLevel(positionX, positionY)){continue;}
			if(!level.isTraversable(positionX, positionY)){continue;}



		}
		return new ArrayList<Node>();
	}


	private class Node implements Comparable<Node>{

		int originX, originY;
		int score;

		Node(int score, int originX, int originY){
			this.score = score;
			this.originX = originX;
			this.originY = originY;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare( this.score, node.score);
		}
	}
}
