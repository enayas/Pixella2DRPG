package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int originalTileSize = 16; // means 16x16 size
    final int scale = 3; // to make the original tile size bigger for modern screens
    public final int maxScreenCols = 16;
    public final int maxScreenRows = 12;

    public final int tileSize = originalTileSize * scale; // official tile size used in the game
    final int screenWidth = tileSize * maxScreenCols; // 786 pixels
    final int screenHeight = tileSize * maxScreenRows; // 576 pixels

    //FPS - Frame Per Second (how many refreshes/repaints in a second)
    int FPS = 60;
    TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.setBackground(Color.black);
        this.addKeyListener(keyH); // GamePanel is now able to listen to the key inputs
        this.setFocusable(true); // GamePanel is now "focused" to receive key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; // sets the drawInterval to refresh 60 times per second, or 1000000000 nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta > 1) {

                // 1: UPDATE: Update info such as character positions
                update();
                // 2: DRAW: Draw the screen with updated information
                repaint();
                delta--;

            }
        }
    }


    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) { // g or the new type-casted g2 is basically like a pencil
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // casting Graphics g to Graphics2D g2 because of some extra functions
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose(); // saves memory by disposing the pencil after you're done. It's like you have a one-time use pencil
    }
}