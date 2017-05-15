package com.person;

import com.action.Walking;
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
import com.level.Position;

import java.util.ArrayList;

public class Person extends Actor {
	public String name;
	public Date birthday;
	private Position position;
	private Body body;
	public ViewDirection view = ViewDirection.left;
	private State state = State.active;
	public Statistic statistic;
	private PersonGrowth growth;
	private Stage stage;
	private BitmapFont font;
	private Scheduler scheduler;
	private final float shakeAmplitude = 5.0f;
	private float rotation = 0;
	private ArrayList<Walking> actions = new ArrayList<Walking>();
	private Group FontGroup = new Group();
	public ActorFont printName;


	public Person(Stage stage, PersonGrowth growth, BitmapFont font) {
		stage.addActor(FontGroup);
		stage.addActor(this);
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
		this.position.addFloatX(X);
		this.position.addFloatY(Y);
	}


	@Override
	public void draw(Batch batch, float alpha) {
		if (state == State.active) {
			body.draw(batch, alpha, position, view, 0f);
		}
		if (state == State.unconscious) {
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, position, view, -90f + MathUtils.sin(rotation) * shakeAmplitude);
		}
		if (state == State.dead) {
			rotation = (rotation + Gdx.graphics.getDeltaTime() * 5f);
			view = ViewDirection.left;
			body.draw(batch, alpha, position, view, -90f);
		}
	}

	@Override
	public void act(float delta) {
		if (state == State.active) {
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

	public void setState(State state) {
		this.state = state;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}


	public State getState() {
		return state;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public void setName(String name){
		this.name = name;
	}

	public void addText(ActorFont actorFont){
		FontGroup.addActor(actorFont);
	}

}
