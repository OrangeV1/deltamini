package map;

import java.awt.Graphics2D;

import main.GamePanel;

/*
- loading maps
- changing maps (load new map and remove old map from memory)
- update tiles and map entities
- draw tiles and map entities
*/

public class LevelManager {
    private GamePanel panel;
    public Level curLevel;

    public LevelManager(GamePanel panel) {
        this.panel = panel;
    }

    public void loadLevel(String name, int width, int height) {
        this.curLevel = new Level(name, width, height, panel.tileSize);
    }

    public void loadLevel(Level level) {
        this.curLevel = level;
    }

    public void update() {

    }

    public void draw(Graphics2D g2d) {
        this.curLevel.draw(g2d);
    }
}