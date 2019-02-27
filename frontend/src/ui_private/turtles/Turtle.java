package ui_private.turtles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
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

    private BooleanProperty myIsShowingProperty;
    private DoubleProperty myHeadingProperty;
    private DoubleProperty myXProperty;
    private DoubleProperty myYProperty;
    private double myOldX = 0;
    private double myOldY = 0;
    private boolean myReadyToMove; //tradeoff: have to wait until both x and y have been updated to draw and updateOnPositionChange turtle
                                    //could have created a Coordinate object, but then front and back end would have to share this class


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

        addPropertyListeners();
        bindProperties();
        eraseLines(); // a dot of a line is added if not called???????????
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

    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    private void addPropertyListeners() {
        myXProperty.addListener((o, oldVal, newVal) -> updateOnPositionChange(oldVal.doubleValue(), true));
        myYProperty.addListener((o, oldVal, newVal) -> updateOnPositionChange(oldVal.doubleValue(), false));
        myHeadingProperty.addListener((o, oldVal, newVal) -> myNode.setRotate(newVal.doubleValue()));
    }

    private void bindProperties() {
        myXProperty.bind(//get properties from back end);
        myYProperty.bind(//get properties from back end);
        myHeadingProperty.bind(//get properties from back end);
    }

    private void updateOnPositionChange(double old, boolean isX) {
        if (isX)
            myOldX = old;
        else
            myOldY = old;

        if (myReadyToMove) {
            move();
        }
        myReadyToMove = !myReadyToMove;
    }

    private void move() {
        if (didNotMove())
            return;
        double oldDisplayX = getOriginAdjustedTurtleX(myOldX);
        double oldDisplayY = getOriginAdjustedTurtleY(myOldY);
        double newDisplayX = getOriginAdjustedTurtleX(myXProperty.doubleValue());
        double newDisplayY = getOriginAdjustedTurtleY(myYProperty.doubleValue());
        myNode.relocate(newDisplayX, newDisplayY);
        myPen.draw(oldDisplayX + Turtle.WIDTH / 2.0,
                oldDisplayY + Turtle.HEIGHT / 2.0,
                newDisplayX + Turtle.WIDTH / 2.0,
                newDisplayY + Turtle.HEIGHT / 2.0);
        moveAboveLines();
    }

    private boolean didNotMove() {
        return myOldX == myXProperty.doubleValue() && myOldY == myYProperty.doubleValue();
    }

    private double getOriginAdjustedTurtleX(double x) {
        double centerX = myDisplayHeight / 2.0 - Turtle.WIDTH / 2.0;
        return x + centerX;
    }

    private double getOriginAdjustedTurtleY(double y) {
        double centerY = myDisplayWidth / 2.0 - Turtle.HEIGHT / 2.0;
        return y + centerY;
    }

    /**
     * Call after move if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }

    private void eraseLines() {
        myPen.erase();
    }
}
