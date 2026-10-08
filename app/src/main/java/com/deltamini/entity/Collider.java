package com.deltamini.entity;

import java.awt.Rectangle;

public class Collider extends Entity {
    public Collider(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.hitbox = new Rectangle(x, y, width, height);
    }
}
