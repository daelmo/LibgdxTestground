package com.action;

public interface Action {
	public void execute(float delta);
	public boolean checkCondition();
	public String toString();
}
