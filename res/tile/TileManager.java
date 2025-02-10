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

	private GamePanel gp;
	private Tile<BufferedImage>[] tile;
	private int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		setTile(new Tile[10]);
		setMapTileNum(new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()]);
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		 
		try {
			
			getTile()[0] = new Tile<BufferedImage>();
			getTile()[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png")));
			
			getTile()[1] = new Tile<BufferedImage>();
			getTile()[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
			getTile()[1].setCollision(true);
			
			getTile()[2] = new Tile<BufferedImage>();
			getTile()[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/water01.png")));
			getTile()[2].setCollision(true);
			
			getTile()[3] = new Tile<BufferedImage>();
			getTile()[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png")));
			
			getTile()[4] = new Tile<BufferedImage>();
			getTile()[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")));
			getTile()[4].setCollision(true);
			
			getTile()[5] = new Tile<BufferedImage>();
			getTile()[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png")));
			
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
			
			while(col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
				
				String line = br.readLine();
				
				while(col < gp.getMaxWorldCol()) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					getMapTileNum()[col][row] = num;
					col++;
				}
				if(col == gp.getMaxWorldCol()) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			System.err.println("Error getting image: " + e.getMessage());
		}
	}
	public void draw(Graphics2D g2) {
		
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.getMaxWorldCol() && worldRow  < gp.getMaxWorldRow()) {
			
			int tileNum = getMapTileNum()[worldCol][worldRow ];
			
			int worldX = worldCol * gp.getTileSize();
			int worldY = worldRow * gp.getTileSize();
			int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
			int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();
			
			if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
			   worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
			   worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
			   worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
			
			g2.drawImage(getTile()[tileNum].getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
		}
		
			worldCol++;
			
			
			if(worldCol == gp.getMaxWorldCol()) {
				worldCol = 0;
				worldRow ++;
				
			}
		}
	}

	public int[][] getMapTileNum() {
		return mapTileNum;
	}

	public void setMapTileNum(int mapTileNum[][]) {
		this.mapTileNum = mapTileNum;
	}

	public Tile<BufferedImage>[] getTile() {
		return tile;
	}

	public void setTile(Tile<BufferedImage>[] tile) {
		this.tile = tile;
	}
}
		
	

