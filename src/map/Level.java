package map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import entity.Entity;
import entity.Sprite2D;
import main.GamePanel;

public class Level {
    private final GamePanel panel;
    public Entity[] entities;
    public String name;
    public Sprite2D[][] tiles;
    public int width;
    public int height;
    public int tileSize;
    private Sprite2D[] tileKeys = new Sprite2D[] {
        new Sprite2D("tiles/32xtileset.png", new int[] {16, 0, 16, 16})
    };

    public Level(GamePanel panel, String name, int width, int height, int tileSize) {
        this.panel = panel;
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = new Sprite2D[height][width];
        this.tileSize = tileSize;

        readLevel();
    }

    public void readLevel() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/" + name + "/tiles.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0;i < height;i++) {
                String line = br.readLine();
                String[] lineTiles = line.split(";");

                for (int j = 0; j < width;j++) {
                    int tile = Integer.parseInt(lineTiles[j]);

                    tiles[i][j] = new Sprite2D(this.tileKeys[tile]);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2d) {
        for (int i = 0;i < height;i++) {
            for (int j = 0;j < width;j++) {
                try {
                    g2d.drawImage(
                        tiles[i][j].getSprites()[0], 
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

    public void draw(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        for (int i = y1;i < y2;i++) {
            for (int j = x1;j < x2;j++) {
                try {
                    g2d.drawImage(
                        tiles[i][j].getSprites()[0], 
                        j * this.tileSize, 
                        i * this.tileSize, 
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

