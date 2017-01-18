package com.cherno.rain.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.atomic.AtomicInteger;

public class Mouse implements MouseListener, MouseMotionListener {

	protected volatile AtomicInteger mouseX = new AtomicInteger(-1);
	protected volatile AtomicInteger mouseY = new AtomicInteger(-1);
	protected volatile AtomicInteger mouseB = new AtomicInteger(-1);

	public int getX(){
		return mouseX.get();
	}
	
	public int getY(){
		return mouseY.get();
	}
	
	public int getB(){
		return mouseB.get();
	}
	public void mouseDragged(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		setMouseX(e.getX());
		setMouseY(e.getY());
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		setMouseB(e.getButton());
	}

	public void mouseReleased(MouseEvent e) {
		setMouseB(-1);
	}

	public void setMouseX(int mouseX) {
		this.mouseX.set(mouseX);
	}

	public void setMouseY(int mouseY) {
		this.mouseY.set(mouseY);
	}

	public void setMouseB(int mouseB) {
		this.mouseB.set(mouseB);
	}

	@Override
	public String toString() {
		return "Mouse{" +
				"mouseX=" + mouseX +
				", mouseY=" + mouseY +
				", mouseB=" + mouseB +
				'}';
	}
}
