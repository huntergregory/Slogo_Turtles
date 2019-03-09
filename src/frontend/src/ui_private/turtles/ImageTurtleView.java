package ui_private.turtles;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import state.Turtle;

public class ImageTurtleView extends AnimatedTurtleView {

    private ImageView myImageView;
    private static String DEFAULT_IMAGE_NAME = "Turtle 1.png"; //TODO

    public ImageTurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
        bindImage();
        setImage(myTurtleStates.getImageProperty().getValue());
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

    private void bindImage() {
        myTurtleStates.getImageProperty().addListener(((o, oldImage, newImage) -> setImage(newImage)));
    }

    private void setImage(String fileName) {
        if (fileName == null)
            return;
        Image image = new Image(fileName);
        if (image != null)
            myImageView.setImage(image);
    }
}
