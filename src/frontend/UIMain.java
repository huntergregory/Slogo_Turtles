package frontend;

import control.backendapi.ParseCall;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import parser.ParserException;

public class UIMain extends Application {

    private static UIMain instance;

    public static final String TITLE = "SLogo";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    public static final Paint BACKGROUND = Color.WHITE;

    private Group root;
    private BorderPane myPane;
    private Pane myTurtlePane;
    private Scene myScene;
    private ControlPanel myControlPanel;

    public UIMain() {


    }

    public static UIMain getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setupGame (int width, int height, Paint background) {
        //root = new Group();
        myPane = new BorderPane();
        myTurtlePane = new Pane();
        var scene = new Scene(myPane, width, height, background);
        myControlPanel = new ControlPanel(WIDTH, HEIGHT);
        myPane.setLeft(myControlPanel.paneBox);
        myPane.setCenter(myTurtlePane);
        //root.getChildren().addAll(myControlPanel.paneBox);
        //root.getChildren().addAll(myPanel.buttons);
        //scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

//    private void handleKeyInput (KeyCode code) {
//        if (code == KeyCode.ENTER) {
//
//        }
//    }

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


    // Frontend internal api
    public double getX() {
        return 3.0;
    }

    public double getY() {
        return 7.0;
    }

    public void setXY(double x, double y) {
        System.out.println("Setting x and y");
    }
}
