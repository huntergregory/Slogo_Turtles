package ui_private.turtles;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import parser_public.TurtleManager;
import ui_private.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;

import java.util.ArrayList;

import static javafx.scene.paint.Color.TRANSPARENT;

class Pen {
    private static final String CSS_TAG = "line";
    private static final LineStroke DEFAULT_LINE_STROKE = LineStroke.NORMAL;

    private int myID;
    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;
    private LineStroke myStroke;
    private SimpleBooleanProperty myIsDown = new SimpleBooleanProperty();
    private SimpleBooleanProperty myShouldEraseLines = new SimpleBooleanProperty(); //should be true after a clear screen command

    Pen(int id, ObservableList list) {
        myID = id;
        myModifiableList = list;
        myStroke = DEFAULT_LINE_STROKE;
        myLines = new ArrayList<>();
        addEraseListener();
        bindProperties();
    }

    private void addEraseListener() {
        myShouldEraseLines.addListener((o, oldBool, newBool) -> { if (newBool) erase(); });
    }

    private void bindProperties() {
        var manager = TurtleManager.getInstance();
        myIsDown.bind(manager.getDownProperty(myID));
        myShouldEraseLines.bindBidirectional(manager.getEraseProperty(myID));
    }

    void draw(double oldX, double oldY, double newX, double newY) {
        if (!myIsDown.getValue())
            return;
        Line line = new Line(oldX, oldY, newX, newY);
        line.getStyleClass().add(CSS_TAG);
        addStroke(line);
        myLines.add(line);
        myModifiableList.add(line);
    }

    private void addStroke(Line line) {
        for (double value : myStroke.getStrokeArray())
            line.getStrokeDashArray().add(value);
    }

    void setStroke(LineStroke stroke) {
        if (stroke.equals(myStroke))
            return;
        myStroke = stroke;
        for (Line line : myLines) {
            line.getStrokeDashArray().removeAll(); //might need to remove each number individually before updating myStroke
            addStroke(line);
        }
    }

    void erase() {
        myModifiableList.removeAll(myLines);
        myLines = new ArrayList<>();
    }
}
