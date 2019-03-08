package ui_public;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import parser_public.CommandParser;
import state_public.StateManager;
import ui_private.FeatureFactory;
import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.exceptions.NoFeatureException;

import java.util.*;

public class UIBuilder {
    private static final String FEATURE_PROPERTIES = "feature.properties";

    private final double mySidePanelWidth;

    private BorderPane myBorderPane;
    private TurtleDisplay myTurtleDisplay;
    private SidePanel myLeftPanel;
    private CommandTerminal myTerminal;
    private SidePanel myRightPanel;
    private CommandParser myBackend;
    private StateManager myStateManager;
    private FeatureFactory myFeatureFactory;
    private ArrayList<String> myRightFeatureLabels;
    private ArrayList<String> myLeftFeatureLabels;


    public UIBuilder(CommandParser backend, StateManager stateManager, double width, double height) {
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
        myFeatureFactory = new FeatureFactory(myStateManager, myTerminal);
        myRightFeatureLabels = new ArrayList<>();
        myLeftFeatureLabels = new ArrayList<>();
        initBorderPane();
    }

    public static final void addStyle(Scene scene) {
        scene.getStylesheets().add("style.css");
    }

    public BorderPane getContent() {
        return myBorderPane;
    }

    public void addLeftFeature(String label) {
        addFeature(label, myLeftFeatureLabels, myLeftPanel);
    }

    public void addRightFeature(String label) {
        addFeature(label, myRightFeatureLabels, myRightPanel);
    }

    public void removeFeature(String label) {
        removeLabelFromPanel(label, myRightFeatureLabels, myRightPanel);
        removeLabelFromPanel(label, myLeftFeatureLabels, myLeftPanel);
    }

    public String[] getFeatureNames() {
        return myFeatureFactory.getFeatureNames();
    }

    private void initBorderPane() {
        myBorderPane = new BorderPane();
        myBorderPane.setLeft(myLeftPanel.getPane());
        myBorderPane.setRight(myRightPanel.getPane());
        myBorderPane.setCenter(myTurtleDisplay.getPane());
        myBorderPane.setBottom(myTerminal.getPane());
    }

    private void addFeature(String label, List<String> labelList, SidePanel panel) {
        removeFeature(label);

        try {
            var feature = myFeatureFactory.getFeature(label);
            panel.addRow(feature.getPane(mySidePanelWidth));
            labelList.add(label);
        }
        catch (NoFeatureException e) {
            System.out.println("Failed to add feature, check the properties file or the factory.");
        }
    }

    private void removeLabelFromPanel(String label, List<String> labelList, SidePanel panel) {
        for (int k=0; k<labelList.size(); k++) {
            if (!labelList.get(k).equals(label))
                continue;
            panel.removeRow(k);
            labelList.remove(k);
        }
    }
}
