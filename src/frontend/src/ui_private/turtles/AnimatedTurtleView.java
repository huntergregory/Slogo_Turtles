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

public abstract class AnimatedTurtleView extends TurtleView {
    private static final double ANIMATION_LENGTH = 1000;

    private Queue<Transition> myTransitions;

    protected AnimatedTurtleView(ObservableList list, Turtle turtle, double dispOffsetX, double dispOffsetY) {
        super(list, turtle, dispOffsetX, dispOffsetY);
        myTransitions = new LinkedList<>();
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
        rotateTransition.setDuration(Duration.millis(ANIMATION_LENGTH));
        rotateTransition.setNode(myNode);
        rotateTransition.setByAngle(newAngle.doubleValue() - oldAngle.doubleValue());
        handleTransition(rotateTransition);
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
}
