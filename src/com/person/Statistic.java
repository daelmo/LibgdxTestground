package com.person;

public class Statistic {
	private Person person;
	public float walkingSpeed = 60f;
	public float hungerRate = 3;
	public float stressingRate = 3;
	public float health = FULL_HEALTH;
	public float satiety = FULL_SATIETY;
	public float relaxation = FULL_RELAXATION;


	public static final float FULL_HEALTH = 100;
	public static final float UNCONSCIOUS = 35;
	public static final float DEAD = 0;

	public static final float FULL_SATIETY = 100;
	public static final float HUNGRY = 35;
	public static final float URGENTLY_HUNGRY = 0;

	public static final float FULL_RELAXATION = 100;
	public static final float STRESSED = 35;
	public static final float CRAZY = 0;

	public Statistic(Person person){
		this.person = person;
	}

	public void update( float delta){
		if(satiety <= 0){
			takeDamage( delta * 1);
		}
		becomeHungry(delta);
		becomeStressed(delta);
	}

	public void takeDamage(float damage){
		health -= damage;
		if(health < UNCONSCIOUS && person.getPersonState() != PersonState.unconscious){
			person.setPersonState(PersonState.unconscious);
		}
		if(health < DEAD && person.getPersonState() != PersonState.dead){
			person.setPersonState(PersonState.dead);
		}
	}

	private void becomeHungry(float delta){
		satiety = Math.max(satiety - hungerRate * delta, 0);
	}

	private void becomeStressed(float delta){
		relaxation = Math.max(relaxation - stressingRate*delta, 0);

	}



}
