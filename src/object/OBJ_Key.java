package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
	
	//Constructor Overloading
	
	public OBJ_Key() {
		
		setName("Key");
		try {
			setImage(ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));
			
		}catch(IOException e) {
			System.err.println("Error loading key image: " + e.getMessage());	
		}
	}


    
    public OBJ_Key(String name) {
	
	    this.setName(name);
	    try {
		    setImage(ImageIO.read(getClass().getResourceAsStream("/objects/key.png")));
		
	    }catch(IOException e) {
	    	System.err.println("Error loading key image: " + e.getMessage());
		   
	    }
     }
}