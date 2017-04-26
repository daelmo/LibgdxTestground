package com.event;

import static com.event.Date.SECONDS_PER_DAY;

public class TimeController {

	public enum Season {
		spring, summer, autumn, winter
	}

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

}
