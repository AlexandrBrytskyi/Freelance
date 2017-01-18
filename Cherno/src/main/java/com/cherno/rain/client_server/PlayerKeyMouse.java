package com.cherno.rain.client_server;


import java.io.*;

public class PlayerKeyMouse implements Serializable {

    private Boolean up, down, left, right;
    private Integer x, y, b;
    private Integer identifier;
    private Double playerX;
    private Double playerY;


    public PlayerKeyMouse(boolean up, boolean down, boolean left, boolean right, int x, int y, int b, int identifier) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.x = x;
        this.y = y;
        this.b = b;
        this.identifier = identifier;
    }

    public Boolean isUp() {
        return up;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public Boolean isDown() {
        return down;
    }

    public void setDown(Boolean down) {
        this.down = down;
    }

    public Boolean isLeft() {
        return left;
    }

    public void setLeft(Boolean left) {
        this.left = left;
    }

    public Boolean isRight() {
        return right;
    }

    public void setRight(Boolean right) {
        this.right = right;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Double getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Double playerX) {
        this.playerX = playerX;
    }

    public Double getPlayerY() {
        return playerY;
    }

    public void setPlayerY(Double playerY) {
        this.playerY = playerY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerKeyMouse that = (PlayerKeyMouse) o;

        if (up != that.up) return false;
        if (down != that.down) return false;
        if (left != that.left) return false;
        if (right != that.right) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;
        if (b != that.b) return false;
        return identifier == that.identifier;

    }

    @Override
    public int hashCode() {
        int result = (up ? 1 : 0);
        result = 31 * result + (down ? 1 : 0);
        result = 31 * result + (left ? 1 : 0);
        result = 31 * result + (right ? 1 : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + b;
        result = 31 * result + identifier;
        return result;
    }

    @Override
    public String toString() {
        return "PlayerKeyMouse{" +
                "up=" + up +
                ", down=" + down +
                ", left=" + left +
                ", right=" + right +
                ", x=" + x +
                ", y=" + y +
                ", b=" + b +
                ", identifier=" + identifier +
                '}';
    }
}
