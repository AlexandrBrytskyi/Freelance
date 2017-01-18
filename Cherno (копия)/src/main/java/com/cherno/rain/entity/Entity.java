package com.cherno.rain.entity;

import java.util.Random;

import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;
import com.cherno.rain.level.Level;

public class Entity {

	protected double x, y;
	protected Sprite sprite;
	private boolean removed = false; // removed from level or not
	protected Level level;
	protected final Random random = new Random();

	public Entity() {

	}

	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void update() {

	} // end update() method

	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
	} // end render() method

	public Sprite getSprite() {
		return sprite;
	}

	public void remove() {
		// remove from level
		removed = true;
	} // end remove() method

	public boolean isRemoved() {
		return removed;
	} // end isRemoved() method

	public void init(Level level) {
		this.level = level;
	}
}
