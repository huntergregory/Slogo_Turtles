package frontend;

import control.backendapi.ParseCall;
import frontend.turtles.ImageTurtle;
import frontend.turtles.Turtle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
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

    public double getX() {
        return 3.0; //TODO: FIX
    }

    public double getY() {
        return 7.0; //TODO: FIX
    }

    public void setPosition(double x, double y) {
        System.out.println("Setting x and y"); //TODO: FIX
    }

    public double getHeading() {
        return 69; //TODO: FIX
    }

    public void setHeading(double heading) {
        System.out.println("Setting heading"); //TODO: FIX
    }

    public boolean getPenIsDown() {
        return false; //TODO: FIX
    }

    public void setPenIsDown(boolean down) {
        System.out.println("Setting penIsDown"); //TODO: FIX
    }

    public boolean getTurtleIsShowing() {
        return false; //TODO: FIX
    }

    public void setTurtleIsShowing(boolean showing) {
        System.out.println("Setting turtleIsShowing"); //TODO: FIX
    }

    public void eraseLines() {
        System.out.println("Erasing lines"); //TODO: FIX
    }
}
