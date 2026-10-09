package com.deltamini.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    // Declare entity properties
    public int x;
    public int y;
    public double speed;
    public Vector2 velocity;
    public Sprite2D sprite;
    public String direction;
    public Rectangle hitbox;

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
