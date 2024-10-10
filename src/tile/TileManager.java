package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile [] tile;
    int [] [] mapTileNum ;

    public TileManager(GamePanel gp){
        this.gp=gp;
        tile=new Tile[10];
        mapTileNum=new int[gp.maxScreenRows][gp.maxScreenCols];

        getTileImage();
        loadMap();

    }
    public void getTileImage(){
        try{
            tile[0]=new Tile();
            tile[0].image= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/pinkGrass.png")));

            tile[1]=new Tile();
            tile[1].image= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/orangeWood.png")));

            tile[2]=new Tile();
            tile[2].image=ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/shadedOrangeWood.png")));

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void loadMap(){
        try{

            InputStream is = getClass().getClassLoader().getResourceAsStream("maps/map1.txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int i=0; i<gp.maxScreenRows; i++){
                String line = br.readLine();
                System.out.println(line);
                String [] numbers = line.split(" ");
                for(int j=0; j<gp.maxScreenCols; j++){
                    int num = Integer.parseInt(numbers[j]);
                    mapTileNum[i][j]=num;
                }
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    public void draw (Graphics2D g2){
        int col =0;
        int row=0;
        int x=0;
        int y=0;

        while(col<gp.maxScreenCols && row<gp.maxScreenRows){
            int tileNum = mapTileNum[row][col];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+=gp.tileSize;

            if(col==gp.maxScreenCols){
                col=0;
                x=0;
                row++;
                y+=gp.tileSize;
            }
        }

    }
}
