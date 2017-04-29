package com.person;
import java.util.Date;

public class Statistic {
	private Person person;

	public String name;
	public Date birthday;
	private int speedWalking;
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
		if(health < UNCONSCIOUS){
			person.setState(State.unconscious);
		}
		if(health < DEAD){
			person.setState(State.dead);
		}
	}

}
