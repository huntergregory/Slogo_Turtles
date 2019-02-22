package frontend;

import control.backendapi.ParseCall;
import javafx.application.Application;
import javafx.stage.Stage;
import parser.ParserException;

public class UIMain extends Application {

    private static UIMain instance;

    private UIMain() {

    }

    public static UIMain getInstance() {
        if (instance == null)
            instance = new UIMain();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    private void parseButtonPressed() {
        try {
            new ParseCall("fd 50").call();
        } catch (ParserException e) {
            handleException(e);
        }
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

    public static void main(String args[]) {
        UIMain.getInstance().parseButtonPressed();
    }

}
