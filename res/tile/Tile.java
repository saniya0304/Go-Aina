package tile;

public class Tile<T> {

    private T image;  // Generic type for image
    private boolean collision = false;



    // Setter for the image
    public void setImage(T image) {
        this.image = image;
    }

    // Setter for collision
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    // Getter for the image
    public T getImage() {
        return image;
    }

    // Getter for collision
    public boolean getCollision() {
        return collision;
    }
}
