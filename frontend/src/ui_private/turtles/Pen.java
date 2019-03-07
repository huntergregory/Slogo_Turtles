package ui_private.turtles;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import state_public.TurtleManager;
import ui_private.displays.TurtleDisplay;

import java.util.ArrayList;

public class Pen {
    public static final String CSS_TAG = "line";

    private TurtleManager myTurtleManager;
    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;
    private LineStroke myStroke;
    private Color myColor; //TODO remove
    private  SimpleBooleanProperty myIsDown = new SimpleBooleanProperty();
    private  SimpleBooleanProperty myShouldEraseLines = new SimpleBooleanProperty(); //should be true after a clear screen command

    Pen(ObservableList list, TurtleManager turtleManager) {
        myTurtleManager = turtleManager;
        myModifiableList = list;
        myStroke = TurtleDisplay.DEFAULT_LINE_STROKE;  //TODO remove
        myColor = TurtleDisplay.DEFAULT_PEN_COLOR; //TODO remove
        myLines = new ArrayList<>();
        addEraseListener();
        bindProperties();
    }

    private void addEraseListener() {
        myShouldEraseLines.addListener((o, oldBool, newBool) -> { if (newBool) erase(); });
    }

    private void bindProperties() {
        //var manager = TurtleManager.getInstance();
        //myIsDown.bind(.getDownProperty(myID));
        //myShouldEraseLines.bindBidirectional(manager.getEraseProperty(myID));
    }

    void draw(double oldX, double oldY, double newX, double newY) {
        if (!myIsDown.getValue())
            return;
        Line line = new Line(oldX, oldY, newX, newY);
        line.getStyleClass().add(CSS_TAG);
        setStyle(line);
        myLines.add(line);
        myModifiableList.add(line);
    }

    private void setStyle(Line line) {
        for (double value : myStroke.getStrokeArray())
            line.getStrokeDashArray().add(value);
        line.setStroke(myColor);

        System.out.println(myColor.toString());
    }

    protected void setStroke(LineStroke stroke) {
        myStroke = stroke;
        for (Line line : myLines) {
            line.getStrokeDashArray().removeAll(); //might need to remove each number individually before updating myStroke
            setStyle(line);
        }
    }

    protected void setPenColor(Color color) {
        myColor = color;
        for (Line line : myLines)
            line.setStroke(color);
    }

    protected void erase() {
        myModifiableList.removeAll(myLines);
        myLines = new ArrayList<>();
    }
}
