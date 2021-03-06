package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.level.Position;
import com.person.PersonGrowth;
import com.person.ViewDirection;

import java.util.Random;

public class PersonBody extends Actor {

	private static Sprite[][][] sprites = null;
	private int[] variants;
	private Color[] bodyColor;
	public final float SETOFFX= -64;
	public final float SETOFFY= -10;
	private static final int[] variantCounts = {1, 1, 1, 1};
	private static final int bodyPartCount = variantCounts.length;
	private static final int IMAGEWIDTH = 128;
	private static final String[] ImgPaths = {
		"body/body%03d.png",
		"body/head%03d.png",
		"body/hands%03d.png",
		"body/hair%03d.png"
	};
	private Color possibleColor[][];



	public PersonBody(PersonGrowth growth) {
		possibleColor = new Color[2][4];
		possibleColor[0][0] = Color.RED; // clothing color
		possibleColor[0][1] = Color.BROWN; // head skin
		possibleColor[0][2] = Color.BROWN; // hands skin
		possibleColor[0][3] = Color.GRAY; // hair color

		possibleColor[1][0] = Color.OLIVE; // clothing color
		possibleColor[1][1] = Color.SALMON; // head skin
		possibleColor[1][2] = Color.SALMON; // hands skin
		possibleColor[1][3] = Color.BLACK; // hair color

		if (sprites == null) {
			initSprites();
		}

		//init body colors and body part images for painting
		variants = new int[bodyPartCount];
		bodyColor = new Color[bodyPartCount];
		Random random = new Random();

		for (int bodyPart = 0; bodyPart < bodyPartCount; bodyPart++) {
			variants[bodyPart] = random.nextInt(variantCounts[bodyPart]);
			bodyColor[bodyPart] = possibleColor[random.nextInt(2)][bodyPart]; // clothing color
		}

		//setting hand = skin color
		bodyColor[1] = bodyColor[2];
	}

	private static void initSprites() {
		int viewDirLength = ViewDirection.values().length;
		sprites = new Sprite[bodyPartCount][][]; // [Körperteil][Variante][Richtung]
		for (int bPart = 0; bPart < bodyPartCount; bPart++) {
			sprites[bPart] = new Sprite[variantCounts[bPart]][viewDirLength];
			for (int variant = 0; variant < variantCounts[bPart]; variant++) {
				Texture t = new Texture(Gdx.files.internal(String.format(ImgPaths[bPart], variant)));
				for (int dir = 0; dir < viewDirLength; dir++) {
					sprites[bPart][variant][dir] = new Sprite(t, IMAGEWIDTH * dir, 0, IMAGEWIDTH, IMAGEWIDTH);
				}
			}
		}
	}

	public void draw(Batch batch, float alpha, Position position, ViewDirection view, float rotation) {
		for (int bPart = 0; bPart < bodyPartCount; bPart++) {
			Sprite s = sprites[bPart][variants[bPart]][view.ordinal()];
			s.setOrigin(IMAGEWIDTH/2,30);
			s.setColor(bodyColor[bPart]);
			s.setPosition(position.getFloatX() + SETOFFX, position.getFloatY() + SETOFFY);
			s.setRotation(rotation);
			s.draw(batch);
		}
	}
}
