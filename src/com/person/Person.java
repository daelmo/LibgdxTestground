package com.person;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


/**
 * @author fine
 */
public class Person extends Actor {

	public enum ViewDir {
		down, left, top, right;
	}
	
	private Statistic statistic;
	private Body body;
	public ViewDir view;
	private Stage stage;
	public State state;

	public Person(Stage stage) {
		body = new Body();
		view = ViewDir.left;
		this.stage = stage;
		this.stage.addActor(this);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		body.draw(batch, alpha, view);

	}
	
	public void act(){
		state.goNext();
	}
}
