package frontend;

import control.frontendapi.SetHeadingCall;
import control.frontendapi.move_distance_calls.BackCall;
import control.frontendapi.move_distance_calls.ForwardCall;
import control.frontendapi.move_to_position_calls.ClearScreenCall;
import control.frontendapi.move_to_position_calls.GoToCall;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class TurtleTester {
    private void test(EventHandler<ActionEvent> handler) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), handler));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        System.out.println("testing");
    }

    public void testForwardBackward() {
        test(new ForwardBackwardHandler());
    }

    public void testGoTo() {
        test(new GoToHandler());
    }

    public void testRotate() {
        test(new RotateHandler());
    }

    private class RotateHandler implements EventHandler<ActionEvent> {
        private int testIndex;

        @Override
        public void handle(ActionEvent e) {

        }
    }

    private class ForwardBackwardHandler implements EventHandler<ActionEvent> {
        private int testIndex;

        @Override
        public void handle(ActionEvent e) {

            double[] amounts = {100, 300, 100, 100};
            double[] headings = {0, 90, 90, 45};

            if (testIndex >= amounts.length) {
                System.out.println("Clearing screen: " + new ClearScreenCall().call());
                testIndex = 0;
            }
            else {
                System.out.println();
                System.out.println("index is: " + testIndex);
                double amt = amounts[testIndex];
                System.out.println("Setting new heading. Degrees moved is: " + new SetHeadingCall(headings[testIndex]).call());
                if (testIndex == 2) {
                    System.out.println("Going backwards by " + amt + ": " + new BackCall(amt).call());
                }
                else {
                    System.out.println("Going forwards by " + amt + ": " + new ForwardCall(amt).call());
                }
                testIndex ++;
            }
        }
    }

    private class GoToHandler implements EventHandler<ActionEvent> {
        private int testIndex;

        @Override
        public void handle(ActionEvent e) {
            double[] xPositions = {0, 100, -100, -100, 20};
            double[] yPositions = {0, 100, 100, -100, 20};
            double[] headings = {0, 0, 0, 69, 69};

            if (testIndex >= xPositions.length) {
                System.out.println("Clearing screen: " + new ClearScreenCall().call());
                testIndex = 0;
            }
            else {
                System.out.println();
                System.out.println("index is: " + testIndex);
                System.out.println("going to position (" + xPositions[testIndex] + ", " + yPositions[testIndex] + "): " + new GoToCall(xPositions[testIndex], yPositions[testIndex]).call());
                System.out.println("Setting new heading. Degrees moved is: " + new SetHeadingCall(headings[testIndex]).call());
                testIndex ++;
            }
        }
    }


}
