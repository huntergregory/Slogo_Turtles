package ui_public;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import parser_public.CommandParser;
import state_public.StateManager;
import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.exceptions.NoFeatureException;

public class UIBuilder {
    private int myWorkspaceID;
    private BorderPane myBorderPane;
    private TurtleDisplay myTurtleDisplay;
    private SidePanel myLeftPanel;
    private CommandTerminal myTerminal;
    private SidePanel myRightPanel;
    private double mySidePanelWidth;
    private CommandParser myBackend;
    private StateManager myStateManager;

    public UIBuilder(int workspaceID, CommandParser backend, StateManager stateManager, double width, double height) {
        mySidePanelWidth = width / 3.0;
        var turtlePanelWidth = width / 3.0;
        var turtlePaneHeight = height * 2.0 / 3.0;
        var terminalHeight = height / 6.0;

        myWorkspaceID = workspaceID;
        myBackend = backend;
        myStateManager = stateManager;
        myTerminal = new CommandTerminal(myBackend); //FIXME
        myTurtleDisplay = new TurtleDisplay(myStateManager.getTurtleManager(), turtlePanelWidth, turtlePaneHeight);
        myLeftPanel = new SidePanel(mySidePanelWidth);
        myRightPanel = new SidePanel(mySidePanelWidth);
        initBorderPane();
    }

    public static final void addStyle(Scene scene) {
        scene.getStylesheets().add("style.css");
    }

    public BorderPane getContent() {
        return myBorderPane;
    }

    private void initBorderPane() {
        myBorderPane = new BorderPane();
        myBorderPane.setLeft(myLeftPanel.getPane());
        myBorderPane.setRight(myRightPanel.getPane());
        myBorderPane.setCenter(myTurtleDisplay.getPane());
        myBorderPane.setBottom(myTerminal.getPane());
    }

    public void addLeftFeature(FeatureType type) {
        addFeature(type, myLeftPanel);
    }

    public void addRightFeature(FeatureType type) {
        addFeature(type, myRightPanel);
    }


    private void addFeature(FeatureType type, SidePanel panel) {
        try {
            var feature = type.getFeature(myStateManager, myTerminal);
            panel.addRow(feature.getPane(mySidePanelWidth));
        }
        catch (NoFeatureException e) {
            System.out.println("Failed to add feature, update the FeatureType enum.");
        }
    }
}
