package frontend.turtles;

import frontend.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Pen {
    private ObservableList myModifiableList;
    private ArrayList<Line> myLines;
    private boolean myIsDown;
    private LineStroke myStroke;

    protected Pen(ObservableList list) {
        myModifiableList = list;
        myStroke = LineStroke.NORMAL;
        myLines = new ArrayList<>();
        myIsDown = true;
    }

    protected void draw(double oldX, double oldY, double newX, double newY) {
        if (!myIsDown)
            return;
        Line line = new Line(oldX, oldY, newX, newY);
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

    protected boolean getIsDown() {
        return myIsDown;
    }

    protected void setIsDown(boolean down) {
        myIsDown = down;
    }
}
