package ui_private.turtles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.ObservableList;
import ui_private.displays.TurtleDisplay;

public class ImageTurtleView extends TurtleView {

    private ImageView myImageView;

    public ImageTurtleView(int id, double xOrigin, double yOrigin, ObservableList list) {
        super(id, list, xOrigin, yOrigin);
    }

    @Override
    protected void initializeNode() {
        var image = new Image(getClass().getClassLoader().getResourceAsStream(TurtleDisplay.DEFAULT_IMAGE_NAME));
        myImageView = new ImageView(image);
        myImageView.setPreserveRatio(false);
        myImageView.setFitWidth(WIDTH);
        myImageView.setFitHeight(HEIGHT);
        myNode = myImageView;
    }

    public void setImage(Image image) {
        if (myImageView == null)
            return;
        myImageView.setImage(image);
    }
}
