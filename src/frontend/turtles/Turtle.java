package frontend.turtles;

import frontend.LineStroke;
import javafx.collections.ObservableList;
import javafx.scene.Node;

/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class Turtle {
    public static final double WIDTH = 20;
    public static final double HEIGHT = 20;
    public static final String CSS_TAG = "turtle";

    protected Node myNode; //must be accessed by subclass

    private ObservableList myModifiableList;
    private Pen myPen;
    private double myDisplayWidth;
    private double myDisplayHeight;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean myIsShowing;

    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param displayWidth
     * @param displayHeight
     * @param list
     */
    public Turtle(double displayWidth, double displayHeight, ObservableList list) {
        myDisplayWidth = displayWidth;
        myDisplayHeight = displayHeight;

        myModifiableList = list;
        initializeNode();
        myNode.getStyleClass().add(CSS_TAG);
        myModifiableList.add(myNode);

        myPen = new Pen(myModifiableList);
        setPosition(0, 0);
        eraseLines(); // a dot of a line is added if not called
        setHeading(0);
        myIsShowing = true;
    }

    /**
     * Turtle class depends on the implementation assigning a nonnull Node to myNode.
     */
    abstract protected void initializeNode();


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
        double oldDisplayX = getOriginAdjustedTurtleX();
        double oldDisplayY = getOriginAdjustedTurtleY();
        setX(newX);
        setY(newY);
        double newDisplayX = getOriginAdjustedTurtleX();
        double newDisplayY = getOriginAdjustedTurtleY();
        myNode.relocate(newDisplayX, newDisplayY);
        myPen.draw(oldDisplayX + Turtle.WIDTH / 2,
                oldDisplayY + Turtle.HEIGHT / 2,
                newDisplayX + Turtle.WIDTH / 2,
                newDisplayY + Turtle.HEIGHT / 2);
    }

    /**
     * Call after setPosition if you want Turtles to be drawn above lines drawn.
     */
    public void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
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


    private double getOriginX() {
        return myDisplayWidth / 2;
    }

    private double getOriginY() {
        return myDisplayHeight / 2;
    }

    private double getOriginAdjustedTurtleX() {
        double centerX = getOriginX() - Turtle.WIDTH / 2;
        return myX + centerX;

    }

    private double getOriginAdjustedTurtleY() {
        double centerY = getOriginY()- Turtle.HEIGHT / 2;
        return myY + centerY;
    }

    private void setX(double x) {
        myX = getInBoundsNum(x, -getOriginX() + Turtle.WIDTH/2, getOriginX() - Turtle.WIDTH/2);
    }

    private void setY(double y) {
        myY = getInBoundsNum(y, -getOriginY() + Turtle.HEIGHT/2, getOriginY() - Turtle.HEIGHT/2);
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
