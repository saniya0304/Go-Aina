package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
	
   public OBJ_Door() {
		
		setName("Door");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/door.png")));
			
		}catch(IOException e) {
			System.err.println("Error getting image: " + e.getMessage());
		}
		setCollision(true);
	}

}
