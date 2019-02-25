package ui_private.turtles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.ObservableList;

public class ImageTurtle extends Turtle {
    public static String IMAGE_NAME = "tan_turtle.png";

    public ImageTurtle(double screenWidth, double screenHeight, ObservableList list) {
        super(screenWidth, screenHeight, list);
    }

    @Override
    protected void initializeNode() {
        var image = new Image(getClass().getClassLoader().getResourceAsStream(IMAGE_NAME));
        var view = new ImageView(image);
        view.setPreserveRatio(false);
        view.setFitWidth(WIDTH);
        view.setFitHeight(HEIGHT);
        view.getStyleClass().add("turtle");
        myNode = view;
    }
}
