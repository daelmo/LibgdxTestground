package com.level;

import com.game.Constants;

import java.util.Random;

public class Position {
	private float x;
	private float y;

	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getIntX() {
		return (int) x;
	}

	public int getIntY() {
		return (int) y;
	}

	public float getFloatX() {
		return x;
	}

	public float getFloatY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.x = y;
	}

	public void addX(float x) {
		this.x += x;
	}

	public void addY(float y) {
		this.y += y;
	}

	@Override
	public String toString() {
		return getIntX() + " " + getIntY();
	}

	// stop action when true, go on when false
	public boolean compareTo(Position position) {
		if (Math.abs(this.x - position.x) > 3.9f) return false;
		if (Math.abs(this.y - position.y) > 3.9f) return false;
		return true;
	}

	public static Position getRandomPosition(){
		Random random = new Random();
		int x = random.nextInt( Constants.TILE_WIDTH *10) +1;
		int y = random.nextInt(Constants.TILE_HEIGHT *10) +1;
		return new Position(x,y);
	}

	public float calculateSlope(Position position){
		float deltaX = this.getFloatX() - position.getFloatX();
		float deltaY = this.getFloatY() - position.getFloatY();

		float slope = deltaY/deltaX;
		return slope;
	}
}
