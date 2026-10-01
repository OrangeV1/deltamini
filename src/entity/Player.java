package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    private GamePanel panel;
    private KeyHandler keyH;

    private Sprite2D upSprites;
    private Sprite2D downSprites;
    private Sprite2D leftSprites;
    private Sprite2D rightSprites;

    public Player(GamePanel panel, KeyHandler keyH) {
        // Declare default values and initialize player
        this.panel = panel;
        this.keyH = keyH;
        this.hitbox = new Rectangle(this.x + panel.tileSize, this.y + 8 * panel.tileSize, 14 * panel.tileSize, 8 * panel.tileSize);
        this.setDefaultValues();
        this.getPlayerImage();
    }

    public void setDefaultValues() {
        this.x = this.panel.screenWidth / 2;
        this.y = this.panel.screenHeight / 2;
        this.speed = 10;
        this.velocity = new Vector2(0, 0);
        this.direction = "down";
    }

    // Load player sprites
    public void getPlayerImage() {
        this.upSprites = new Sprite2D("player/spritesheet.png", new int[] {102, 16, 16, 16}, new int[] {119, 16, 16, 16});
        this.upSprites.startAnim(7);
        this.downSprites = new Sprite2D("player/spritesheet.png", new int[] {0, 16, 16, 16}, new int[] {17, 16, 16, 16});
        this.downSprites.startAnim(7);
        this.leftSprites = new Sprite2D("player/spritesheet.png", new int[] {34, 16, 16, 16}, new int[] {51, 16, 16, 16});
        this.leftSprites.startAnim(7);
        this.rightSprites = new Sprite2D("player/spritesheet.png", new int[] {68, 16, 16, 16}, new int[] {85, 16, 16, 16});
        this.rightSprites.startAnim(7);
    }

    public void update() {
        // Check for key inputs and set direction if true
        if (this.keyH.upPressed) {
            this.direction = "up";
            this.velocity.y -= 1;
        }

        if (this.keyH.downPressed) {
            this.direction = "down";
            this.velocity.y += 1;
        }

        if (this.keyH.leftPressed) {
            this.direction = "left";
            this.velocity.x -= 1;
        }

        if (this.keyH.rightPressed) {
            this.direction = "right";
            this.velocity.x += 1;
        }

        // Move if no collisions
        //this.collided = false;
        //this.panel.collisionH.checkTile(this);
        //if (this.CheckCollision(Rectangle())) {
        this.y += velocity.y * this.speed;
        this.x += velocity.x * this.speed;
        this.velocity.x = 0;
        this.velocity.y = 0;
        //}

        // Update walking sprite when applicable
        if ((this.keyH.upPressed || this.keyH.downPressed || this.keyH.leftPressed || this.keyH.rightPressed)) {
            this.upSprites.update();
            this.downSprites.update();
            this.leftSprites.update();
            this.rightSprites.update();
        }

    }

    // Draw player based on spriteNum
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                image = this.upSprites.getSprite();
                break;
            case "left":
                image = this.leftSprites.getSprite();
                break;
            case "down":
                image = this.downSprites.getSprite();
                break;
            case "right":
                image = this.rightSprites.getSprite();
        }
        g2.drawImage(image, this.x, this.y, this.panel.tileSize, this.panel.tileSize, (ImageObserver)null);
    }
}
