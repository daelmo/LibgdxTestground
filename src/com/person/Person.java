package com.person;

import com.action.Walking;
import com.action.ActiveScheduler;
import com.action.Scheduler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Date;
import com.event.TimeController;
import com.level.Position;

import java.util.ArrayList;

public class Person extends Actor {
	public String name;
	public Date birthday;
	private Position position;
	private Body body;
	public ViewDirection view;
	private State state;
	private Statistic statistic;
	private PersonGrowth growth;
	private Stage stage;
	private Scheduler scheduler;
	private final float shakeAmplitude = 5.0f;
	private float rotation = 0;
	private ArrayList<Walking> actions;


	private Person(Stage stage, PersonGrowth growth) {
		this.stage = stage;
		this.position = position;
		this.stage.addActor(this);
		this.growth = growth;
		this.body = new Body(growth);
		this.view = ViewDirection.left;
		this.statistic = new Statistic(this);
		this.state = State.active;
		this.actions = new ArrayList<Walking>();
	}

	public static Person generatePerson(Stage stage, TimeController timeController, PersonGrowth growth){
		Person person = new Person(stage, growth);
		person.setBirthday(timeController.generateBirthday(growth));
		person.setPosition(new Position(30,30));
		person.scheduler = new ActiveScheduler();
		return person;
	}

	private void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public void setPosition(Position position){
		this.position = position;
	}

	public Position getPosition(){
		return position;
	}


	@Override
	public void draw(Batch batch, float alpha) {
		if(state == State.active){
			body.draw(batch, alpha, position, view, 0f);
		}
		if (state == State.unconscious){
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, position,  view, -90f + MathUtils.sin(rotation) * shakeAmplitude);
		}
		if (state == State.dead){
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, position, view, -90f);
		}
	}

	@Override
	public void act(float delta){
		if(!actions.isEmpty() && actions.get(0).checkCondition()){
			actions.remove(0);
		}
		if(actions.isEmpty()){
			actions.add(scheduler.getAction(this));
		}
		actions.get(0).execute();
		statistic.update(delta);
	}

	public void setState(State state){
		this.state = state;
	}

	public State getState(){
		return state;
	}


}
