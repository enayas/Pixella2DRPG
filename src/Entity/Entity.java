package Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed ;

    public BufferedImage down, downLeft, downRight, left, leftLeft, leftRight, right, rightLeft, rightRight, up, upRight, upLeft;
    public String direction;

    public int spriteCounter =0;
    public int spriteNumber = 0;
    public int refreshCounter=0;
    public void refreshSpriteNumber1(){
        spriteNumber =1;
    }
    public void refreshSpriteNumber2(){
        spriteNumber =2;
    }

}
