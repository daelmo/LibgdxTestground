package com.level;

import com.game.Constants;

import java.util.Random;

public class Position {
	private float X;
	private float Y;

	public Position(float X, float Y) {
		this.X = X;
		this.Y = Y;
	}

	public int getIntX() {
		return (int) X;
	}

	public int getIntY() {
		return (int) Y;
	}

	public float getFloatX() {
		return X;
	}

	public float getFloatY() {
		return Y;
	}

	public void setFloatX(float X) {
		this.X = X;
	}

	public void setFloatY(float Y) {
		this.X = Y;
	}

	public void addFloatX(float X) {
		this.X += X;
	}

	public void addFloatY(float Y) {
		this.Y += Y;
	}

	@Override
	public String toString() {
		return this.X + " " + this.Y;
	}

	// stop action when true, go on when false
	public boolean compareTo(Position position) {
		if (Math.abs(this.X - position.X) > 1.9f) return false;
		if (Math.abs(this.Y - position.Y) > 1.9f) return false;
		return true;
	}

	public static Position getRandomPosition(){
		Random random = new Random();
		int x = random.nextInt( Constants.TILE_WIDTH *10) +1;
		int y = random.nextInt(Constants.TILE_HEIGHT *10) +1;
		return new Position(x,y);
	}
}
