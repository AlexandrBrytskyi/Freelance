package com.cherno.rain.entity.mob;

import com.cherno.rain.Game;
import com.cherno.rain.entity.projectile.Projectile;
import com.cherno.rain.entity.projectile.WizardProjectile;
import com.cherno.rain.graphics.AnimatedSprite;
import com.cherno.rain.graphics.Screen;
import com.cherno.rain.graphics.Sprite;
import com.cherno.rain.graphics.SpriteSheet;
import com.cherno.rain.input.Keyboard;
import com.cherno.rain.input.Mouse;

public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private Mouse mouse;

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    private int fireRate = 0;

    public Player(Keyboard input, Mouse mouse) {
        this.input = input;
        sprite = Sprite.player_forward;
        this.mouse = mouse;
    } // end constructor

    public Player(int x, int y, Keyboard input, Mouse mouse) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.mouse = mouse;
        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;
    } // end constructor

    public void update() {
//        System.out.println("painting "+ "" + input);
        if (walking) animSprite.update();
        else
            animSprite.setFrame(0);
        if (fireRate > 0) fireRate--;
        double xa = 0.0, ya = 0.0;
        double speed = 1;
        if (anim < 7500) anim++;
        else
            anim = 0;
        if (input.up) {
            animSprite = up;
            ya -= speed;
        } else if (input.down) {
            animSprite = down;
            ya += speed;
        } else if (input.left) {
            animSprite = left;
            xa -= speed;
        } else if (input.right) {
            animSprite = right;
            xa += speed;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
        clear();
        updateShooting();
    } // end update() method

    private void clear() {
        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    private void updateShooting() {
//		System.out.println("mouse" + mouse);
        if (mouse.getB() == 1 && fireRate <= 0) {
//            System.out.println("shoot");
            double dx = mouse.getX() - Game.getWindowWidth() / 2;
            double dy = mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    public void render(Screen screen) {
        int flip = 0;
        sprite = animSprite.getSprite();
        screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);
    } // end render() method

    public Keyboard getInput() {
        return input;
    }

    public Mouse getMouse() {
        return mouse;
    }


}

// Previous animation(render) code
// if(dir == 0) {
// sprite = Sprite.player_forward;
// if(walking) {
// if(anim % 20 > 10) {
// sprite = Sprite.player_forward_1;
// } else {
// sprite = Sprite.player_forward_2;
// }
// } // end if walking
// }
// if(dir == 1 || dir == 3) {
// sprite = Sprite.player_side;
// if(walking) {
// if(anim % 20 > 10) {
// sprite = Sprite.player_side_1;
// } else {
// sprite = Sprite.player_side_2;
// }
// } // end if walking
// }
// if(dir == 2) {
// sprite = Sprite.player_back;
// if(walking) {
// if(anim % 20 > 10) {
// sprite = Sprite.player_back_1;
// } else {
// sprite = Sprite.player_back_2;
// }
// } // end if walking
// }
// if(dir == 3) {
// sprite = Sprite.player_side;
// if(anim % 20 > 10) {
// sprite = Sprite.player_side_1;
// } else {
// sprite = Sprite.player_side_2;
// }
// flip = 1;
// }