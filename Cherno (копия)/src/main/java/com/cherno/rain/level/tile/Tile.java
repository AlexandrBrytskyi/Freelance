package com.cherno.rain.level.tile;

import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;
import com.cherno.rain.level.tile.spawn_level.SpawnFloorTile;
import com.cherno.rain.level.tile.spawn_level.SpawnGrassTile;
import com.cherno.rain.level.tile.spawn_level.SpawnHedgeTile;
import com.cherno.rain.level.tile.spawn_level.SpawnWallTile;
import com.cherno.rain.level.tile.spawn_level.SpawnWaterTile;

public class Tile {

	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public static final int col_spawn_grass = 0XFF00FF08;
	public static final int col_spawn_hedge = 0; // unused
	public static final int col_spawn_water = 0; // unsed
	public static final int col_spawn_wall1 = 0XFF808080;
	public static final int col_spawn_wall2 = 0XFF0C0800;
	public static final int col_spawn_floor = 0XFF7F5500;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	} // end constructor
	
	public void render(int x, int y, Screen screen) {
	} // end render() method
	
	public boolean solid() {
		return false;
	} // end solid() method
}
