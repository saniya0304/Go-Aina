package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

	private BufferedImage image;
	private String name;
	private boolean collision = false;
	private int worldX, worldY;
	private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	private int solidAreaDefaultX = 0;
	private int solidAreaDefaultY = 0;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = getWorldX() - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
		int screenY = getWorldY() - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();                     //inclusion = getScreenX and getScreenY gets called from player class despite being in entity too because player class overrides them
		
		if(getWorldX() + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() &&
		   getWorldX() - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX() &&
		   getWorldY() + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() &&
		   getWorldY() - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()) {
		
		g2.drawImage(getImage(), screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
	  }
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWorldX() {
		return worldX;
	}


	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}


	public int getWorldY() {
		return worldY;
	}


	public void setWorldY(int worldY) {
		this.worldY = worldY;
	}


	public Rectangle getSolidArea() {
		return solidArea;
	}


	public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}


	public boolean isCollision() {
		return collision;
	}


	public void setCollision(boolean collision) {
		this.collision = collision;
	}


	public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}


	public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}


	public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}


	public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
