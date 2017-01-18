package com.cherno.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean[120]; // 120 is the no.of keys on ur keyboard. High no, just in case
	public boolean up, down, left, right;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	} // end update method()
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		update();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public String toString() {
		return "Keyboard{" +
				", up=" + up +
				", down=" + down +
				", left=" + left +
				", right=" + right +
				'}';
	}
}
