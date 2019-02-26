package ui_private;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

//TODO: make angle always in range [0, 360) and update Calls related to angles if necessary
public class TurtleTester {
//    private void test(EventHandler<ActionEvent> handler) {
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500), handler));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//        System.out.println("testing");
//    }
//
//    public void testForwardBackward() {
//        test(new ForwardBackwardHandler());
//    }
//
//    public void testGoTo() {
//        test(new GoToHandler());
//    }
//
//    public void testRotate() {
//        test(new RotateHandler());
//    }
//
//    public void testTowards() {
//        test(new TowardsHandler());
//    }
//
//    public void testQueries() {
//        testPenDownQuery();
//        testHeadingQuery();
//        testXYQueries();
//        testShowingQuery();
//    }
//
//    private class TowardsHandler implements EventHandler<ActionEvent> {
//        private int index;
//
//        @Override
//        public void handle(ActionEvent e) {
//            double[] xPositions = {0, 0, 50, 50, -50};
//            double[] yPositions = {0, 50, 0, 50, -50};
//
//            if (index < xPositions.length) {
//                var x = xPositions[index];
//                var y = yPositions[index];
//                print("Rotating towards (" + x + ", " + y + "): " + new TowardsCall(x, y).call());
//                index++;
//            }
//        }
//    }
//
//    private class RotateHandler implements EventHandler<ActionEvent> {
//        private int index;
//
//        @Override
//        public void handle(ActionEvent e) {
//            double[] angles = {0, 80, 380, 10};
//            if (index < angles.length) {
//                print("Rotating left: " + new LeftCall(angles[index]).call());
//                index++;
//            }
//        }
//    }
//
//    private class ForwardBackwardHandler implements EventHandler<ActionEvent> {
//        private int index;
//
//        @Override
//        public void handle(ActionEvent e) {
//
//            double[] amounts = {100, 300, 100, 100};
//            double[] headings = {0, 90, 90, 45};
//
//            if (index >= amounts.length) {
//                print("Clearing screen: " + new ClearScreenCall().call());
//                index = 0;
//            }
//            else {
//                print("");
//                print("index is: " + index);
//                double amt = amounts[index];
//                print("Setting new heading. Degrees moved is: " + new SetHeadingCall(headings[index]).call());
//                if (index == 2) {
//                    print("Going backwards by " + amt + ": " + new BackCall(amt).call());
//                }
//                else {
//                    print("Going forwards by " + amt + ": " + new ForwardCall(amt).call());
//                }
//                index++;
//            }
//        }
//    }
//
//    private class GoToHandler implements EventHandler<ActionEvent> {
//        private int index;
//
//        @Override
//        public void handle(ActionEvent e) {
//            double[] xPositions = {200, -240, -240, 50, 50, -50};
//            double[] yPositions = {200, 50, -240, 0, 50, -50};
//
//            if (index >= xPositions.length) {
//                print("Clearing screen: " + new ClearScreenCall().call());
//                index = 0;
//            }
//            else {
//                print("");
//                print("index is: " + index);
//                double newX = xPositions[index];
//                double newY = yPositions[index];
//                print("going to position (" + newX + ", " + newY + "): " + new GoToCall(newX, newY).call());
//                index++;
//            }
//        }
//    }
//
//
//    private void testShowingQuery() {
//        print("turtle is showing: " + new ShowingQuery().call());
//        new HideTurtleCall().call();
//        print("turtle is showing: " + new ShowingQuery().call());
//        new ShowTurtleCall().call();
//        print("turtle is showing: " + new ShowingQuery().call());
//    }
//
//    private void testXYQueries() {
//        print("xpos is: " + new XPositionQuery().call());
//        print("ypos is: " + new YPositionQuery().call());
//        new GoToCall(50, 20).call();
//        print("xpos is: " + new XPositionQuery().call());
//        print("ypos is: " + new YPositionQuery().call());
//    }
//
//    private void testHeadingQuery() {
//        print("heading: " + new HeadingQuery().call());
//        new SetHeadingCall(50).call();
//        print("heading: " + new HeadingQuery().call());
//    }
//
//    private void testPenDownQuery() {
//        print("pen is down: " + new PenDownQuery().call());
//        new PenUpCall().call();
//        print("pen is down: " + new PenDownQuery().call());
//        new PenDownCall().call();
//        print("pen is down: " + new PenDownQuery().call());
//    }
//
//
//    private void print(String message) {
//        System.out.println(message);
//    }
}
