package Entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/front.png"));
            downLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/frontLeft.png"));
            downRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/frontRight.png"));
            left = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/left.png"));
            leftLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/leftLeft.png"));
            leftRight=ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/leftRight.png"));
            right = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/right.png"));
            rightLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/rightLeft.png"));
            rightRight= ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/rightRight.png"));
            up = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/back.png"));
            upRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/backRight.png"));
            upLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("C1PlayerSprite/backLeft.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if (!keyH.downPressed) {
            spriteNumber = 0;
        }
        if (keyH.downPressed || keyH.upPressed || keyH.rightPressed || keyH.leftPressed) {
            refreshCounter++;
            if(refreshCounter>24){
                refreshSpriteNumber1();
                refreshCounter=0;
            } else if (refreshCounter >12){
                refreshSpriteNumber2();
            }
            if (keyH.upPressed) {
                y -= speed;
                direction = "up";
            } else if (keyH.leftPressed) {
                x -= speed;
                direction = "left";
            } else if (keyH.downPressed) {
                y += speed;
                direction = "down";
            } else if (keyH.rightPressed) {
                x += speed;
                direction = "right";
            }

            // walking animations, refreshes the type of walk image every 15 frames, or 5 times per second

        }
    }

        public void draw (Graphics2D g2){

            BufferedImage image = null;
            switch (direction) {
                case "down":
                    if (spriteNumber == 0) {
                        image = down;
                        break;
                    }
                    if (spriteNumber == 1) {
                        image = downLeft;
                        break;
                    }
                    if (spriteNumber == 2) {
                        image = downRight;
                        break;
                    }
                case "left":
                    if (spriteNumber == 0) {
                        image = leftLeft;
                        break;
                    }
                    if (spriteNumber == 1) {
                        image = leftLeft;
                        break;
                    }
                    if (spriteNumber == 2) {
                        image = leftRight;
                        break;
                    }
                case "right":
                    if (spriteNumber == 0) {
                        image = rightRight;
                        break;
                    }
                    if (spriteNumber == 1) {
                        image = rightRight;
                        break;
                    }
                    if (spriteNumber == 2) {
                        image = rightLeft;
                        break;
                    }
                case "up":
                        image = up;
                        break;
            }

            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
