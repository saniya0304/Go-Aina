package main;

import entity.Entity;

public class CollisionChecker {

	private GamePanel gp;

	public CollisionChecker(GamePanel gamePanel) {
		this.gp = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
		int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
		int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
		int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;
		
		int entityLeftCol = entityLeftWorldX/gp.getTileSize();
		int entityRightCol = entityRightWorldX/gp.getTileSize();
		int entityTopRow = entityTopWorldY/gp.getTileSize();
		int entityBottomRow = entityBottomWorldY/gp.getTileSize();
		
		int tileNum1, tileNum2;
		
		switch(entity.getDirection()) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.getTileSize();
			tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
			tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
			if(gp.getTileM().getTile()[tileNum1].getCollision() == true || gp.getTileM().getTile()[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.getTileSize();
			tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
			tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
			if(gp.getTileM().getTile()[tileNum1].getCollision() == true || gp.getTileM().getTile()[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.getTileSize();
			tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
			tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
			if(gp.getTileM().getTile()[tileNum1].getCollision() == true || gp.getTileM().getTile()[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.getTileSize();
			tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
			tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
			if(gp.getTileM().getTile()[tileNum1].getCollision() == true || gp.getTileM().getTile()[tileNum2].getCollision()) {
				entity.setCollisionOn(true);
				
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.getObj().length; i++) {
			
			if(gp.getObj()[i] != null) {
				
				//Get entity's solid area position
				entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
				entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
				
				//get the object's solid area position
				gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getWorldX() + gp.getObj()[i].getSolidArea().x;
				gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getWorldY() + gp.getObj()[i].getSolidArea().y;
				
				switch(entity.getDirection()) {
				case "up":
					entity.getSolidArea().y -= entity.getSpeed();
					if(entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
						if(gp.getObj()[i].isCollision() == true) {
							entity.setCollisionOn(true);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.getSolidArea().x += entity.getSpeed();
					if(entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
						if(gp.getObj()[i].isCollision() == true) {
							entity.setCollisionOn(true);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.getSolidArea().y -= entity.getSpeed();
					if(entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
						if(gp.getObj()[i].isCollision() == true) {
							entity.setCollisionOn(true);
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.getSolidArea().x += entity.getSpeed();
					if(entity.getSolidArea().intersects(gp.getObj()[i].getSolidArea())) {
						if(gp.getObj()[i].isCollision() == true) {
							entity.setCollisionOn(true);
						}
						if(player == true) {
							index = i;
						}
					
					break;
			      }
				}
				entity.getSolidArea().x = entity.getSolidAreaDefaultX();
				entity.getSolidArea().y = entity.getSolidAreaDefaultY();
				gp.getObj()[i].getSolidArea().x = gp.getObj()[i].getSolidAreaDefaultX();
				gp.getObj()[i].getSolidArea().y = gp.getObj()[i].getSolidAreaDefaultY();
			}
		}
		return index;
	}
}
