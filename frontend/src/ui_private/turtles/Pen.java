package ui_private.turtles;

import javafx.beans.property.BooleanProperty;
import ui_private.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Pen {
    public static final String CSS_TAG = "line";
    public static final LineStroke DEFAULT_LINE_STROKE = LineStroke.NORMAL;

    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;
    private LineStroke myStroke;
    private BooleanProperty myIsDown;
    private BooleanProperty myShouldEraseLines; //should be true after a clear screen command

    protected Pen(ObservableList list) {
        myModifiableList = list;
        myStroke = DEFAULT_LINE_STROKE;
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
        myIsDown.bind(//get properties from back end);
        myShouldEraseLines.bind(//get properties from back end);
    }

    protected void draw(double oldX, double oldY, double newX, double newY) {
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

    protected void setStroke(LineStroke stroke) {
        if (stroke.equals(myStroke))
            return;
        myStroke = stroke;
        for (Line line : myLines) {
            line.getStrokeDashArray().removeAll(); //might need to remove each number individually before updating myStroke
            addStroke(line);
        }
    }

    protected void erase() {
        myModifiableList.removeAll(myLines);
        myLines = new ArrayList<>();
    }
}
