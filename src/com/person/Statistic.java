package com.person;

public class Statistic {
	private Person person;
	public float walkingSpeed = 60f;
	public float health = 100;


	public static final float FULLHEALTH = 100;
	public static final float UNCONSCIOUS = 35;
	public static final float DEAD = 0;

	public Statistic(Person person){
		this.person = person;
	}

	public void update( float delta){
		takeDamage( delta * 1);
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
