package ui_public;

import parser_public.ParserException;
import ui_private.UIFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * Coordinate system is positive in the right and downwards direction.
 * A heading of 0 points upwards.
 */
public class UIMain extends Application {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 600;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";

    private Scene myScene;
    private BorderPane myPane;
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
        myFactory = new UIFactory(WIDTH, HEIGHT);
        myPane.setLeft(myFactory.getLeft());
        myPane.setRight(myFactory.getRight());
        myPane.setCenter(myFactory.getCenter());
        myPane.setBottom(myFactory.getBottom());

        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode())); //FIXME: unnecessary?
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
