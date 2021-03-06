package com.cherno.rain.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cherno.rain.entity.mob.Chaser;
import com.cherno.rain.entity.mob.Dummy;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	} // end constructor
	
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width =  image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			System.out.println("Exception! Could not load level file!");
		}
		for(int i = 0; i < 1; i++){
			add(new Chaser(20, 55));
			add(new Dummy(20, 55));
		}
	} // end loadLevel() method
	
	
	protected void generateLevel() {
	}
	
}
