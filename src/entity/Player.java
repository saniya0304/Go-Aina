package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	private GamePanel gp;
	private KeyHandler keyH;
    private int hasKey = 0;
	
	
	
	public Player(GamePanel gamePanel, KeyListener keyH) {     //Constructor of player class
		 
		this.gp = gamePanel;
		this.keyH = (KeyHandler) keyH;
		
		
		
		setSolidArea(new Rectangle());
		getSolidArea().x = 8;
		getSolidArea().y = 16;
		setSolidAreaDefaultX(getSolidArea().x);
		setSolidAreaDefaultY(getSolidArea().y);
		getSolidArea().width = 32;
		getSolidArea().height = 32;
		
		setDefaultValues();
		getPlayerImage();
		
	}
    
	public void setDefaultValues() {
    	 
    	setWorldX(gp.getTileSize() * 23);
    	setWorldY(gp.getTileSize() * 21);
    	setSpeed(4);                         // 4 PIXELS
    	setDirection("down");
    }
	
	public void getPlayerImage() {
		try {
			
			setUp1(ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png")));
			setUp2(ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png")));
			setDown1(ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png")));
			setDown2(ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png")));
			setLeft1(ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png")));
			setLeft2(ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png")));
			setRight1(ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png")));
			setRight2(ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png")));
		
		}catch(IOException e) {
			System.err.println("Error loading images: " + e.getMessage());
		}
	}
	
	@Override
    public void update() {
    	
     if(keyH.isUpPressed() == true || keyH.isDownPressed() == true || 
    		 keyH.isLeftPressed() == true || keyH.isRightPressed() == true ) {
    
    	 
   	 if(keyH.isUpPressed() == true) {
   		 setDirection("up");
   	 }
   	 else if(keyH.isDownPressed() == true) {
   		setDirection("down");
   	 }
   	 else if(keyH.isLeftPressed() == true) {
   		setDirection("left");
   	 }
   	 else if(keyH.isRightPressed() == true) {
   		setDirection("right");
   	 }
   	 
   	 //CHECK TILE COLLISION
   	 setCollisionOn(false);
   	 gp.getcChecker().checkTile(this);
   	 
   	 // CHECK OBJECT COLLISION
   	 int objIndex = gp.getcChecker().checkObject(this, true);
   	 pickUpObject(objIndex);
   	 
   	 //IF COLLISION IS FALSE, PLAYER CAN MOVE
   	 if(isCollisionOn() == false) {
   		 
   		 switch(getDirection()) {
   		 case "up":
   			 
   			setWorldY(getWorldY() - getSpeed());
   			 
   			 break;
   		 case "down":
   			
   			 setWorldY(getWorldY() + getSpeed());
   			
  			 break;
   	   	 case "left":
   	   
   	   	 setWorldX(getWorldX() - getSpeed());
   	   	
  			 break;
   		 case "right":
   			
   			 setWorldX(getWorldX() + getSpeed());
   			
  			 break;
   			
   		 }
   	 }
   	 
   	 setSpriteCounter(getSpriteCounter() + 1);
   	 if(getSpriteCounter() > 12) {
   		 if(getSpriteNum() == 1) {
   			 setSpriteNum(2);
   		 }
   		 else if(getSpriteNum() == 2) {
   			 setSpriteNum(1);
   		 }
   		 setSpriteCounter(0);
   	   }
   	  }
   }
	
	@Override
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    	  
    	   String objectName = gp.getObj()[i].getName();
    	   
    	   switch(objectName) {
    	   case "Key":
    		   gp.playSE(1);
    		  setHasKey(getHasKey() + 1);
    		  gp.getObj()[i] = null;
    		  gp.getUi().showMessage("You have got lives!");
    		  break;
    	   case "Door":
    		   if(getHasKey() > 0) {
    			   gp.playSE(3);
    			   gp.getObj()[i] = null;
    			   setHasKey(getHasKey() - 1);
    			   gp.getUi().showMessage("You have defeated the enemy!");
    		   }
    		   else {
    			   gp.getUi().showMessage("You need lives!");
    		   }
    		   System.out.println("lives:" + getHasKey());
    		   break;
    	   case "Boots":
    		   gp.playSE(2);
    		  setSpeed(getSpeed() + 1);
    		  gp.getObj()[i] = null;
    		  gp.getUi().showMessage("Speed up!");
    		  break;
    		  
    	   case "Chest":
    		   gp.getUi().setGameFinished(true);
    		   gp.stopMusic();
    		   gp.playSE(4);
    		   break;
    	   }
    	}
    }
    
    public void draw(Graphics2D g2) {
    	
//    	g2.setColor(Color.white);
//   	 
//   	    g2.fillRect(x,  y,  gp.tileSize,  gp.tileSize);
   	 
    	BufferedImage image = null;
    	
    	switch(getDirection()) {
    	case "up":
    		if(getSpriteNum() == 1) {
    		image = getUp1();
    		}
    		if(getSpriteNum() == 2) {
        		image = getUp2();
        	}
    		break;
    	case "down":
    		if(getSpriteNum() == 1) {
    		image = getDown1();
    		}
    		if(getSpriteNum() == 2) {
    			image = getDown2();
    		}
    		break;
    	case "left":
    		if(getSpriteNum() == 1) {
    		image = getLeft1();
    		}
    		if(getSpriteNum() == 2) {
    			image = getLeft2();
    		}
    		break;
    	case "right":
    		if(getSpriteNum() == 1) {
    		image = getRight1();
    		}
    		if(getSpriteNum() == 2) {
    			image = getRight2();
    		}
    		break;
    	}
    	
    	g2.drawImage(image, getScreenX(), getScreenY(), gp.getTileSize(), gp.getTileSize(), null);
    	
    	
    }

    @Override
	public int getHasKey() {
		return hasKey;
	}

    @Override
	public void setHasKey(int hasKey) {
		this.hasKey = hasKey;
	}

    @Override
	public int getScreenX() {
    	return gp.getScreenWidth()/2 - (gp.getTileSize()/2); //360 pixels
		
	}                                        

    @Override
	public int getScreenY() {
    	return gp.getScreenHeight()/2 - (gp.getTileSize()/2); //264 pixels
		
	}
    
 
}


