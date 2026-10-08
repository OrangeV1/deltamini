package com.deltamini.main;
import com.deltamini.entity.Player;
import com.deltamini.map.LevelManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // 720p = 80x, 1080p = 120x, 1440p = 160x, 2160p = 240x
    public final int maxScreenWidth = 16 * 80;
    public final int maxScreenHeight = 9 * 80;
    public final int maxScreenColumns = 12;
    public final int maxScreenRows = 8;

    final int originalTileSize = 16;
    public final int scale = maxScreenHeight / (originalTileSize * maxScreenRows);
    public final int tileSize = (originalTileSize * scale);

    int FPS = 30;

    // Create objects
    public LevelManager levelM = new LevelManager(this);
    KeyHandler keyH = new KeyHandler();
    private Thread gameThread;
    private Player player1 = new Player(this, keyH);

    public Player getPlayer1() {return player1;}

    public GamePanel() {
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

 // Start game thread loop
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Create FPS cap with delta time
    @Override
    public void run() {

        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        this.levelM.loadLevel("demo1", 12, 8);

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta -= 1;
            }
            if (timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    // Updates objects before drawing to the screen
    public void update() {
        levelM.update();
        getPlayer1().update();
    }

    // Draws objects to the screen
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
            levelM.draw(g2d);
            getPlayer1().draw(g2d);
        }
        catch (Exception ignored) {}
        g2d.dispose();
    }

}
