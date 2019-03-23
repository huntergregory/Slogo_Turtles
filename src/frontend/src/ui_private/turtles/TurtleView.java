package ui_private.turtles;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Duration;
import state.Turtle;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Represents a movable turtle with drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class TurtleView {
    static final double WIDTH = 20;
    static final double HEIGHT = 20;
    private static final String CSS_TAG = "turtle";
    protected static final double INACTIVE_OPACITY = 0.6;

    protected Node myNode; //must be accessed by subclass
    protected Turtle myTurtleStates;

    private ObservableList myModifiableList;
    private Pen myPen;
    private double myDispXOffset;
    private double myDispYOffset;


    /**
     * Assumes all double inputs are positive, and list input is nonnull.
     * @param list
     * @param turtle
     * @param dispOffsetX
     * @param dispOffsetY
     */
    TurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        myModifiableList = list;
        myTurtleStates = turtle;
        myPen = new Pen(myModifiableList, myTurtleStates.getPen());
        myDispXOffset = dispOffsetX;
        myDispYOffset = dispOffsetY;

        initializeNode();
        myNode.setOnMouseClicked(mouseEvent -> toggleActive());
        myNode.getStyleClass().add(CSS_TAG);
        myModifiableList.add(myNode);

        bindProperties();
        addPropertyListeners();

        relocateNode(myTurtleStates.getPosition());
    }

    /**
     * TurtleView class depends on the implementation assigning a nonnull Node to myNode.
     */
    abstract protected void initializeNode();

    /**
     * Removes the turtle and its lines from the scene
     */
    public void removeFromScene() {
        myPen.erase();
        myModifiableList.remove(myNode);
    }

    private void toggleActive() {
        boolean wasActive = myTurtleStates.getIsActive();
        myTurtleStates.setActive(!wasActive);
        updateOpacity(!wasActive);
    }

    protected void updateOpacity(boolean isActive) {
        double newOpacity = (isActive) ? 1.0 : INACTIVE_OPACITY;
        myNode.setOpacity(newOpacity);
    }

    private void bindProperties() {
        myNode.visibleProperty().bind(myTurtleStates.getShowingProperty());
    }

    private void addPropertyListeners() {
        myTurtleStates.getPositionProperty().addListener((o, oldPosition, newPosition) -> move(oldPosition, newPosition));
        myTurtleStates.getHeadingProperty().addListener((o, oldHeading, newHeading) -> rotate(oldHeading, newHeading));
        myTurtleStates.getActiveProperty().addListener((o, oldActive, newActive) -> updateOpacity(newActive));
    }

    protected void move(Point2D oldPoint, Point2D newPoint) {
        relocateNode(newPoint);
        drawLine(oldPoint, newPoint);
    }

    protected void drawLine(Point2D oldPoint, Point2D newPoint) {
        myPen.draw(oldPoint.getX() + myDispXOffset,
                oldPoint.getY() + myDispYOffset,
                newPoint.getX() + myDispXOffset,
                newPoint.getY() + myDispYOffset);
        moveAboveLines();
    }

    private void relocateNode(Point2D newPoint) {
        myNode.relocate(newPoint.getX() - TurtleView.WIDTH / 2.0 + myDispXOffset,
                newPoint.getY() - TurtleView.HEIGHT / 2.0 + myDispYOffset);
    }

    protected void rotate(Number oldAngle, Number newAngle) {
        myNode.setRotate(newAngle.doubleValue());
    }

    /**
     * Call after move if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }
}
