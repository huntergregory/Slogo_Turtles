package frontend.turtles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.collections.ObservableList;

public class ImageTurtle extends Turtle {
    public static String IMAGE_NAME = "tan_turtle.png";

    public ImageTurtle(double screenWidth, double screenHeight, double topLeftX, double topLeftY, ObservableList list) {
        super(screenWidth, screenHeight, topLeftX, topLeftY, list);
    }

    @Override
    protected void initializeNode() {
        var image = new Image(getClass().getClassLoader().getResourceAsStream(IMAGE_NAME));
        myNode = new ImageView(image);
    }

    @Override
    protected void setNodePosition(double x, double y) {
        var view = (ImageView) myNode;
        view.setX(x);
        view.setY(y);
    }
}
