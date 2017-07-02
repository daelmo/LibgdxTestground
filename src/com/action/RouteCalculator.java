package com.action;

import com.game.Constants;
import com.level.Level;
import com.level.Position;
import javafx.util.Pair;

import java.util.*;


public class RouteCalculator {
	private Level level;
	private Map<Pair<Integer,Integer>, Node> uncheckedNodes;
	private Map<Pair<Integer,Integer>, Node> checkedNodes ;

	public RouteCalculator(Level level){
		this.level = level;
		this.uncheckedNodes = new HashMap<>();
		this.checkedNodes = new HashMap<>();
	}


	public LinkedList<Position> performAStar(Pair startCoorinates, Pair goalCoordinates){
		uncheckedNodes.put(
				startCoorinates,
				new Node(startCoorinates,
						null,
						calculateHeuristic(startCoorinates, goalCoordinates),
						0 ));

		while((
				(Collections.min(uncheckedNodes.values())).currentCoordinates.getKey() != goalCoordinates.getKey()) &&
				(Collections.min(uncheckedNodes.values())).currentCoordinates.getValue() != goalCoordinates.getValue()){
			Node node = Collections.min(uncheckedNodes.values());
			checkNode(node, goalCoordinates);
		}
		LinkedList<Position> resultPath= new LinkedList<>();
		Node node= Collections.min(uncheckedNodes.values());
		while (node != null){
			resultPath.add(new Position(
					node.currentCoordinates.getKey() * Constants.TILE_WIDTH +32,
					node.currentCoordinates.getValue() * Constants.TILE_HEIGHT+32));
			node = checkedNodes.get(node.originCoordinates);
		}
		Collections.reverse(resultPath);
		return resultPath;
	}

	private void checkNode(Node node, Pair goalCoordinates){
		int positionX, positionY;
		int[][] deltaNeighbourPositions =
				{{0,1}, {1,0}, {0, -1}, {-1,0} };
		for (int[] deltaPosition : deltaNeighbourPositions){
			positionX = node.currentCoordinates.getKey() + deltaPosition[0];
			positionY = node.currentCoordinates.getValue() + deltaPosition[1] ;
			if(!level.isInLevel(positionX, positionY)){continue;}
			if(!level.isTraversable(positionX, positionY)){continue;}
			if(uncheckedNodes.get(new Pair(positionX,positionY)) != null){continue;}
			if(checkedNodes.get(new Pair(positionX,positionY)) != null){continue;}

			Pair newCoordinates = new Pair(positionX , positionY);
			Node newNode = new Node(
					newCoordinates, node.currentCoordinates,
					node.cost+1+calculateHeuristic( newCoordinates, goalCoordinates),
					node.cost +1);
			uncheckedNodes.put(newCoordinates, newNode);
		}
		checkedNodes.put(node.currentCoordinates, node);
		uncheckedNodes.remove(node.currentCoordinates);
	}

	private int calculateHeuristic(Pair<Integer, Integer> examinedCoordinates,
	                               Pair<Integer, Integer> goalCoordinates){
		int deltaX = Math.abs(examinedCoordinates.getKey() - goalCoordinates.getKey());
		int deltaY = Math.abs(examinedCoordinates.getValue() - goalCoordinates.getValue());
		int straight = Math.max(deltaY,deltaX) - Math.min(deltaY, deltaX);
		int diagonal = Math.min(deltaX, deltaY);
		return straight + diagonal;
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
