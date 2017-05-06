package com.person;

import com.action.Walking;
import com.action.ActiveScheduler;
import com.action.Scheduler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.Date;
import com.event.TimeController;
import com.game.ActorFont;
import com.level.Position;

import java.util.ArrayList;

public class Person extends Actor {
	public String name = "Gustav";
	public Date birthday;
	private Position position;
	private Body body;
	public ViewDirection view = ViewDirection.left;
	private State state = State.active;
	public Statistic statistic;
	private PersonGrowth growth;
	private Stage stage;
	private Scheduler scheduler;
	private final float shakeAmplitude = 5.0f;
	private float rotation = 0;
	private ArrayList<Walking> actions = new ArrayList<Walking>();
	private Group FontGroup = new Group();
	private ActorFont printName;


	private Person(Stage stage, BitmapFont font, PersonGrowth growth) {
		this.stage = stage;
		this.stage.addActor(this);
		this.growth = growth;
		this.body = new Body(growth);
		this.statistic = new Statistic(this);
		stage.addActor(FontGroup);
		printName = new ActorFont(font, name, 50, 0);
		FontGroup.addActor(printName);
	}

	public static Person generatePerson(Stage stage, BitmapFont font, TimeController timeController, PersonGrowth growth) {
		Person person = new Person(stage, font, growth);
		person.setBirthday(timeController.generateBirthday(growth));
		person.setPosition(new Position(10, 10));
		person.scheduler = new ActiveScheduler();
		return person;
	}

	private void setBirthday(Date birthday) {
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
		if(state == State.active) {
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

	public State getState() {
		return state;
	}

}
