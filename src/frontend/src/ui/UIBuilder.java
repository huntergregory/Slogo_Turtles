package ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import parser.CommandParser;
import state.StateManager;
import ui_private.FeatureFactory;
import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.exceptions.NoFeatureException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * A workspace that manages user interaction with and display of features, turtles, and command terminal parsing.
 * Allows for addition, removal, and manipulation of the current features in the UI and their positions on the screen.
 * @author Hunter Gregory
 */
public class UIBuilder {
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

    /**
     * Create a UIBuilder workspace
     * @param backend
     * @param stateManager
     * @param width
     * @param height
     */
    public UIBuilder(CommandParser backend, StateManager stateManager, double width, double height) {
        mySidePanelWidth = width / 3.0;
        var turtlePanelWidth = width / 3.0;
        var turtlePaneHeight = height * 2.0 / 3.0;

        myBackend = backend;
        myStateManager = stateManager;
        myTerminal = new CommandTerminal(myBackend);
        myTurtleDisplay = new TurtleDisplay(myStateManager.getTurtleManager(), turtlePanelWidth, turtlePaneHeight);
        myLeftPanel = new SidePanel(mySidePanelWidth);
        myRightPanel = new SidePanel(mySidePanelWidth);
        myFeatureFactory = new FeatureFactory(myStateManager, myTerminal);
        myRightFeatureLabels = new ArrayList<>();
        myLeftFeatureLabels = new ArrayList<>();
        initBorderPane();
    }

    /**
     * Adds css stylesheet to the application's scene.
     * @param scene
     */
    public static void addStyle(Scene scene) {
        scene.getStylesheets().add("style.css");
    }

    /**
     * @return UI represented as a BorderPane
     */
    public BorderPane getContent() {
        return myBorderPane;
    }

    /**
     * Places the feature with the given name to the left of the screen, if there exists such a feature.
     * @param label
     */
    public void addLeftFeature(String label) {
        addFeature(label, myLeftFeatureLabels, myLeftPanel);
    }

    /**
     * Places the feature with the given name to the right of the screen, if there exists such a feature.
     * @param label
     */
    public void addRightFeature(String label) {
        addFeature(label, myRightFeatureLabels, myRightPanel);
    }

    /**
     * Removes the feature with the given name from the UI if its visible, if there exists such a feature.
     * @param label
     */
    public void removeFeature(String label) {
        removeLabelFromPanel(label, myRightFeatureLabels, myRightPanel);
        removeLabelFromPanel(label, myLeftFeatureLabels, myLeftPanel);
    }

    /**
     * @return an array of all the names of features that can be added to the UI
     */
    public String[] getFeatureNames() {
        return myFeatureFactory.getFeatureNames();
    }

    /**
     * @return an array of all the names of features that are currently on the left side of the screen
     */
    public String[] getLeftFeatures() {
        return myLeftFeatureLabels.toArray(new String[0]);
    }

    /**
     * @return an array of all the names of features that are currently on the right side of the screen
     */
    public String[] getRightFeatures() {
        return myRightFeatureLabels.toArray(new String[0]);
    }

    /**
     * Set the text of the terminal display
     * @param file
     */
    public void setText(File file) {
        try {
            String content = new String(Files.readAllBytes(file.toPath())); // copied from https://www.journaldev.com/875/java-read-file-to-string
            myTerminal.setText(content);
        }
        catch (IOException e) {
            System.out.println("Couldn't read the file"); //Shouldn't reach this point
        }
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
            break;
        }
    }
}
