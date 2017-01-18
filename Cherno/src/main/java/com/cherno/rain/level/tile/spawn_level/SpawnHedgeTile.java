package com.cherno.rain.level.tile.spawn_level;

import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;
import com.cherno.rain.level.tile.Tile;

public class SpawnHedgeTile extends Tile {

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	} // end render() method
	
	public boolean solid(){
		return true;
	}
	
	public boolean breakable(){
		return true;
	}
}
