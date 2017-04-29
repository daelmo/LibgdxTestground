package com.event;

import java.util.Random;

import static com.event.Date.SECONDS_PER_DAY;

public class TimeController {
	private double absoluteTime =0; //passed game absoluteTime in seconds
	public Date currentDate;
	public float currentSeconds;

	public TimeController(Season season){
		this.currentDate = new Date(1, season, 1);
		this.currentSeconds = 0;
	}

	public void addTime(float delta){
		absoluteTime += delta;
		currentSeconds += delta;
		if(currentSeconds >= SECONDS_PER_DAY){
			currentDate = currentDate.getNextDate();
			currentSeconds = currentSeconds % SECONDS_PER_DAY;
		}
	}

	public Date generateBirthday(int year){
		Random rn = new Random();
		int birthDay = rn.nextInt((Date.DAYS_PER_SEASON - 1) + 0) + 1;
		int birthSeason = rn.nextInt ((Date.SEASONS_PER_YEAR -1) +0) +1;
		int birthYear = currentDate.year - year;
		return new Date(birthYear, birthSeason, birthDay);
	}

}
