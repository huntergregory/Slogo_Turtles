package ui_private.turtles;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.paint.Color;
import parser_public.TurtleManager;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import ui_private.displays.TurtleDisplay;

import java.util.ArrayList;

public class Pen {
    public static final String CSS_TAG = "line";

    private int myID;
    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;
    private LineStroke myStroke;
    private Color myColor;
    private  SimpleBooleanProperty myIsDown = new SimpleBooleanProperty();
    private  SimpleBooleanProperty myShouldEraseLines = new SimpleBooleanProperty(); //should be true after a clear screen command

    protected Pen(int id, ObservableList list) {
        myID = id;
        myModifiableList = list;
        myStroke = TurtleDisplay.DEFAULT_LINE_STROKE;
        myColor = TurtleDisplay.DEFAULT_PEN_COLOR;
        myLines = new ArrayList<>();
        addEraseListener();
        bindProperties();
    }

    private void addEraseListener() {
        myShouldEraseLines.addListener((o, oldBool, newBool) -> {
            if (oldBool) {
                erase();
                myShouldEraseLines.set(false);
            }
        });
    }

    private void bindProperties() {
        var manager = TurtleManager.getInstance();
        myIsDown.bind(manager.getDownProperty(myID));
        myShouldEraseLines.bindBidirectional(manager.getEraseProperty(myID));
    }

    protected void draw(double oldX, double oldY, double newX, double newY) {
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
