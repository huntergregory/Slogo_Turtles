package ui_public;

import javafx.scene.layout.Pane;

public class TurtleDisplay {
    public static final String PANE_CSS_CLASS = "pane";

    private Pane myTurtlePane;

    protected TurtleDisplay(double width, double height) {
        myTurtlePane = new Pane();
        myTurtlePane.setMaxSize(width, height);
        myTurtlePane.setMinSize(width, height);
        myTurtlePane.getStyleClass().add(PANE_CSS_CLASS);
    }

    protected Pane getPane() {
        return myTurtlePane;
    }

    public addTurtle() {

    }

    public removeTurtle() {

    }
}
