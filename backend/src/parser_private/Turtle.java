package parser_private;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import parser_public.NoTurtleException;

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
    private double myX;
    private double myY;
    private DoubleProperty myHeading;
    private boolean myIsShowing;
    private boolean myPenIsDown;
    private boolean myErasePreviousLines;

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
        myX = 0;
        myY = 0;
        myHeading = 0;
        myIsShowing = true;
        myPenIsDown = true;
        myErasePreviousLines = false;
    }

    public void setPosition(double newX, double newY) {

    }

    public void setHeading(double heading) {
        myNode.setRotate(heading);
    }

    private double getOriginAdjustedTurtleX() {
        double centerX = getOriginX() - Turtle.WIDTH / 2.0;
        return myX + centerX;
    }

    private double getOriginAdjustedTurtleY() {
        double centerY = getOriginY()- Turtle.HEIGHT / 2.0;
        return myY + centerY;
    }

    private void setX(double x) {
        myX = getInBoundsNum(x, -getOriginX() + Turtle.WIDTH/2.0, getOriginX() - Turtle.WIDTH/2.0);
    }

    private void setY(double y) {
        myY = getInBoundsNum(y, -getOriginY() + Turtle.HEIGHT/2.0, getOriginY() - Turtle.HEIGHT/2.0);
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

    public int getTurtleID() {
        return myTurtleID;
    }


    public DoubleProperty getXProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getXProperty();
    }

    public DoubleProperty getYProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getYProperty();
    }

    public DoubleProperty getHeadingProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getHeadingProperty();
    }

    public BooleanProperty getDownProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getDownProperty();
    }

    public BooleanProperty getShowingProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getShowingProperty();
    }

    public BooleanProperty getEraseProperty(int turtleID) throws NoTurtleException {
        if (idOutOfBounds(turtleID))
            throw new NoTurtleException();
        return myTurtles.get(turtleID).getEraseLinesProperty();
    }
}
