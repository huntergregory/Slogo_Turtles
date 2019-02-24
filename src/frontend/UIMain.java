package frontend;

import control.backendapi.ParseCall;
// TODO: REMOVE front end api calls AFTER TESTING TURTLES

import control.frontendapi.SetHeadingCall;
import control.frontendapi.move_to_position_calls.ClearScreenCall;
import control.frontendapi.move_to_position_calls.GoToCall;
import frontend.turtles.ImageTurtle;
import frontend.turtles.Turtle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import parser.ParserException;

import java.util.ArrayList;

public class UIMain extends Application {

    private static UIMain instance;

    public static final String TITLE = "SLogo";
    public static final int SIZE = 600;
    public static final Paint BACKGROUND = Color.WHITE;

    private Group myRoot;
    private Scene myScene;
    private ArrayList<Turtle> myTurtles;

    // TODO: REMOVE AFTER TESTING TURTLES
    private int testIndex;

    private UISidePanel myUISidePanel;

    public UIMain() {

    }

    public static UIMain getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        initializeTurtles();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        testTurtle();
    }

    private void testTurtle() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> testMethod()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void testMethod() {
        double[] xPositions = {0, 100, -100, -100, 20};
        double[] yPositions = {0, 100, 100, -100, 20};
        double[] headings = {0, 0, 0, 69, 69};

        if (testIndex > xPositions.length)
            return;

        if (testIndex == xPositions.length)
            System.out.println("Clearing screen: " + new ClearScreenCall().call());
        else {
            System.out.println();
            System.out.println("index is: " + testIndex);
            System.out.println("going to position (" + xPositions[testIndex] + ", " + yPositions[testIndex] + "): " + new GoToCall(xPositions[testIndex], yPositions[testIndex]).call());
            System.out.println("Setting new heading: " + new SetHeadingCall(headings[testIndex]).call());
        }
        testIndex ++;
    }

    private Scene setupGame (int width, int height, Paint background) {
        myRoot = new Group();
        var scene = new Scene(myRoot, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    //must be called after setupGame to prevent null pointer on myRoot
    private void initializeTurtles() {
        myTurtles = new ArrayList<>();
        var turtle = new ImageTurtle(SIZE, SIZE, 0, 0, myRoot.getChildren());
        myTurtles.add(turtle);

        // TODO: REMOVE AFTER TESTING
        var otherTurtle = new ImageTurtle(SIZE, SIZE, 0, 0, myRoot.getChildren());
        myTurtles.add(otherTurtle);
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ENTER) {

        }
    }

    // Method for testing structure
    public void parseButtonPressed() {
        try {
            new ParseCall("fd 50").call();
        } catch (ParserException e) {
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        // TODO: Handle exception with some display
    }


    /*************      Frontend internal api      *********************/
    // All assume there is at least one turtle in myTurtles

    public double getX() {
        return myTurtles.get(0).getX();
    }

    public double getY() {
        return myTurtles.get(0).getY();
    }

    public void setPosition(double x, double y) {
        myTurtles.get(0).setPosition(x,y);
    }

    public double getHeading() {
        return myTurtles.get(0).getHeading();
    }

    public void setHeading(double heading) {
        myTurtles.get(0).setHeading(heading);
    }

    public boolean getPenIsDown() {
        return myTurtles.get(0).getPenIsDown();
    }

    public void setPenIsDown(boolean down) {
        myTurtles.get(0).setPenIsDown(down);
    }

    public boolean getTurtleIsShowing() {
        return myTurtles.get(0).getIsShowing();
    }

    public void setTurtleIsShowing(boolean showing) {
        myTurtles.get(0).setIsShowing(showing);
    }

    public void eraseLines() {
        myTurtles.get(0).eraseLines();
    }
}
