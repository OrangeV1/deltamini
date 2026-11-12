package com.deltamini.entity;

import java.awt.Rectangle;
import java.awt.Graphics2D;

import com.deltamini.main.GamePanel;
import com.google.gson.internal.LinkedTreeMap;

public class Collider extends Entity {
    public Collider(int x, int y, int width, int height, GamePanel panel) {
        super(panel);
        this.x = x;
        this.y = y;
        this.hitbox = new Rectangle(x, y, width, height);
        this.panel = panel;
    }

    public Collider(LinkedTreeMap<?, ?> params, GamePanel panel) {
        super(params, panel);
        this.x = offsetScreenX(((Number) params.get("x")).intValue());
        this.y = offsetScreenY(((Number) params.get("y")).intValue());
        this.hitbox = new Rectangle(this.x, this.y, ((Number) params.get("width")).intValue(), ((Number) params.get("height")).intValue());
        this.panel = panel;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(new Sprite2D("assets/player/spritesheet.png", new int[] {0, 16, 16, 16}).getSprite(), this.x, this.y, 80, 80, null);
    }
}
