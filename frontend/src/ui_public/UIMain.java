package ui_public;

import parser_public.ParserException;
import ui_private.displays.CommandTerminal;
import ui_private.ControlPanel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import ui_private.displays.WindowPanel;

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
    private static final double TURTLE_PANE_HEIGHT = HEIGHT * 2/3.0;
    private static final double TERMINAL_HEIGHT = HEIGHT / 6.0;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";

    private Scene myScene;
    private BorderPane myPane;

    //all these besides factory can be local
    private WindowPanel myWindowPanel;
    private TurtleDisplay myTurtleDisplay;
    private ControlPanel myControlPanel;
    private CommandTerminal myTerminal;
    private UIFactory myFactory;

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
        var scene = new Scene(myPane, width, height, background);
        scene.getStylesheets().add("style.css");

        myTerminal = new CommandTerminal(); //FIXME
        myTurtleDisplay = new TurtleDisplay(TURTLE_PANE_WIDTH, TURTLE_PANE_HEIGHT);
        myControlPanel = new ControlPanel(CONTROL_PANEL_WIDTH, HEIGHT);
        myWindowPanel = new WindowPanel(); //FIXME
        myFactory = new UIFactory(myTurtleDisplay, myControlPanel, myWindowPanel, myTerminal);

        //TODO: have ControlPanel and WindowPanel be width 0 until UIFactory adds something to them.
        myPane.setLeft(myControlPanel.getPaneBox());
        myPane.setRight(myWindowPanel.getPane());
        myPane.setCenter(myTurtleDisplay.getPane());
        myPane.setBottom(myTerminal.getPane());

        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    //Delete??
    private void handleKeyInput (KeyCode code) {
        if (code == KeyCode.ENTER) {

        }
    }

    //TODO: put this in CommandTerminal
    private void handleException(ParserException e) {
        // TODO: Handle exception with some display
    }
}
