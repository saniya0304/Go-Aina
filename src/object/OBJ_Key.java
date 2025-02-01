package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
	
	//Constructor Overloading
	
	public OBJ_Key() {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			
		}catch(IOException e) {
			System.err.println("Error loading key image: " + e.getMessage());	
		}
	}


    
    public OBJ_Key(String name) {
	
	    this.name = name;
	    try {
		    image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		
	    }catch(IOException e) {
	    	System.err.println("Error loading key image: " + e.getMessage());
		   
	    }
     }
}