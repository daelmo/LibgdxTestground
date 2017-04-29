package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Person extends Actor {
	private Statistic statistic;
	private Body body;
	public ViewDirection view;
	private Stage stage;
	private State state;
	private final float shakeAmplitude = 5.0f;
	private float rot = 0;

	public Person(Stage stage) {
		this.body = new Body();
		this.view = ViewDirection.left;
		this.stage = stage;
		this.stage.addActor(this);
		this.statistic = new Statistic(this);
		this.state = State.active;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if(state == State.active){
			body.draw(batch, alpha, view, 0f);
		}
		if (state == State.unconscious){
			rot = (rot + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, view, -90f + MathUtils.sin(rot) * shakeAmplitude);
		}
		if (state == State.dead){
			rot = (rot + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, view, -90f);
		}
	}

	@Override
	public void act(float delta){
		statistic.update(delta);
	}

	public void setState(State state){
		this.state = state;
	}

}
