package main;

import javax.swing.JPanel;


import entity.Entity;
import entity.Player;
import object.SuperObject;
import sound.CitySound;
import sound.Sound;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements Runnable{
	
         // SCREEN SETTINGS
	     private final byte originalTileSize = 16; //16x16 tile
         private final byte scale =  3;	 
         
         private final byte tileSize = originalTileSize * scale; //48x48 tile
         private final byte maxScreenCol = 16;
         private final byte maxScreenRow = 12;
         private final int screenWidth = getTileSize() * maxScreenCol; // 768 pixels
         private final int screenHeight = getTileSize() * maxScreenRow; // 576 pixels
         
         //WORLD SETTINGS
         private final byte maxWorldCol = 50;
         private final byte maxWorldRow = 50;
        
         //FPS
         private byte FPS = 60; //updates the screen 60 times per second
         
         private TileManager tileM = new TileManager(this);
         private KeyListener keyH = new KeyHandler();   
         private Sound music = new CitySound();
         private Sound se = new CitySound();
         private CollisionChecker cChecker = new CollisionChecker(this);
         private AssetSetter aSetter = new AssetSetter(this);
         private UI ui = new UI(this);
         private Thread gameThread;
         
         // ENTITY AND OBJECT
         private Entity player = new Player(this,keyH);  
         private SuperObject obj[] = new SuperObject[10];
         
         
         public  GamePanel() {
        	 
        	 this.setPreferredSize(new Dimension(getScreenWidth(), getScreenHeight()));
        	 this.setBackground(Color.black);
        	 this.setDoubleBuffered(true);
        	 this.addKeyListener(keyH);
        	 this.setFocusable(true);
        	 
         }
         
         public void setupGame() {
        	    
        	 aSetter.setObject();
        	 
        	 playMusic(0);
         }

         public void startGameThread() {
        	 
        	 setGameThread(new Thread(this));
        	 getGameThread().start();
         }
		
       @Override	
       public void run() {
        	 
        	 double drawInterval = 1000000000/FPS;
        	 double delta = 0;
        	 long lastTime = System.nanoTime();
        	 long currentTime;
        	 long timer = 0;
        	 int drawCount = 0;
        	 
        	 
        	 while(getGameThread() != null) {
        		 
        		 currentTime = System.nanoTime();
        		 
        		 delta += (currentTime - lastTime) / drawInterval;
        		 timer += (currentTime - lastTime);
        		 lastTime = currentTime;
        		 
        		 if(delta >= 1) {
        		 update();
        		 repaint();
        		 delta--;
        		 drawCount++;
        		 }
                if(timer >= 1000000000) {
                	System.out.println("FPS:" + drawCount);
                	drawCount = 0;
                	timer = 0;
                }
            }
         }
         
         public void update() {
        	getPlayer().update();
         }
         
         public void paintComponent(Graphics g) {
        	 super.paintComponent(g);
        	 Graphics2D g2 = (Graphics2D)g;
        	 
        	 // TILE 
        	 getTileM().draw(g2);
        	 
        	 // OBJECT
        	 for(int i = 0; i < getObj().length; i++) {
        		 if(getObj()[i] != null) {
        			 getObj()[i].draw(g2, this);
        		 }
        	 }
        	 
        	 // PLAYER
        	 getPlayer().draw(g2);
        	 
        	 // UI
        	 getUi().draw(g2);
        	 
        	 g2.dispose();
        	 
         }
         public void playMusic(int i) {
        	 
        	 music.setFile(i);
        	 music.play();
        	 music.loop();
         }
         public void stopMusic() {
        	 
        	music.stop();
         }
         public void playSE(int i) {
        	 
        	 se.setFile(i);
        	 se.play();
         }

		public byte getTileSize() {
			return tileSize;
		}

		public int getScreenHeight() {
			return screenHeight;
		}

		public int getScreenWidth() {
			return screenWidth;
		}

		public CollisionChecker getcChecker() {
			return cChecker;
		}

		public void setcChecker(CollisionChecker cChecker) {
			this.cChecker = cChecker;
		}

		public SuperObject[] getObj() {
			return obj;
		}

		public void setObj(SuperObject obj[]) {
			this.obj = obj;
		}

		public UI getUi() {
			return ui;
		}

		public void setUi(UI ui) {
			this.ui = ui;
		}

		public TileManager getTileM() {
			return tileM;
		}

		public void setTileM(TileManager tileM) {
			this.tileM = tileM;
		}

		public int getMaxWorldRow() {
			return maxWorldRow;
		}

		public int getMaxWorldCol() {
			return maxWorldCol;
		}

		public Entity getPlayer() {
			return player;
		}

		public void setPlayer(Entity player) {
			this.player = player;
		}

		public Thread getGameThread() {
			return gameThread;
		}

		public void setGameThread(Thread gameThread) {
			this.gameThread = gameThread;
		}
}
