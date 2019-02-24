package frontend;

import control.frontendapi.TowardsCall;
import control.frontendapi.SetHeadingCall;
import control.frontendapi.move_distance_calls.*;
import control.frontendapi.move_to_position_calls.*;
import control.frontendapi.query_calls.*;
import control.frontendapi.rotate_angle_calls.*;
import control.frontendapi.set_pen_calls.*;
import control.frontendapi.turtle_visibility_calls.*;

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

    public void testTowards() {
        test(new TowardsHandler());
    }

    public void testQueries() {
        print("pen is down: " + new PenDownQuery().call());
        new PenUpCall().call();
        print("pen is down: " + new PenDownQuery().call());
        new PenDownCall().call();
        print("pen is down: " + new PenDownQuery().call());

        print("heading: " + new HeadingQuery().call());
        new SetHeadingCall(50).call();
        print("heading: " + new HeadingQuery().call());

        print("xpos is: " + new XPositionQuery().call());
        print("ypos is: " + new YPositionQuery().call());
        new GoToCall(50, 20).call();
        print("xpos is: " + new XPositionQuery().call());
        print("ypos is: " + new YPositionQuery().call());

        print("turtle is showing: " + new ShowingQuery().call());
        new HideTurtleCall().call();
        print("turtle is showing: " + new ShowingQuery().call());
        new ShowTurtleCall().call();
        print("turtle is showing: " + new ShowingQuery().call());
    }

    private class TowardsHandler implements EventHandler<ActionEvent> {
        private int index;

        @Override
        public void handle(ActionEvent e) {

        }
    }

    private class RotateHandler implements EventHandler<ActionEvent> {
        private int index;

        @Override
        public void handle(ActionEvent e) {
            double[] angles = {0, 80, 380, 10};
            if (index < angles.length) {
                print("Rotating left: " + new LeftCall(angles[index]).call());
                index++;
            }
        }
    }

    private class ForwardBackwardHandler implements EventHandler<ActionEvent> {
        private int index;

        @Override
        public void handle(ActionEvent e) {

            double[] amounts = {100, 300, 100, 100};
            double[] headings = {0, 90, 90, 45};

            if (index >= amounts.length) {
                print("Clearing screen: " + new ClearScreenCall().call());
                index = 0;
            }
            else {
                print("");
                print("index is: " + index);
                double amt = amounts[index];
                print("Setting new heading. Degrees moved is: " + new SetHeadingCall(headings[index]).call());
                if (index == 2) {
                    print("Going backwards by " + amt + ": " + new BackCall(amt).call());
                }
                else {
                    print("Going forwards by " + amt + ": " + new ForwardCall(amt).call());
                }
                index++;
            }
        }
    }

    private class GoToHandler implements EventHandler<ActionEvent> {
        private int index;

        @Override
        public void handle(ActionEvent e) {
            double[] xPositions = {0, 100, -100, -100, 20};
            double[] yPositions = {0, 100, 100, -100, 20};
            double[] headings = {0, 0, 0, 69, 69};

            if (index >= xPositions.length) {
                print("Clearing screen: " + new ClearScreenCall().call());
                index = 0;
            }
            else {
                print("");
                print("index is: " + index);
                double newX = xPositions[index];
                double newY = yPositions[index];
                print("going to position (" + newX + ", " + newY + "): " + new GoToCall(newX, newY).call());
                print("Setting new heading. Degrees moved is: " + new SetHeadingCall(headings[index]).call());
                index++;
            }
        }
    }


    private void print(String message) {
        System.out.println(message);
    }
}
