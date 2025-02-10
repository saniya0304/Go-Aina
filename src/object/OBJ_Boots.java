package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject{
	
     public OBJ_Boots() {
		
		setName("Boots");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/boots.png")));
			
		}catch(IOException e) {
			System.err.println("Error loading boot image: " + e.getMessage());
		}
	}

}
