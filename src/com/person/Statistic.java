package com.person;
import com.event.Date;
import com.event.TimeController;

public class Statistic {
	private Person person;

	public String name;
	public Date birthday;
	private int speedWalking;
	private float health = 100;
	private TimeController timeController;


	private static final float FULLHEALTH = 100;
	private static final float UNCONSCIOUS = 35;
	private static final float DEAD = 0;

	public Statistic(Person person, TimeController timeController){
		this.person = person;
		this.birthday = timeController.generateBirthday(17);
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
