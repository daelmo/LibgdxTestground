package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Date;
import com.event.TimeController;

import java.util.Random;

public class Person extends Actor {
	private Statistic statistic;
	private Body body;
	public ViewDirection view;
	private Stage stage;
	private State state;
	private final float shakeAmplitude = 5.0f;
	private float rotation = 0;
	private PersonGrowth growth;

	private Person(Stage stage, Date birthday, PersonGrowth growth) {
		this.stage = stage;
		this.stage.addActor(this);
		this.growth = growth;
		this.body = new Body(growth);
		this.view = ViewDirection.left;
		this.statistic = new Statistic(this, birthday);
		this.state = State.active;
	}

	public static Person generatePerson(Stage stage, TimeController timeController, PersonGrowth growth){
		int min, max;
		switch (growth){
			case baby:
				min = 0;
				max = 4;
				break;
			case kid:
				min = 5;
				max = 14;
				break;
			case teenager:
				min = 15;
				max = 20;
				break;
			default:
				min = 20;
				max = 56;
		}
		Random rand = new Random();
		int age = rand.nextInt((max - min) + 1) + min;
		Date birthday = timeController.generateBirthday(age);



		return new Person(stage, birthday, growth);
	}



	@Override
	public void draw(Batch batch, float alpha) {
		if(state == State.active){
			body.draw(batch, alpha, view, 0f);
		}
		if (state == State.unconscious){
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, view, -90f + MathUtils.sin(rotation) * shakeAmplitude);
		}
		if (state == State.dead){
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
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

	public State getState(){
		return state;
	}

}
