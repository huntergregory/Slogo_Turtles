package ui_public;

import parser_public.TurtleState;
import parser_public.StateList;
import ui_private.ControlPanel;
import ui_private.TurtleTester;
import ui_private.turtles.ImageTurtle;
import ui_private.turtles.Turtle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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
    private static final String PANE_CSS_CLASS = "pane";
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";

    private ArrayList<Turtle> myTurtleImages;
    private BorderPane myPane;
    private Pane myTurtlePane;
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
        initializeTurtles();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //new TurtleTester().testGoTo(); //TODO: Remove when done testing
    }

    private Scene setupGame (double width, double height, Paint background) {
        myPane = new BorderPane();
        setUpTurtlePane();
        var scene = new Scene(myPane, width, height, background);
        scene.getStylesheets().add("style.css");
        myControlPanel = new ControlPanel(CONTROL_PANEL_WIDTH, HEIGHT);
        myPane.setLeft(myControlPanel.getPaneBox());
        myPane.setCenter(myTurtlePane);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void setUpTurtlePane() {
        myTurtlePane = new Pane();
        myTurtlePane.getStyleClass().add(PANE_CSS_CLASS);
        myTurtlePane.setMaxSize(TURTLE_PANE_WIDTH, TURTLE_PANE_HEIGHT);
        myTurtlePane.setMinSize(TURTLE_PANE_WIDTH, TURTLE_PANE_HEIGHT);
    }

    // Must be called after setupGame to prevent null pointer on myRoot
    private void initializeTurtles() {
        myTurtleImages = new ArrayList<>();
        StateList.getInstance().initialize(TURTLE_PANE_HEIGHT, TURTLE_PANE_WIDTH); // Initialize GUI turtle state list with 1 turtle, set vars to default
        updateTurtles();
    }

    public void updateTurtles() {
        List<TurtleState> newStates = StateList.getInstance().getList();
        for (TurtleState state : newStates) {
            if (myTurtleImages.size() < state.getTurtleID() + 1) { //TODO this logic may need to change for multiple turtles depending on how it's implemented
                var turtle = new ImageTurtle(TURTLE_PANE_WIDTH, TURTLE_PANE_HEIGHT, myTurtlePane.getChildren());
                myTurtleImages.add(turtle);
            }
            Turtle toEdit = myTurtleImages.get(state.getTurtleID());
            toEdit.setState(state);
        }
    }

    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ENTER) {

        }
    }

    private void handleException(Exception e) {
        // TODO: Handle exception with some display
    }

    public void eraseLines() {
        myTurtleImages.get(0).eraseLines();
    }
}
