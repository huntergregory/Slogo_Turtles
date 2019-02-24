package frontend;

import control.backendapi.ParseCall;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parser.ParserException;

public class UIMain extends Application {

    private static UIMain instance;

    public UIMain() {

    }

    public static UIMain getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        stage.setTitle("Test");
        Group root = new Group();
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
    }

    // Method for testing structure
    public void parseButtonPressed() {
        try {
            new ParseCall("fd 50").call();
        } catch (ParserException e) {
            handleException(e);
        }
    }

    public double getHeading() {
        return 0.0; // TODO This
    }

    private void handleException(Exception e) {
        // TODO: Handle exception with some display
    }


    // Frontend internal api
    public int getX() {
        return 3;
    }

    public int getY() {
        return 7;
    }

    public void setXY(int x, int y) {
        System.out.println("Setting x and y");
    }

    public double getTurtleIsShowing() {
        return 0;
    }

    public boolean getPenIsDown() {
        return false;
    }

    public void eraseLines() {
    }
}
