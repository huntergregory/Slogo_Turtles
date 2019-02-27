package ui_private.turtles;

import parser_public.TurtleState;
import ui_private.LineStroke;
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

    private TurtleState myCurrentState;

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
        myNode.setVisible(true);
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

    public void setState(TurtleState newState) {
        myCurrentState = newState;

        setPosition(newState.getX(), newState.getY());
        setHeading(newState.getHeading());
        myPen.setIsDown(newState.getPenDown());
        myNode.setVisible(newState.getShowing());
    }

    private void setPosition(double newX, double newY) {
        double oldDisplayX = getOriginAdjustedTurtleX();
        double oldDisplayY = getOriginAdjustedTurtleY();
        setX(newX);
        setY(newY);
        double newDisplayX = getOriginAdjustedTurtleX();
        double newDisplayY = getOriginAdjustedTurtleY();
        myNode.relocate(newDisplayX, newDisplayY);
        myPen.draw(oldDisplayX + Turtle.WIDTH / 2.0,
                oldDisplayY + Turtle.HEIGHT / 2.0,
                newDisplayX + Turtle.WIDTH / 2.0,
                newDisplayY + Turtle.HEIGHT / 2.0);
        moveAboveLines();
    }

    /**
     * Call after setPosition if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }

    private void setHeading(double heading) {
        myNode.setRotate(heading);
    }

    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    private double getOriginX() {
        return myDisplayWidth / 2.0;
    }

    private double getOriginY() {
        return myDisplayHeight / 2.0;
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
}
