package com.person;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Person extends Actor {

	public enum ViewDirection {
		down, left, top, right
	}
	
	private Statistic statistic;
	private Body body;
	public ViewDirection view;
	private Stage stage;
	private State state;

	public Person(Stage stage) {
		this.body = new Body();
		this.view = ViewDirection.left;
		this.stage = stage;
		this.stage.addActor(this);
		this.statistic = new Statistic(this);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		body.draw(batch, alpha, view);

	}

	@Override
	public void act(float delta){
		statistic.update(delta);
	}

	public void setState(State state){
		this.state = state;
	}

}
