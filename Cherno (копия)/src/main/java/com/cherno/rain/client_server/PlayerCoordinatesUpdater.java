package com.cherno.rain.client_server;


import com.cherno.rain.entity.mob.Player;
import com.cherno.rain.input.Keyboard;
import com.cherno.rain.input.Mouse;
import com.cherno.rain.level.Level;

public class PlayerCoordinatesUpdater {

    private Level level;

    public PlayerCoordinatesUpdater(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    public void updatePlayerCharacteristics(PlayerKeyMouse characteristics) {

        Player player = level.getPlayer(characteristics.getIdentifier());
//        System.out.println("updating" + characteristics);
        if (player == null) return;
        Keyboard keyboard = player.getInput();
        keyboard.up = characteristics.isUp();
        keyboard.down = characteristics.isDown();
        keyboard.left = characteristics.isLeft();
        keyboard.right = characteristics.isRight();
//        System.out.println("after updating " + keyboard);

        Mouse mouse = player.getMouse();
        mouse.setMouseX(characteristics.getX());
        mouse.setMouseY(characteristics.getY());
        mouse.setMouseB(characteristics.getB());
    }


    public void setPlayerCharacteristics(PlayerKeyMouse characteristics) {


        Player player = level.getPlayer(characteristics.getIdentifier());
        if (player == null) return;
        Keyboard keyboard = player.getInput();
        characteristics.setUp(keyboard.up);
        characteristics.setDown(keyboard.down);
        characteristics.setLeft(keyboard.left);
        characteristics.setRight(keyboard.right);

        Mouse mouse = player.getMouse();
        characteristics.setX(mouse.getX());
        characteristics.setY(mouse.getY());
        characteristics.setB(mouse.getB());

//        System.out.println("setted characteristics " + characteristics);
    }
}
