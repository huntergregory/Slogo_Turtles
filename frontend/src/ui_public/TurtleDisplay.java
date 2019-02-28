package ui_public;

import javafx.scene.layout.Pane;
import parser_public.TurtleManager;
import ui_private.turtles.ImageTurtle;
import ui_private.turtles.Turtle;

import java.util.ArrayList;

public class TurtleDisplay {
    private static final String PANE_CSS_CLASS = "pane";

    private Pane myTurtlePane;
    private ArrayList<Turtle> myTurtleViews; //TODO: call that class TurtleView
    private double myWidth;
    private double myHeight;
    private double myTurtleXOrigin;
    private double myTurtleYOrigin;

    TurtleDisplay(double width, double height) {
        myWidth = width;
        myHeight = height;
        myTurtleXOrigin = width / 2.0;
        myTurtleYOrigin = height / 2.0;
        initializePane();
        initializeTurtles();
    }

    private void initializePane() {
        myTurtlePane = new Pane();
        myTurtlePane.setMaxSize(myWidth, myHeight);
        myTurtlePane.setMinSize(myWidth, myHeight);
        myTurtlePane.getStyleClass().add(PANE_CSS_CLASS);
    }


    // Must be called after initializing myPane
    private void initializeTurtles() {
        myTurtleViews = new ArrayList<>();
        TurtleManager.getInstance().initialize(myWidth, myHeight);
        myTurtleViews.add(new ImageTurtle(0, myTurtleXOrigin, myTurtleYOrigin, myTurtlePane.getChildren()));
    }

    Pane getPane() {
        return myTurtlePane;
    }

    public void addTurtle() {
        //FIXME
    }

    public void removeTurtle() {
        //FIXME
        //TODO: catch NoTurtleException
    }
}
