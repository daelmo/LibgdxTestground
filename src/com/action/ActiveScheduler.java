package com.action;

import com.level.Position;
import com.level.Tile;
import com.person.Person;
import java.util.Random;

public class ActiveScheduler implements Scheduler {

	public Walking getAction(Person person) {
		Random random = new Random();
		int x = random.nextInt( Tile.WIDTH *10) +1;
		int y = random.nextInt(Tile.HEIGHT *10) +1;
		return new Walking(person, new Position(x,y));
	}
}
