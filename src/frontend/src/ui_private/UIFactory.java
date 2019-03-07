package ui_private;

import javafx.scene.layout.Pane;
import parser_public.CommandParser;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;
import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.features.FeatureType;
import ui_private.features.exceptions.NoFeatureException;

public class UIFactory {
    private TurtleDisplay myTurtleDisplay;
    private SidePanel myLeftPanel;
    private CommandTerminal myTerminal;
    private SidePanel myRightPanel;
    private double mySidePanelWidth;
    private CommandParser myBackend;
    private StateManager myStateManager;

    public UIFactory(CommandParser backend, StateManager stateManager, double width, double height) {
        mySidePanelWidth = width / 3.0;
        var turtlePanelWidth = width / 3.0;
        var turtlePaneHeight = height * 2.0 / 3.0;
        var terminalHeight = height / 6.0;
        myBackend = backend;
        myStateManager = stateManager;
        myTerminal = new CommandTerminal(myBackend); //FIXME
        myTurtleDisplay = new TurtleDisplay(myStateManager.getTurtleManager(), turtlePanelWidth, turtlePaneHeight);
        myLeftPanel = new SidePanel(mySidePanelWidth);
        myRightPanel = new SidePanel(mySidePanelWidth);
    }

    public Pane getLeft() {
        return myLeftPanel.getPane();
    }

    public Pane getRight() {
        return myRightPanel.getPane();
    }

    public Pane getBottom() {
        return myTerminal.getPane();
    }

    public Pane getCenter() {
        return myTurtleDisplay.getPane();
    }


    public void addLeftFeature(FeatureType type) {
        addFeature(type, myLeftPanel);
    }

    public void addRightFeature(FeatureType type) {
        addFeature(type, myRightPanel);
    }


    private void addFeature(FeatureType type, SidePanel panel) {
        try {
            var feature = type.getFeature();
            panel.addRow(feature.getPane(mySidePanelWidth));
        }
        catch (NoFeatureException e) {
            System.out.println("Failed to add feature, update the FeatureType enum.");
        }
    }
}
