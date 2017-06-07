package com.event;

import box2dLight.RayHandler;
import com.person.PersonGrowth;

import java.util.Random;

import static com.event.Date.SECONDS_PER_DAY;

public class TimeController {
	private double absoluteTime =0; //passed game absoluteTime in seconds
	public Date currentDate;
	public float currentSeconds;
	private RayHandler rayHandler;

	public TimeController(RayHandler rayHandler, Season season){
		this.rayHandler = rayHandler;
		this.currentDate = new Date(1, season, 1);
		this.currentSeconds = Math.round(0.5 * SECONDS_PER_DAY);
		rayHandler.setShadows(true);
	}

	public void addTime(float delta){
		absoluteTime += delta;
		currentSeconds += delta;
		if(currentSeconds >= SECONDS_PER_DAY){
			currentDate = currentDate.getNextDate();
			currentSeconds = currentSeconds % SECONDS_PER_DAY;
		}
		//change lightening
		float currentLightening = 0.6f - 0.4f* (float) Math.cos((currentSeconds*6)/SECONDS_PER_DAY  );
		rayHandler.setAmbientLight(currentLightening);

	}

	public Date generateBirthday(PersonGrowth growth){
		Random rn = new Random();
		int age = rn.nextInt((growth.max - growth.min) + 1) + growth.min;
		int birthDay = rn.nextInt((Date.DAYS_PER_SEASON - 1) + 0) + 1;
		int birthSeason = rn.nextInt ((Date.SEASONS_PER_YEAR -1) +0) +1;
		int birthYear = currentDate.year - age;
		return new Date(birthYear, birthSeason, birthDay);
	}

	public String toString(){
		return currentDate.toString() + " " + getCurrentHour() + " h";
	}

	public int getCurrentHour(){
		return Math.round(currentSeconds/SECONDS_PER_DAY*24);
	}
}
