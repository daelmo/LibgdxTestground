package com.person;

public class Statistic {
	private Person person;
	public float walkingSpeed = 60f;
	public float hungerRate = 3;
	public float health = FULLHEALTH;
	public float satiety = FULLSATIETY;


	public static final float FULLHEALTH = 100;
	public static final float UNCONSCIOUS = 35;
	public static final float DEAD = 0;

	public static final float FULLSATIETY = 100;
	public static final float LOWSATIETY = 35;
	public static final float NOSATIETY = 0;

	public Statistic(Person person){
		this.person = person;
	}

	public void update( float delta){
		if(satiety <= 0){
			takeDamage( delta * 1);
		}
		beHungry();
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

	private void beHungry(){
		satiety = Math.max(satiety - hungerRate, 0);
	}

}
