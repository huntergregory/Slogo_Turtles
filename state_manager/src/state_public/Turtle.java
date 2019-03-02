package state_public;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in display width and height.
 * X and Y properties reflect the coordinates of the turtle view's top left corner.
 * @author Hunter Gregory
 * @author David Miron
 */
public class Turtle {

    private int myTurtleID;
    private double myPaneWidth;
    private double myPaneHeight;

    private SimpleDoubleProperty myXProperty;
    private SimpleDoubleProperty myYProperty;
    private SimpleDoubleProperty myHeadingProperty;
    private SimpleBooleanProperty myIsShowingProperty;
    private SimpleBooleanProperty myShouldEraseLinesProperty;
    private Pen myPen;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     */
    public Turtle(int turtID, double pwidth, double pheight, Palette penColor) {
        myTurtleID = turtID;
        myPaneWidth = pwidth;
        myPaneHeight = pheight;
        myPen = new Pen(penColor);
        instantiateProperties();
        setDefaultState();
    }

    private void instantiateProperties() {
        myXProperty = new SimpleDoubleProperty();
        myYProperty = new SimpleDoubleProperty();
        myHeadingProperty = new SimpleDoubleProperty();
        myIsShowingProperty = new SimpleBooleanProperty();
        myShouldEraseLinesProperty = new SimpleBooleanProperty();
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min)
            return min;
        if (num > max)
            return max;
        return num;
    }

    private void setDefaultState() {
        setPosition(0,0);
        setHeading(0);
        setShowing(true);
        myShouldEraseLinesProperty.set(false);
    }

    public void setPosition(double x, double y) {
        myXProperty.set(getInBoundsNum(x, - myPaneWidth / 2.0, myPaneWidth / 2.0));
        myYProperty.set(getInBoundsNum(y, - myPaneHeight / 2.0, myPaneHeight / 2.0));
    }

    public void setHeading(double heading) {
        myHeadingProperty.set(heading);
    }

    public void setShowing(boolean bool) {
        myIsShowingProperty.set(bool);
    }

    public void eraseLines() {
        myShouldEraseLinesProperty.set(true);
        myShouldEraseLinesProperty.set(false); // Reset to false after listener deletes lines
    }

    public int getTurtleID() {
        return myTurtleID;
    }

    public DoubleProperty getXProperty() {
        return myXProperty;
    }

    public DoubleProperty getYProperty() {
        return myYProperty;
    }

    public DoubleProperty getHeadingProperty() {
        return myHeadingProperty;
    }

    public BooleanProperty getShowingProperty() {
        return myIsShowingProperty;
    }

    public BooleanProperty getEraseProperty() {
        return myShouldEraseLinesProperty;
    }

    public Pen getPen() {
        return myPen;
    }
}
