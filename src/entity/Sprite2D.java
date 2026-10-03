package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite2D {
    private BufferedImage[] sprites;
    private String spritesheet;
    private int curSprite = 0;
    private int animDelay;
    private int spriteTime = 0;

    public Sprite2D(String sheet, int[]... sprites) {
        this.spritesheet = sheet;
        this.sprites = new BufferedImage[sprites.length];
        try {
            BufferedImage spsheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(spritesheet));
            for (int i = 0;i < sprites.length;i++) {
                this.sprites[i] = spsheet.getSubimage(sprites[i][0], sprites[i][1], sprites[i][2], sprites[i][3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite2D(Sprite2D sprite2D) {
        this.spritesheet = sprite2D.spritesheet;
        this.sprites = sprite2D.sprites;
        this.curSprite = sprite2D.curSprite;
        this.animDelay = sprite2D.animDelay;
        this.spriteTime = sprite2D.spriteTime;
    }

    public void startAnim(int animDelay) {
        this.animDelay = animDelay;
    }

    public void update() {
        this.spriteTime++;
        if (this.spriteTime > this.animDelay) {
            if (this.curSprite < this.sprites.length - 1) {
                this.curSprite++;
            } else {
                this.curSprite = 0;
            }
            this.spriteTime = 0;
        }
    }

    public BufferedImage[] getSprites() {
        return this.sprites;
    }

    public BufferedImage getSprite() {
        return this.sprites[curSprite];
    }
}
