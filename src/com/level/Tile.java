package com.level;

import com.game.Constants;

import java.util.Arrays;

public class Tile {

	public final int posX, posY;
	public boolean[] isTraversable;

	public Tile(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		isTraversable = new boolean[Constants.MAX_ZINDEX];
		Arrays.fill(isTraversable, Boolean.TRUE);
	}

	public void setTraversable(int z, boolean bool) {
		isTraversable[z] = bool;
	}


}
