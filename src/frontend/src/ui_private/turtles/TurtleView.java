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
 * Represents a movable turtle with customizable image and drawing capabilities.
 * @author Hunter Gregory
 */
public abstract class TurtleView {
    static final double WIDTH = 20;
    static final double HEIGHT = 20;
    private static final double INACTIVE_OPACITY = 0.6;
    private static final String CSS_TAG = "turtle";
    private static final double ANIMATION_LENGTH = 1000;

    protected Node myNode; //must be accessed by subclass
    protected Turtle myTurtleStates;

    private ObservableList myModifiableList;
    private Pen myPen;
    private double myDispXOffset;
    private double myDispYOffset;
    private Queue<Transition> myTransitions;


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
        myTransitions = new LinkedList<>();

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

    private void updateOpacity(boolean isActive) {
        double oldOpacity = (isActive) ? INACTIVE_OPACITY : 1.0;
        double newOpacity = (isActive) ? 1.0 : INACTIVE_OPACITY;

        var fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(oldOpacity);
        fadeTransition.setToValue(newOpacity);

        handleTransition(fadeTransition);
    }

    private void bindProperties() {
        myNode.visibleProperty().bind(myTurtleStates.getShowingProperty());
    }

    private void addPropertyListeners() {
        myTurtleStates.getPositionProperty().addListener((o, oldPosition, newPosition) -> move(oldPosition, newPosition));
        myTurtleStates.getHeadingProperty().addListener((o, oldHeading, newHeading) -> rotate(oldHeading, newHeading));
        myTurtleStates.getActiveProperty().addListener((o, oldActive, newActive) -> updateOpacity(newActive));
    }

    private void move(Point2D oldPoint, Point2D newPoint) {
        var translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(ANIMATION_LENGTH));
        translateTransition.setNode(myNode);
        translateTransition.setByX(newPoint.getX() - oldPoint.getX());
        translateTransition.setByY(newPoint.getY() - oldPoint.getY());
        translateTransition.setOnFinished(e -> drawLine(oldPoint, newPoint));
        handleTransition(translateTransition);
    }

    private void drawLine(Point2D oldPoint, Point2D newPoint) {
        myPen.draw(oldPoint.getX() + myDispXOffset,
                oldPoint.getY() + myDispYOffset,
                newPoint.getX() + myDispXOffset,
                newPoint.getY() + myDispYOffset);
        moveAboveLines();
    }

    private void handleTransition(Transition transition) {
        var oldHandler = transition.getOnFinished();
        if (oldHandler == null)
            transition.setOnFinished(e -> removeCurrentAndPlayNext());
        else
            transition.setOnFinished(e -> { oldHandler.handle(e); removeCurrentAndPlayNext(); });

        myTransitions.add(transition);
        if (myTransitions.size() == 1)
            playNext();
    }

    private void removeCurrentAndPlayNext() {
        myTransitions.remove();
        playNext();
    }

    private void playNext() {
        var nextTransition = myTransitions.peek();
        if (nextTransition != null)
            nextTransition.play();
    }

    private void relocateNode(Point2D newPoint) {
        myNode.relocate(newPoint.getX() - TurtleView.WIDTH / 2.0 + myDispXOffset,
                newPoint.getY() - TurtleView.HEIGHT / 2.0 + myDispYOffset);
    }

    private void rotate(Number oldAngle, Number newAngle) {
        var rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(ANIMATION_LENGTH));
        rotateTransition.setNode(myNode);
        rotateTransition.setByAngle(newAngle.doubleValue() - oldAngle.doubleValue());
        handleTransition(rotateTransition);
    }

    /**
     * Call after move if you want Turtles to be drawn above lines drawn.
     */
    private void moveAboveLines() {
        myModifiableList.remove(myNode);
        myModifiableList.add(myNode);
    }
}
