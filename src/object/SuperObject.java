package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.getScreenX();
		int screenY = worldY - gp.player.worldY + gp.player.getScreenY();                     //inclusion = getScreenX and getScreenY gets called from player class despite being in entity too because player class overrides them
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.getScreenX() &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.getScreenX() &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.getScreenY() &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.getScreenY()) {
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	  }
	}
}
