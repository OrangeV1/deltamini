package com.deltamini.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.deltamini.main.GamePanel;
import com.google.gson.internal.LinkedTreeMap;

public class Entity {
    // Declare entity properties
    protected GamePanel panel;
    public int x;
    public int y;
    public double speed = 0.0;
    public Vector2 velocity = new Vector2(0, 0);
    public Sprite2D sprite;
    public String direction;
    public Rectangle hitbox;

    public Entity(GamePanel panel) {
        this.x = 0;
        this.y = 0;
        this.panel = panel;
    }

    public Entity(LinkedTreeMap<?, ?> params, GamePanel panel) {
        this.x = params.get("x") != null ? ((Number) params.get("x")).intValue() : 0;
        this.y = params.get("y") != null ? ((Number) params.get("y")).intValue() : 0;
        this.panel = panel;
    }

    public int offsetScreenX(int x) {
        return x + (this.panel.maxScreenWidth / 2) - ((this.panel.maxScreenColumns * this.panel.tileSize) / 2);
    }

    public int offsetScreenY(int y) {
        return y + (this.panel.maxScreenHeight / 2) - ((this.panel.maxScreenRows * this.panel.tileSize) / 2);
    }

    public boolean checkCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }

    public void update() {
        // @Override in child classes
    }

    public void draw(Graphics2D g2d) {
        // @Override in child classes
    }
}
