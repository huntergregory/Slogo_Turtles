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

    public int getID() {
        return myId;
    }

    public String getImageFile() {
        return myImageFile;
    }
}
