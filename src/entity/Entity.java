package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Entity {
      
	public int worldX, worldY;
	public int speed;
	
	private int screenX = 380;
	private int screenY = 280;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	abstract public void pickUpObject(int i);
	abstract public void update();
	abstract public void draw(Graphics2D g2);
    abstract public int getHasKey();
    abstract public void setHasKey(int hasKey);
    
    public int getScreenX() {
		return screenX;
	}

	
	public int getScreenY() {
		return screenY;
	}
		
	
}






