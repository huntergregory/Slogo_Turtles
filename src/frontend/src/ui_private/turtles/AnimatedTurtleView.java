package ui_private.turtles;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.util.Duration;
import state.Turtle;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A TurtleView that animates translation, rotation, and opacity changes.
 * @author Hunter Gregory
 */
public abstract class AnimatedTurtleView extends TurtleView {
    private Queue<Transition> myTransitions;

    protected AnimatedTurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
        myTransitions = new LinkedList<>();
        addDurationListener();
    }

    private void addDurationListener() {
        var durationProperty = myTurtleStates.getAnimationDurationProperty();
        durationProperty.addListener((o, oldDuration, newDuration) -> updateDurations(newDuration));
    }

    private void updateDurations(Number newDuration) {
        //unfortunately Transition is the immediate superclass of all these transitions but doesn't have a setDuration method
        //so we have to cast every possible transition here
        var duration = Duration.millis(newDuration.doubleValue());
        for (Transition transition : myTransitions) {
            if (transition instanceof FadeTransition) {
                ((FadeTransition) transition).setDuration(duration);
            }
            else if (transition instanceof RotateTransition) {
                ((RotateTransition) transition).setDuration(duration);
            }
            else if (transition instanceof TranslateTransition) {
                ((TranslateTransition) transition).setDuration(duration);
            }
        }
    }

    @Override
    protected void updateOpacity(boolean isActive) {
        double oldOpacity = (isActive) ? INACTIVE_OPACITY : 1.0;
        double newOpacity = (isActive) ? 1.0 : INACTIVE_OPACITY;
        myNode.setOpacity(newOpacity);

        var fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(oldOpacity);
        fadeTransition.setToValue(newOpacity);

        handleTransition(fadeTransition);
    }

    @Override
    protected void rotate(Number oldAngle, Number newAngle) {
        var rotateTransition = new RotateTransition();
        rotateTransition.setDuration(getAnimationDuration());
        rotateTransition.setNode(myNode);
        rotateTransition.setByAngle(newAngle.doubleValue() - oldAngle.doubleValue());
        handleTransition(rotateTransition);
    }

    @Override
    protected void move(Point2D oldPoint, Point2D newPoint) {
        var translateTransition = new TranslateTransition();
        translateTransition.setDuration(getAnimationDuration());
        translateTransition.setNode(myNode); //could not generalize this and above line to a Transition, so have to live with duplicated code
        translateTransition.setByX(newPoint.getX() - oldPoint.getX());
        translateTransition.setByY(newPoint.getY() - oldPoint.getY());
        translateTransition.setOnFinished(e -> drawLine(oldPoint, newPoint));
        handleTransition(translateTransition);
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

    private Duration getAnimationDuration() {
        return Duration.millis(myTurtleStates.getAnimationDurationProperty().getValue());
    }
}
