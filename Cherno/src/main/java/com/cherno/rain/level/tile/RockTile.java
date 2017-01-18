package com.cherno.rain.level.tile;

import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}
	

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	} // end render() method

	public boolean soild() {
		return true;
	} // end solid() method
}
