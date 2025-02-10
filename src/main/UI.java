package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

public class UI {
	
	private GamePanel gp;
	private Font arial_40, arial_80B;
	private BufferedImage keyImage;
	private boolean messageOn = false;
	private String message = "";
	private int messageCounter = 0;
	private boolean gameFinished = false;
	
	private double playTime;
	private DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key("key");       //second constructor , if left empty then first constructor is being called
		keyImage = key.getImage();
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		if(isGameFinished() == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You have found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.getScreenWidth()/2 - textLength/2;
			y = gp.getScreenHeight()/2 - (gp.getTileSize()*3);
			g2.drawString(text,  x,  y);
			
			text = "Time taken :" + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.getScreenWidth()/2 - textLength/2;
			y = gp.getScreenHeight()/2 + (gp.getTileSize()*4);
			g2.drawString(text,  x,  y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.getScreenWidth()/2 - textLength/2;
			y = gp.getScreenHeight()/2 + (gp.getTileSize()*2);
			g2.drawString(text,  x,  y);
			
			gp.setGameThread(null);
			
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage,  gp.getTileSize()/2, gp.getTileSize()/2, gp.getTileSize(), gp.getTileSize(), null);
			g2.drawString("x "+ gp.getPlayer().getHasKey(), 74, 65);
			
			// TIME
			playTime += (double)1/60; //we add 1/60 seconds in every loop
			g2.drawString("Time:" + dFormat.format(playTime), gp.getTileSize()*11, 65);
			
			// MESSAGE
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30f));
				g2.drawString(message,  gp.getTileSize()/2, gp.getTileSize()*5);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
		
		
	}

	public boolean isGameFinished() {
		return gameFinished;
	}

	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

}
