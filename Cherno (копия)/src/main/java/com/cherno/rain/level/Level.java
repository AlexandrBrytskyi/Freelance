package com.cherno.rain.level;

import java.util.*;

import com.cherno.rain.entity.Entity;
import com.cherno.rain.entity.mob.Player;
import com.cherno.rain.entity.particle.Particle;
import com.cherno.rain.entity.projectile.Projectile;
import com.cherno.rain.graphics.Screen;
import com.cherno.rain.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;

    private List<Entity> entities = new ArrayList<Entity>();
    private List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();
    private Map<Integer, Player> players = new HashMap<>();
    private int playersCounter;

    public static Level spawn = new SpawnLevel("/levels/spawn.png");

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    } // end constructor

    public Level(String path) {
        loadLevel(path);
        generateLevel();
    } // end constructor

    public List<Projectile> getProjectiles() {
        return projectiles;
    }

    public Player getPlayer(Integer identifier) {
        return players.get(identifier);
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public List<Entity> getEntities(Entity e, int radius) {
        List<Entity> result = new ArrayList<Entity>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            int x = (int) entity.getX();
            int y = (int) entity.getY();

            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) result.add(entity);
        }
        return result;
    }

    public List<Player> getPlayers(Entity e, int radius) {
        List<Player> result = new ArrayList<Player>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        for (Player player : players.values()) {
            int x = (int) player.getX();
            int y = (int) player.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) result.add(player);
        }
        return result;
    }


    protected void generateLevel() {
    } // end generateLevel() method

    protected void loadLevel(String path) {

    } // end loadLevel() method

    // For AI elements or other game elements to update or move etc
    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }

        for (Player player : players.values()) {
            player.update();
        }

        remove();
    } // end update() method

    private void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) particles.remove(i);
        }

//        for (int i = 0; i < players.size(); i++) {
//            if (players.get(i).isRemoved()) players.remove(i);
//        }

    }

    private void time() {

    } // end time() method

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;
            if (getTile(xt, yt).solid()) solid = true;
        }
        return solid;
    } // end collision() method

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }

        for (Player player : players.values()) {
            player.render(screen);
        }
    } // end render() method

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.put(playersCounter++, (Player) e);
        } else {
            entities.add(e);
        }
    }

    public void add(Entity e, int identifier) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.put(identifier, (Player) e);
        } else {
            entities.add(e);
        }
    }

    // Grass = 0XFF00FF00
    // Flower = 0XFFFFFF00
    // Rock = 0XFF7f7f00
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
        if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        if (tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
        if (tiles[x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
        if (tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
        if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
        return Tile.voidTile;
    } // end getTile() method



}

// Bad way to make tiles was part of render method just below getTile() method
// call
// if((x + y * 16) < 0 || (x + y * 16) >= 256) { // 16 * 16
// Tile.voidTile.render(x, y, screen);
// continue;
// }
// tiles[x + y * 16].render(x, y, screen);
// }
