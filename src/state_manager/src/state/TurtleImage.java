package state;

/**
 * @author David Miron
 */
public class TurtleImage {

    private int myId;
    private String myImageFile;

    public TurtleImage(int id, String imageFile) {
        myId = id;
        myImageFile = imageFile;
    }

    /**
     * Get the ID of the turtle image
     * @return
     */
    public int getID() {
        return myId;
    }

    /**
     * Get the image file of the turtle image
     * @return The name of the file
     */
    public String getImageFile() {
        return myImageFile;
    }
}
