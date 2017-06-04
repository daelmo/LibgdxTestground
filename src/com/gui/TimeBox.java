package com.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.TimeController;
import com.game.ActorFont;


public class TimeBox {
	private TimeController timeController;
	private Stage stage;
	private ActorFont timeText;

	public TimeBox(TimeController timeController, Stage stage, BitmapFont font){
		this.timeController = timeController;
		this.stage = stage;
		this.timeText = new ActorFont(font, this.timeController, -5, 5);
		this.stage.addActor(timeText);
	}



}
