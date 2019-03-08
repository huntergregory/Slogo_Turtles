package ui_private.turtles;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import state_public.Turtle;

import java.awt.geom.Point2D;


/**
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class TurtleView {
    static final double WIDTH = 20;
    static final double HEIGHT = 20;
    private static final double INACTIVE_OPACITY = 0.6;
    private static final String CSS_TAG = "turtle";

    protected Node myNode; //must be accessed by subclass

    private int myID;
    private ObservableList myModifiableList;
    private Turtle myTurtleStates;
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

    //TODO remove all of these public methods if we decide on having each turtle have its own stroke and pen color
    /*
    public void setStroke(LineStroke stroke) {
        myPen.setStroke(stroke);
    }

    public void setPenColor(Color color) {
        myPen.setPenColor(color);
    }*/

    private void toggleActive() {
        boolean wasActive = myTurtleStates.getIsActive();
        double newOpacity = (wasActive) ? INACTIVE_OPACITY : 1.0;
        myNode.setOpacity(newOpacity);
        myTurtleStates.setActive(!wasActive);
    }


    private void bindProperties() {
        myNode.visibleProperty().bind(myTurtleStates.getShowingProperty());
    }

    private void addPropertyListeners() {
        myTurtleStates.getPositionProperty().addListener((o, oldPosition, newPosition) -> move(oldPosition, newPosition));
        myTurtleStates.getHeadingProperty().addListener((o, oldHeading, newHeading) -> rotate(newHeading));
        myTurtleStates.getActiveProperty().addListener((o, oldActive, newActive) -> updateOnIsActiveChange(newActive));
        myTurtleStates.getTurtleIDProperty().addListener((o, oldID, newID) -> myID = newID.intValue());
    }

    private void move(Point2D oldPoint, Point2D newPoint) {
        relocateNode(newPoint);
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

    private void rotate(Number newVal) {
        myNode.setRotate(newVal.doubleValue());
    }

    private void updateOnIsActiveChange(Boolean bool) {
        //TODO: change opacity
    }

    /**
     * Call after move if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }
}
