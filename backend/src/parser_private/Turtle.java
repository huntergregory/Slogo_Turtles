package parser_private;


import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in display width and height.
 * X and Y properties reflect the coordinates of the turtle view's top left corner.
 * @author Hunter Gregory
 */
public class Turtle {
    private final double myDisplayWidth;
    private final double myDisplayHeight;
    private final double myTurtleWidth;
    private final double myTurtleHeight;

    private int myTurtleID;
    private SimpleDoubleProperty myXProperty;
    private SimpleDoubleProperty myYProperty;
    private SimpleDoubleProperty myHeadingProperty;
    private SimpleBooleanProperty myIsShowingProperty;
    private SimpleBooleanProperty myPenIsDownProperty;
    private SimpleBooleanProperty myShouldEraseLinesProperty;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param displayWidth
     * @param displayHeight
     * @param turtleWidth
     * @param turtleHeight
     */
    public Turtle(int turtID, double displayWidth, double displayHeight, double turtleWidth, double turtleHeight) {
        myTurtleID = turtID;
        myDisplayWidth = displayWidth;
        myDisplayHeight = displayHeight;
        myTurtleWidth = turtleWidth;
        myTurtleHeight = turtleHeight;
        instantiateProperties();
        setDefaultState();
    }

    private void instantiateProperties() {
        myXProperty = new SimpleDoubleProperty();
        myYProperty = new SimpleDoubleProperty();
        myHeadingProperty = new SimpleDoubleProperty();
        myIsShowingProperty = new SimpleBooleanProperty();
        myPenIsDownProperty = new SimpleBooleanProperty();
        myShouldEraseLinesProperty = new SimpleBooleanProperty();
    }

    private void setDefaultState() {
        setPosition(0,0);
        setHeading(0);
        setShowing(true);
        setPenDown(true);
        myShouldEraseLinesProperty.set(false);
    }

    public void setPosition(double x, double y) {
        double displayX = getDisplayX(x);
        double displayY = getDisplayY(y);
        myXProperty.set(getInBoundsNum(displayX, 0, myDisplayWidth - myTurtleWidth));
        myYProperty.set(getInBoundsNum(displayY, 0, myDisplayHeight - myTurtleHeight));
    }

    public void setHeading(double heading) {
        myHeadingProperty.set(heading);
    }

    public void setShowing(boolean bool) {
        myIsShowingProperty.set(bool);
    }

    public void setPenDown(boolean bool) {
        myIsShowingProperty.set(bool);
    }

    public void eraseLines() {
        myShouldEraseLinesProperty.set(true);
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

    public BooleanProperty getDownProperty() {
        return myPenIsDownProperty;
    }

    public BooleanProperty getShowingProperty() {
        return myIsShowingProperty;
    }

    public BooleanProperty getEraseProperty() {
        return myShouldEraseLinesProperty;
    }

    private double getDisplayX(double x) {
        return x - myTurtleWidth/2.0 + myDisplayWidth/2.0;
    }

    private double getDisplayY(double y) {
        return -y - myTurtleHeight/2.0 + myDisplayHeight/2.0;
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min)
            return min;
        if (num > max)
            return max;
        return num;
    }
}
