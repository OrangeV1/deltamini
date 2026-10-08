package com.deltamini.map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.deltamini.entity.Sprite2D;
import com.deltamini.main.GamePanel;

public class Level {
    private final GamePanel panel;
    public String name;
    public Sprite2D[][] tiles;
    public int width;
    public int height;
    public int tileSize;
    private Map<Integer, Sprite2D> tileKeys = new HashMap<>();

    public Level(GamePanel panel, String name, int width, int height, int tileSize) {
        this.panel = panel;
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = new Sprite2D[height][width];
        this.tileSize = tileSize;

        tileKeys.put(0, null);
        tileKeys.put(3, new Sprite2D("assets/tiles/32xtileset.png", new int[] {256, 64, 16, 16}));
        tileKeys.put(4, new Sprite2D("assets/tiles/32xtileset.png", new int[] {272, 64, 16, 16}));
        tileKeys.put(5, new Sprite2D("assets/tiles/32xtileset.png", new int[] {288, 64, 16, 16}));
        tileKeys.put(9, new Sprite2D("assets/tiles/32xtileset.png", new int[] {64, 128, 16, 16}, new int[] {80, 128, 16, 16}, new int[] {96, 128, 16, 16}, new int[] {112, 128, 16, 16}));
        tileKeys.get(9).startAnim(7);
        tileKeys.put(10, new Sprite2D("assets/tiles/32xtileset.png", new int[] {80, 192, 16, 16}, new int[] {96, 192, 16, 16}, new int[] {112, 192, 16, 16}, new int[] {128, 192, 16, 16}));
        tileKeys.get(10).startAnim(7);
        tileKeys.put(11, new Sprite2D("assets/tiles/32xtileset.png", new int[] {16, 0, 16, 16}));

        readLevel();
    }

    public void readLevel() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("assets/maps/" + name + "/tiles.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0;i < height;i++) {
                String line = br.readLine();
                String[] lineTiles = line.split(";");

                for (int j = 0; j < width;j++) {
                    int tile = Integer.parseInt(lineTiles[j]);

                    tiles[i][j] = (this.tileKeys.get(tile) != null) ? this.tileKeys.get(tile) : null;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        for (Sprite2D tile : tileKeys.values()) {
            if (tile != null && tile.getSprites().length > 1) {tile.update();}
        }
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0;i < height;i++) {
            for (int j = 0;j < width;j++) {
                if (tiles[i][j] != null) {
                    try {
                        g2d.drawImage(
                            tiles[i][j].getSprite(),
                            j * this.tileSize + (this.panel.maxScreenWidth / 2) - ((this.panel.maxScreenColumns * this.tileSize) / 2),
                            i * this.tileSize + (this.panel.maxScreenHeight / 2) - ((this.panel.maxScreenRows * this.tileSize) / 2),
                            this.tileSize, 
                            this.tileSize, 
                            null
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        for (int i = y1;i < y2;i++) {
            for (int j = x1;j < x2;j++) {
                if (tiles[i][j] != null) {
                    try {
                        g2d.drawImage(
                            tiles[i][j].getSprite(),
                            j * this.tileSize + (this.panel.maxScreenWidth / 2) - ((this.panel.maxScreenColumns * this.tileSize) / 2),
                            i * this.tileSize + (this.panel.maxScreenHeight / 2) - ((this.panel.maxScreenRows * this.tileSize) / 2),
                            this.tileSize, 
                            this.tileSize, 
                            null
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
}

