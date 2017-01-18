package com.cherno.rain.client_server;


import com.cherno.rain.input.Mouse;

import java.awt.event.MouseEvent;

public class MouseMustNotify extends Mouse {

    private KeyMouseObservable observable;
    private boolean mousePressed = false;

    public MouseMustNotify(KeyMouseObservable observable) {
        this.observable = observable;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (!mousePressed) observable.valueChanged();
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        observable.valueChanged();
        mousePressed = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (mousePressed) observable.valueChanged();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        if (mousePressed) observable.valueChanged();
    }
}
