package ui_public;

import ui_private.ControlPanel;
import ui_private.turtles.ImageTurtle;
import ui_private.turtles.Turtle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 *
 * Coordinate system is positive in the right and downwards direction.
 * A heading of 0 points upwards.
 */
public class UIMain extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 600;
    private static final double CONTROL_PANEL_WIDTH = WIDTH / 3.0;
    private static final double TURTLE_PANE_WIDTH = WIDTH / 2.0;
    private static final double TURTLE_PANE_HEIGHT = HEIGHT * 5/6.0;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";

    private ArrayList<Turtle> myTurtleImages;
    private BorderPane myPane;
    private TurtleDisplay myTurtleDisplay;
    private Scene myScene;
    private ControlPanel myControlPanel;

    private static UIMain instance;

    public UIMain() {
    }

    public static UIMain getInstance() {
        return instance;
    }

    @Override
    public void start(Stage stage) {
        instance = this;
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setupGame (double width, double height, Paint background) {
        myPane = new BorderPane();
        myTurtleDisplay = new TurtleDisplay(TURTLE_PANE_WIDTH, TURTLE_PANE_HEIGHT, myPane.getChildren());
        var scene = new Scene(myPane, width, height, background);
        scene.getStylesheets().add("style.css");
        myControlPanel = new ControlPanel(CONTROL_PANEL_WIDTH, HEIGHT);
        myPane.setLeft(myControlPanel.getPaneBox());
        myPane.setCenter(myTurtleDisplay.getPane());
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ENTER) {

        }
    }

    private void handleException(Exception e) {
        // TODO: Handle exception with some display
    }
}
