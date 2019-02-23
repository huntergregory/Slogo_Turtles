package frontend;

import control.backendapi.ParseCall;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import parser.ParserException;

public class UIMain extends Application {

    private static UIMain instance;

    public static final String TITLE = "SLogo";
    public static final int SIZE = 1000;
    public static final Paint BACKGROUND = Color.WHITE;

    private Group root;
    private Scene myScene;

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
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setupGame (int width, int height, Paint background) {
        root = new Group();
        var scene = new Scene(root, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
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
