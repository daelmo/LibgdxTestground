package com.person;

public enum PersonGrowth {
	BABY("baby", 0,4),
	KID("kid", 5,14),
	TEENAGER("teenager", 15, 20),
	ADULT("adult", 20, 56);

	public int max, min;
	public String name;

	PersonGrowth(String name, int min, int max){
		this.name = name;
		this.max = max;
		this.min = min;
	}
}
