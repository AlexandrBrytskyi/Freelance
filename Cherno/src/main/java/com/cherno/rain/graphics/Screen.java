package com.cherno.rain.graphics;

import java.util.Random;

import com.cherno.rain.entity.mob.Chaser;
import com.cherno.rain.entity.mob.Mob;
import com.cherno.rain.entity.projectile.Projectile;
import com.cherno.rain.level.tile.Tile;

public class Screen {
	
	public int width, height;
	public int xOffset, yOffset;
	
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	} // end constructor
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	} // end clear() method
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed){
		if(fixed){
			xp = xp - xOffset;
			yp = yp - yOffset;
		}
		for(int y = 0; y < sprite.getHeight(); y++){
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++){
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
//	// old method
//	public void render(int xOffset, int yOffset) {
//		for(int y = 0; y < height; y++) {
//			int yp = y + yOffset;
//			if(yp < 0 || yp >= height) continue;
//			for(int x = 0; x < width; x++) {
//				int xp = x + xOffset;
//				if(xp < 0 || xp >= width) continue;
//				pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
//			} 
//		} 
//	} // end render() method
	
	public void renderProjectile(int xp, int yp, Projectile p){
		xp = xp - xOffset;
		yp = yp - yOffset;
		for(int y = 0; y < p.getSpriteSize(); y++){
			int ya = y + yp;
			for(int x = 0; x < p.getSpriteSize(); x++){
				int xa = x + xp;
				if(xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if(col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = yp + y; // ya for yabsolute
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = xp + x; // xa for xabsolute
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	} // end renderTIle() method
	
	public void renderMob(int xp, int yp, Mob mob) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = yp + y; // ya for yabsolute
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = xp + x; // xa for xabsolute
				int xs = x;
				if(xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * 32];
				if((mob instanceof Chaser) && col == 0xff6F614C) col = 0xffFF4235;
				if(col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	} // end renderPlayer() method
	
	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp = xp - xOffset;
		yp = yp - yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = yp + y; // ya for yabsolute
			int ys = y;
			if(flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = xp + x; // xa for xabsolute
				int xs = x;
				if(flip == 1 || flip == 3) xs = 31 - x;
				if(xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if(col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	} // end renderPlayer() method
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	} // end setOffset() method
}
