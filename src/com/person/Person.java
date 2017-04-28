package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
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
	private final float shakeAmplitude = 5.0f;
	private float rot = 0;

	public Person(Stage stage) {
		this.body = new Body();
		this.view = ViewDirection.left;
		this.stage = stage;
		this.stage.addActor(this);
		this.statistic = new Statistic(this);
		this.state = new StateActive();
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if(state.getClass() == StateActive.class){
			body.draw(batch, alpha, view, 0f);
		}
		if (state.getClass() == StateUnconscious.class){
			rot = (rot + Gdx.graphics.getDeltaTime() * 5f);
			body.draw(batch, alpha, view, 90f + MathUtils.sin(rot) * shakeAmplitude);
		}
		if (state.getClass() == StateDead.class){
			rot = (rot + Gdx.graphics.getDeltaTime() * 5f);
			body.draw(batch, alpha, view, 90f);
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
