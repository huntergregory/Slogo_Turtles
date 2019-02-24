package frontend.turtles;

import frontend.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class Turtle {
    public static double WIDTH = 20;
    public static double HEIGHT = 20;

    protected Node myNode; //must be accessed by subclass

    private ObservableList myModifiableList;
    private Pen myPen;
    private double myDisplayWidth;
    private double myDisplayHeight;
    private double myTopLeftX;
    private double myTopLeftY;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param displayWidth
     * @param displayHeight
     * @param topLeftX
     * @param topLeftY
     * @param list
     */
    public Turtle(double displayWidth, double displayHeight, double topLeftX, double topLeftY, ObservableList list) {
        myDisplayWidth = displayWidth;
        myDisplayHeight = displayHeight;
        myTopLeftX = topLeftX;  //TODO: ObservableList given is for the TurtleDisplay, minX and y are unnecessary
        myTopLeftY = topLeftY;
        myModifiableList = list;
        initializeNode();
        myModifiableList.add(myNode);
        myPen = new Pen(myModifiableList);
        setPosition(0, 0);
        setHeading(0);
        myIsShowing = true;
    }


    abstract protected void initializeNode();

    abstract protected void setNodePosition(double x, double y);


    /**
     * Removes the turtle and its lines from the scene
     */
    public void removeFromScene() {
        myPen.erase();
        myModifiableList.remove(myNode);
    }


    public void eraseLines() {
        myPen.erase();
    }


    public void setPosition(double newX, double newY) {
        double oldDisplayX = getOriginAdjustedX();
        double oldDisplayY = getOriginAdjustedY();
        setX(newX);
        setY(newY);
        double newDisplayX = getOriginAdjustedX();
        double newDisplayY = getOriginAdjustedY();
        setNodePosition(newDisplayX, newDisplayY);
        myPen.draw(oldDisplayX, oldDisplayY, newDisplayX, newDisplayY);
    }


    public double getX() {
        return myX;
    }


    public double getY() {
        return myY;
    }


    public void setHeading(double heading) {
        myHeading = heading;
        myNode.setRotate(myHeading);
    }


    public double getHeading() {
        return myHeading;
    }


    public boolean getIsShowing() {
        return myIsShowing;
    }


    public void setIsShowing(boolean show) {
        myIsShowing = show;
        myNode.setVisible(show);
    }


    public boolean getPenIsDown() {
        return myPen.getIsDown();
    }


    public void setPenIsDown(boolean down) {
        myPen.setIsDown(down);
    }


    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }



    private double getOriginAdjustedX() {
        double centerX = myDisplayWidth / 2 - Turtle.WIDTH / 2;
        return myX + centerX;

    }

    private double getOriginAdjustedY() {
        double centerY = myDisplayHeight / 2 - Turtle.HEIGHT / 2;
        return myY + centerY;
    }

    private void setX(double x) {
        myX = getInBoundsNum(x, myTopLeftX, myDisplayWidth - Turtle.WIDTH);
    }

    private void setY(double y) {
        myY = getInBoundsNum(y, myTopLeftY, myDisplayHeight - Turtle.HEIGHT);
    }

    private double getInBoundsNum(double num, double min, double max) {
        if (num < min)
            return min;
        if (num > max)
            return max;
        return num;
    }
}
