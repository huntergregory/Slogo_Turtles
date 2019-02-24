package frontend.turtles;

import frontend.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class Turtle {
    protected Node myNode; //must be accessed by subclass

    private ObservableList myModifiableList;
    private Pen myPen;
    private double myMinX;
    private double myMaxX;
    private double myMinY;
    private double myMaxY;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param screenWidth
     * @param screenHeight
     * @param topLeftX
     * @param topLeftY
     * @param list
     */
    public Turtle(double screenWidth, double screenHeight, double topLeftX, double topLeftY, ObservableList list) {
        setBoundaries(screenWidth, screenHeight, topLeftX, topLeftY);
        myModifiableList = list;
        initializeNode();
        myModifiableList.add(myNode);
        myPen = new Pen(myModifiableList);
        myX = 0; myY = 0; myHeading = 0;
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


    public void setPosition(double x, double y) {
        double newX = getAdjustedValue(x, myMinX, myMaxX);
        double newY = getAdjustedValue(y, myMinY, myMaxY);
        setNodePosition(newX, newY);
        myPen.draw(myX, myY, newX, newY);
        myX = newX;
        myY = newY;
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



    private void setBoundaries(double screenWidth, double screenHeight, double topLeftX, double topLeftY) {
        myMinX = topLeftX;
        myMaxX = topLeftX + screenWidth;
        myMinY = topLeftY;
        myMaxY = topLeftY + screenHeight;
    }


    private double getAdjustedValue(double num, double min, double max) {
        if (num < min)
            return min;
        if (num > max)
            return max;
        return num;
    }
}
