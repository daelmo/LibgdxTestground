package com.action;

public interface Action {
	void execute(float delta);
	boolean checkCondition();
	String toString();
}
