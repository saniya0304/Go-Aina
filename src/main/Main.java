package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // this lets the window properly closed when user clicks "X" button
		window.setResizable(false);
		window.setTitle("Go Aina");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();              //pack sets frame to panel's default size
		
		window.setLocationRelativeTo(null);     //"null" sets the frame location to center
		window.setVisible(true);
		
		
		
		gamePanel.setupGame();   // sets up the objects used in the game like heart, etc. & plays the music
		gamePanel.startGameThread();  // the process starts 
	}

}
  