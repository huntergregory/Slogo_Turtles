package parser_private;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

/**
 * A backend turtle that employs bindings to relate properties to frontend turtle representation.
 * Ensures the turtle stays in a boundary width and height.
 * @author Hunter Gregory
 */
public class Turtle {
    private final double myBoundaryWidth;
    private final double myBoundaryHeight;
    private final double myTurtleWidth;
    private final double myTurtleHeight;

    private int myTurtleID;
    private DoubleProperty myXProperty;
    private DoubleProperty myYProperty;
    private DoubleProperty myHeadingProperty;
    private BooleanProperty myIsShowingProperty;
    private BooleanProperty myPenIsDownProperty;
    private BooleanProperty myShouldEraseLinesProperty;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param displayWidth
     * @param displayHeight
     * @param turtleWidth
     * @param turtleHeight
     */
    public Turtle(int turtID, double displayWidth, double displayHeight, double turtleWidth, double turtleHeight) {
        myTurtleID = turtID;
        myBoundaryWidth = displayWidth;
        myBoundaryHeight = displayHeight;
        myTurtleWidth = turtleWidth;
        myTurtleHeight = turtleHeight;
        setDefaultState();
    }

    private void setDefaultState() {
        setPosition(0,0);
        setHeading(0);
        setShowing(true);
        setPenDown(true);
        myShouldEraseLinesProperty.set(false);
    }

    public void setPosition(double x, double y) {
        myXProperty.set(getInBoundsNum(x, -getOriginX() + myTurtleWidth/2.0, getOriginX() - myTurtleWidth/2.0));
        myYProperty.set(getInBoundsNum(y, -getOriginY() + myTurtleHeight/2.0, getOriginY() - myTurtleHeight/2.0));
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


    private double getOriginAdjustedTurtleX() {
        double centerX = getOriginX() - Turtle.WIDTH / 2.0;
        return myXProperty + centerX;
    }

    private double getOriginAdjustedTurtleY() {
        double centerY = getOriginY()- Turtle.HEIGHT / 2.0;
        return myYProperty + centerY;
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min) {
            System.out.println("position was out of bounds on the left or top");
            System.out.println("returning " + min);
            return min;
        }
        if (num > max) {
            System.out.println("position was out of bounds on the right or bottom");
            System.out.println("returning " + max);
            return max;
        }
        return num;
    }
}
