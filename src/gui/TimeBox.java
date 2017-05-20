package gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.event.TimeController;
import com.game.ActorFont;


public class TimeBox {
	private TimeController timeController;
	private Stage stage;
	private ActorFont time;

	public TimeBox(TimeController timer, Stage stage, BitmapFont font){
		this.timeController = timer;
		this.stage = stage;
		time = new ActorFont(font, timeController.toSring() , -5, 5);
		stage.addActor(time);
	}



}
