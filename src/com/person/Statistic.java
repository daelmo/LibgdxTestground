package com.person;
import com.event.Date;

public class Statistic {
	private Person person;
	private int walkingSpeed;
	private float health = 100;


	private static final float FULLHEALTH = 100;
	private static final float UNCONSCIOUS = 35;
	private static final float DEAD = 0;

	public Statistic(Person person){
		this.person = person;
	}

	public void update( float delta){
		takeDamage( delta * 3);
	}

	public void takeDamage(float damage){
		health -= damage;
		if(health < UNCONSCIOUS && person.getState() != State.unconscious){
			person.setState(State.unconscious);
		}
		if(health < DEAD && person.getState() != State.dead){
			person.setState(State.dead);
		}
	}

}
