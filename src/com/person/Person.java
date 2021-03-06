package com.person;

import com.action.Action;
import com.action.Scheduler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Date;
import com.game.ActorFont;
import com.game.Constants;
import com.level.Level;
import com.level.Position;

import java.util.ArrayList;
import java.util.Collections;

public class Person extends Actor {
	private String name;
	private Date birthday;
	private Position position;
	private PersonBody personBody;
	public ViewDirection view = ViewDirection.left;
	private PersonState personState = PersonState.active;
	public Statistic statistic;
	private PersonGrowth growth;
	private Scheduler scheduler;
	private final float shakeAmplitude = 5.0f;
	private float rotation = 0;
	private ArrayList<Action> actions = new ArrayList<Action>();
	private ActorFont printName;
	private Gender gender;
	private Level level;
	private Group group;
	private BitmapFont font;


	public Person(PersonGrowth growth) {
		this.growth = growth;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setPosition(Position position) {
		this.position = position;
		this.printName.setPosition(position);
	}

	public Position getPosition() {
		return position;
	}

	public void movePosition(float X, float Y) {
		this.position.addX(X);
		this.position.addY(Y);
	}


	@Override
	public void draw(Batch batch, float alpha) {
		if (personState == PersonState.active) {
			personBody.draw(batch, alpha, position, view, 0f);
		}
		if (personState == PersonState.unconscious) {
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;

			personBody.draw(batch, alpha, position, view, -90f + MathUtils.sin(rotation) * shakeAmplitude);
		}
		if (personState == PersonState.dead) {
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			personBody.draw(batch, alpha, position, view, -90f);
		}
	}

	@Override
	public void act(float delta) {
		if (personState == PersonState.active) {
			if (actions.isEmpty()) {
				actions.add(scheduler.getAction(this));
			}
			while (actions.get(0).checkCondition()) {
				actions.remove(0);
				actions.add(scheduler.getAction(this));
			}
			actions.get(0).execute(delta);
			statistic.update(delta);
		}
	}

	public void setPersonState(PersonState personState) {
		this.personState = personState;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public PersonState getPersonState() {
		return personState;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public void setPersonBody(PersonBody personBody) {
		this.personBody = personBody;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setViewDirection(ViewDirection viewDirection){
		this.view = viewDirection;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}

	public void setPrintName(ActorFont printName){
		this.printName = printName;
	}

	public ActorFont getPrintName(){
		return printName;
	}

	public void setLevel(Level level){
		this.level = level;
	}

}
