package tile;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import main.GamePanel;


public class TileManager {

	GamePanel gp;
	public Tile<BufferedImage>[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		 
		try {
			
			tile[0] = new Tile<BufferedImage>();
			tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png")));
			
			tile[1] = new Tile<BufferedImage>();
			tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
			tile[1].setCollision(true);
			
			tile[2] = new Tile<BufferedImage>();
			tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png")));
			tile[2].setCollision(true);
			
			tile[3] = new Tile<BufferedImage>();
			tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")));
			
			tile[4] = new Tile<BufferedImage>();
			tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")));
			tile[4].setCollision(true);
			
			tile[5] = new Tile<BufferedImage>();
			tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")));
			
		}catch(IOException e) {
			System.err.println("Error loading tile images: " + e.getMessage());
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow  < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow ];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
			int screenY = worldY - gp.player.worldY + gp.player.getScreenY();
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.getScreenX() &&
			   worldX - gp.tileSize < gp.player.worldX + gp.player.getScreenX() &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.getScreenY() &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.getScreenY()) {
			
			g2.drawImage(tile[tileNum].getImage(), screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
		
			worldCol++;
			
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow ++;
				
			}
		}
	}
}
		
	

