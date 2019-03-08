package ui_private.turtles;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import state.Turtle;
import ui_private.displays.TurtleDisplay;

public class ImageTurtleView extends TurtleView {

    private ImageView myImageView;
    private static String DEFAULT_IMAGE_NAME = "tan_turtle.png"; //TODO

    public ImageTurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
    }

    @Override
    protected void initializeNode() {
        var image = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT_IMAGE_NAME));
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

    public ImageView getImageView() {
        return myImageView;
    }
}
