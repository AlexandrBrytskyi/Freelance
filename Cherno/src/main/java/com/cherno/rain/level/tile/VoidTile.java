package com.cherno.rain.level.tile;

import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	} // end constructor
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	} // end render() method

}
