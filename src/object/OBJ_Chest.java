package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject{
	
  public OBJ_Chest() {
		
		setName("Chest");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/chest.png")));
			
		}catch(IOException e) {
			System.err.println("Error getting image: " + e.getMessage());	
		}
	}

}
