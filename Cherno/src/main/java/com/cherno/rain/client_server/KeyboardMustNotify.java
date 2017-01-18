package com.cherno.rain.client_server;


import com.cherno.rain.input.Keyboard;

import java.awt.event.KeyEvent;

public class KeyboardMustNotify extends Keyboard {

    private KeyMouseObservable observable;
    private boolean isPressed = false;

    public KeyboardMustNotify(KeyMouseObservable observable) {
        super();
        this.observable = observable;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (!isPressed) observable.valueChanged();
        isPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        isPressed = false;
        observable.valueChanged();
    }
}
