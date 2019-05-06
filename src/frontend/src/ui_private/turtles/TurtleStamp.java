package ui_private.turtles;

import javafx.collections.ObservableList;
import state.Turtle;

public class TurtleStamp extends ImageTurtleView {

    public TurtleStamp(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
        super.removeListeners();
        this.rotate(0, turtle.getHeading());
        myNode.setOnMouseClicked(null);
    }
}
