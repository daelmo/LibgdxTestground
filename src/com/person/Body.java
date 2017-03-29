package com.person;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.Random;
import com.person.Person;

/**
 *
 * @author fine
 */
public class Body {

	private static Sprite[][][] sprites = null;
	private int[] variants;
	private Color[] colors;
	private static final int[] variantCounts = {1, 1, 1, 1};
	private static final int bPartCount = variantCounts.length;
	private static final String[] ImgPaths = {
		"body/body%03d.png",
		"body/head%03d.png",
		"body/hands%03d.png",
		"body/hair%03d.png"
	};

	public Body() {
		colors = new Color[4];
		colors[0] = Color.RED; // vet
		colors[1] = Color.BROWN; // head skin
		colors[2] = Color.BROWN; // hands skin
		colors[3] = Color.GRAY; // hair 

		if (sprites == null) {
			initSprites();
		}
		variants = new int[bPartCount];
		Random random = new Random();
		for (int bPart = 0; bPart < bPartCount; bPart++) {
			variants[bPart] = random.nextInt(variantCounts[bPart]);
		}
	}

	private static void initSprites() {
		int viewDirLength = Person.ViewDir.values().length;
		sprites = new Sprite[bPartCount][][]; // [Körperteil][Variante][Richtung]
		for (int bPart = 0; bPart < bPartCount; bPart++) {
			sprites[bPart] = new Sprite[variantCounts[bPart]][viewDirLength];
			for (int variant = 0; variant < variantCounts[bPart]; variant++) {
				Texture t = new Texture(Gdx.files.internal(String.format(ImgPaths[bPart], variant)));
				for (int dir = 0; dir < viewDirLength; dir++) {
					sprites[bPart][variant][dir] = new Sprite(t, 128 * dir, 0, 128, 128);
				}
			}
		}
	}

	public void draw(Batch batch, float alpha, Person.ViewDir view) {
		for (int bPart = 0; bPart < bPartCount; bPart++) {
			Sprite s = sprites[bPart][variants[bPart]][view.ordinal()];
			s.setColor(colors[bPart]);
			s.setPosition(30, 30);
			s.draw(batch);
		}
	}
}
