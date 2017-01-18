package com.cherno.rain.entity.projectile;

import java.util.Random;

import com.cherno.rain.entity.Entity;
import com.cherno.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny; // These are new x and new y which change always
	protected double range, damage, speed;
	protected double distance;
	
	protected double x, y;
	protected final Random random = new Random();

	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {

	}
}
