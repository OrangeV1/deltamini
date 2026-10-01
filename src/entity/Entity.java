package entity;

import java.awt.Rectangle;

public class Entity {
    // Declare entity properties
    public int x;
    public int y;
    public int speed;
    public Vector2 velocity;
    public Sprite2D sprite;
    public String direction;
    public Rectangle hitbox;

    public boolean CheckCollision(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }
}
