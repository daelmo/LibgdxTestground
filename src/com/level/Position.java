package com.level;

public class Position {
	private float X;
	private float Y;

	public Position(float X, float Y){
		this.X = X;
		this.Y = Y;
	}

	public int getIntX(){
		return (int) X;
	}

	public int getIntY(){
		return (int) Y;
	}

	public float getFloatX(){
		return X;
	}

	public float getFloatY(){
		return Y;
	}
	
}
