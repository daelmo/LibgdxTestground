package com.action;

import com.game.Constants;
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
	private Pair<Integer,Integer> startCoorinates, goalCoordinates;
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
		this.startCoorinates=new Pair<Integer,Integer>(
				person.getPosition().getIntX() / Constants.TILE_WIDTH,
				person.getPosition().getIntY() / Constants.TILE_HEIGHT);
		this.goalCoordinates=new Pair<Integer, Integer>(
				finalGoalPosition.getIntX() / Constants.TILE_WIDTH,
				finalGoalPosition.getIntY() / Constants.TILE_HEIGHT);
		this.uncheckedNodes.put(
				startCoorinates,
				new Node(startCoorinates,
						null,
						calculateHeuristic(startCoorinates, goalCoordinates),
						0 ));
		//System.out.println(performAStar());

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

	private int calculateHeuristic(Pair<Integer, Integer> examinedCoordinates,
	                               Pair<Integer, Integer> goalCoordinates){
		int deltaX = Math.abs(examinedCoordinates.getKey() - goalCoordinates.getKey());
		int deltaY = Math.abs(examinedCoordinates.getValue() - goalCoordinates.getValue());
		int straight = Math.max(deltaY,deltaX) - Math.min(deltaY, deltaX);
		int diagonal = Math.min(deltaX, deltaY);
		return straight + diagonal;
	}

	private void checkNode(Node node){
		int positionX, positionY;
		int[][] deltaNeighbourPositions =
				{{0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1}, {-1,0}, {-1, 1} };
		for (int[] deltaPosition : deltaNeighbourPositions){
			positionX = node.currentCoordinates.getKey() + deltaPosition[0];
			positionY = node.currentCoordinates.getValue() + deltaPosition[1] ;
			if(!level.isInLevel(positionX, positionY)){continue;}
			if(!level.isTraversable(positionX, positionY)){continue;}
			if(uncheckedNodes.get(new Pair(positionX,positionY)) == null){continue;}
			if(checkedNodes.get(new Pair(positionX,positionY)) == null){continue;}

			Pair newCoordinates = new Pair(positionX, positionY);
			Node newNode = new Node(
					newCoordinates, node.currentCoordinates,
					node.cost+1+calculateHeuristic( newCoordinates, goalCoordinates),
					node.cost +1);
			uncheckedNodes.put(newCoordinates, newNode);



		}
		checkedNodes.put(node.currentCoordinates, node);
		uncheckedNodes.remove(node.currentCoordinates);

	}

	private ArrayList<Node> performAStar(){
		while(Collections.min(uncheckedNodes.values()).currentCoordinates != goalCoordinates){
			Node node = Collections.min(uncheckedNodes.values());
			checkNode(node);
		}
		ArrayList<Node> resultPath= new ArrayList<>();
		Node endNode=Collections.min(uncheckedNodes.values());
		Node node= endNode;
		resultPath.add(endNode);

		return resultPath;
	}


	private class Node implements Comparable<Node>{

		Pair<Integer, Integer> originCoordinates;
		Pair<Integer, Integer> currentCoordinates;
		int score;
		int cost;

		Node(Pair<Integer, Integer> currentCoordinates,
		     Pair<Integer, Integer> originCoordinates,
		     int score, int cost){
			this.score = score;
			this.originCoordinates = originCoordinates;
			this.currentCoordinates = currentCoordinates;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare( this.score, node.score);
		}
	}
}
