package com.cherno.rain.entity.projectile;

import com.cherno.rain.entity.spawner.ParticleSpawner;
import com.cherno.rain.entity.spawner.Spawner;
import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10; // Higher is slower

	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 8;
		damage = 20;
		sprite = Sprite.projectile_wizard;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		if(level.tileCollision((int)(x + nx), (int)(y + ny), 7, 4, 3)) {
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));

			remove();
		}
		move();
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}

	protected void move() {
		x += nx;
		y += ny;
		if(distance() > range) remove();
	}

	public double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

}
