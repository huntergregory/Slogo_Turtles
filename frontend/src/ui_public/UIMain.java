package ui_public;

import parser_public.CommandParser;
import state_public.ParserException;
import state_public.StateManager;
import ui_private.UIFactory;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import ui_private.features.FeatureType;

/**
 *
 * Coordinate system is positive in the right and downwards direction.
 * A heading of 0 points upwards.
 */
public class UIMain {
    private static final double WIDTH = 1000;
    private static final double HEIGHT = 750;
    private static final Paint BACKGROUND = Color.WHITE;
    private static final String TITLE = "SLogo";

    private Stage myStage;
    private Scene myScene;
    private BorderPane myPane;
    private UIFactory myFactory;
    private StateManager myStateManager;
    private CommandParser myBackend;
    private int myWorkspaceID;

    public UIMain(int workspaceID, Stage stage, CommandParser backend, StateManager stateManager) {
        myWorkspaceID = workspaceID;
        myStage = stage;
        myBackend = backend;
        myStateManager = stateManager;
        init(myStage);
    }

    private void init(Stage stage) {
        myScene = setupGame(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    private Scene setupGame (double width, double height, Paint background) {
        myPane = new BorderPane();
        var scene = new Scene(myPane, width, height, background);
        scene.getStylesheets().add("style.css");
        myFactory = new UIFactory(myBackend, myStateManager, WIDTH, HEIGHT);
        myPane.setLeft(myFactory.getLeft());
        myPane.setRight(myFactory.getRight());
        myPane.setCenter(myFactory.getCenter());
        myPane.setBottom(myFactory.getBottom());

        addFeatures();

        return scene;
    }

    private void addFeatures() {
        myFactory.addLeftFeature(FeatureType.PEN_COLOR_CHOOSER);
        myFactory.addLeftFeature(FeatureType.BACKGROUND_COLOR_CHOOSER);
        myFactory.addLeftFeature(FeatureType.LANGUAGE_SELECTOR);
        myFactory.addLeftFeature(FeatureType.TURTLE_IMAGE_SELECTOR);
        //myFactory.addLeftFeature(FeatureType.PAST_COMMANDS_WINDOW);
        myFactory.addLeftFeature(FeatureType.PAST_COMMANDS_SELECTOR);
        //myFactory.addRightFeature(FeatureType.USER_COMMANDS_WINDOW);
        myFactory.addLeftFeature(FeatureType.USER_COMMANDS_SELECTOR);
        myFactory.addLeftFeature(FeatureType.MOVE_TURTLE_SELECTOR);
        myFactory.addRightFeature(FeatureType.VARIABLES_WINDOW);
        myFactory.addRightFeature(FeatureType.TURTLESTATE_WINDOW);
        //myFactory.addRightFeature(FeatureType.FD_BUTTON);
        myFactory.addRightFeature(FeatureType.PEN_THICKNESS_SLIDER);
        myFactory.addRightFeature(FeatureType.PEN_UPDOWN_SELECTOR);
    }

    //TODO: put this in CommandTerminal
    private void handleException(ParserException e) {
        // TODO: Handle exception with some display
    }
}
