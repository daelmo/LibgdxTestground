package com.event;

import java.util.Random;

public class Date {
	public Season season;
	public int day;
	public int year;
	public static final int DAYS_PER_SEASON = 30;
	public static final int SECONDS_PER_DAY = 10;
	public static final int SEASONS_PER_YEAR = Season.values().length;

	public Date(int year, Season season, int day){
		this.year = year;
		this.season = season;
		this.day = day;
	}

	public Date(int year, int season, int day) {
		this.year = year;
		this.season = Season.values()[season];
		this.day = day;
	}

	public Date getNextDate(){
		if(day++ <= DAYS_PER_SEASON){
			return new Date(year, season, day++);
		}
		if(season.ordinal() + 1 <= SEASONS_PER_YEAR){
			return new Date(year, Season.values()[season.ordinal() + 1], 1);
		}
		return new Date(year++, Season.spring, 1);
	}

	@Override
	public String toString(){
		return day + " " + season + " " + year;
	}

}
