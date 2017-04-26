package com.event;

public class Date {
	public TimeController.Season season;
	public int day;
	public int year;
	public static final int DAYS_PER_SEASON = 30;
	public static final int SECONDS_PER_DAY = 10;

	public Date(int year, TimeController.Season season, int day){
		this.year = year;
		this.season = season;
		this.day = day;
	}

	public Date getNextDate(){
		if(day++ <= DAYS_PER_SEASON){
			return new Date(year, season, day++);
		}
		if(season.ordinal() + 1 <= TimeController.Season.values().length){
			return new Date(year, TimeController.Season.values()[season.ordinal() + 1], 1);
		}
		return new Date(year++, TimeController.Season.spring, 1);
	}

	@Override
	public String toString(){
		return day + " " + season + " " + year;
	}
}
