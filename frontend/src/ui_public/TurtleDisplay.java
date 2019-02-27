package ui_public;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import parser_public.TurtleManager;
import ui_private.turtles.ImageTurtle;
import ui_private.turtles.Turtle;

import java.util.ArrayList;

public class TurtleDisplay {
    public static final String PANE_CSS_CLASS = "pane";

    private Pane myTurtlePane;
    private ArrayList<Turtle> myTurtleViews; //TODO: call that class TurtleView
    private double myWidth;
    private double myHeight;
    private ObservableList myObservableList;

    protected TurtleDisplay(double width, double height, ObservableList list) {
        myWidth = width;
        myHeight = height;
        myObservableList = list;
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
        TurtleManager.getInstance().initialize(myWidth, myHeight, Turtle.WIDTH, Turtle.HEIGHT);
        myTurtleViews.add(new ImageTurtle(myWidth, myHeight, myObservableList));
    }

    protected Pane getPane() {
        return myTurtlePane;
    }

    public void addTurtle() {
        //FIXME
    }

    public void removeTurtle() {
        //FIXME
    }
}
