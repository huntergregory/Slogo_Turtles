package ui_public;

import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.*;
import ui_private.features.scrollable_windows.ScrollableWindow;

public class UIFactory {

    private TurtleDisplay myTurtleDisplay;
    private SidePanel myLeftPanel;
    private CommandTerminal myTerminal;
    private SidePanel myRightPanel;

    protected UIFactory(TurtleDisplay turtleDisplay, CommandTerminal terminal, SidePanel leftPanel, SidePanel rightPanel) {
        myTurtleDisplay = turtleDisplay;
        myTerminal = terminal;
        myLeftPanel = leftPanel;
        myRightPanel = rightPanel;
    }

    public void addFeature(FeatureType type) {
        try {
            var feature = getFeature(type);
            if (feature instanceof VerticalFeature)
                addVerticalFeature((VerticalFeature) feature);
            else
                addHorizontalFeature((HorizontalFeature) feature);
        }
        catch (NoFeatureException e) {
            System.out.println("Failed to add feature, update the FeatureType enum.");
        }
    }

    private Feature getFeature(FeatureType type) throws NoFeatureException {
        try {
            var featureClass = Class.forName(type.getClassName());
            Object feature = featureClass.getDeclaredConstructor().newInstance();
            return (Feature) feature;
        }
        catch (Exception e) {
            throw new NoFeatureException();
        }
    }

    //vertical features currently consist of only scrollable windows
    private void addVerticalFeature(VerticalFeature feature) {
        myRightPanel.addRow(feature.getPane());
    }

    //horizontal features currently consist of Selectors and ColorChoosers
    private void addHorizontalFeature(HorizontalFeature feature) {
        myLeftPanel.addRow(feature.getPane());
    }

/*
    //FOR ALL OF THESE, MAKE SURE THEY CAN ONLY BE CALLED ONCE

    public void addTurtleBackgroundSelector() {
        //TODO: add selector to controlPanel for language that has handler to communicate with TurtleDisplay
    }

    public void addPenColorSelector() {
        //TODO: add selector to controlPanel for language that has handler to communicate with TurtleDisplay
    }

    // allow images or shapes to be selected for turtle
    public void addTurtleTypeSelector() {
        //TODO: add selector to controlPanel for language that has handler to communicate with TurtleDisplay
    }

    public void addLanguageSelector() {
        //TODO: add selector to controlPanel for language that has handler to communicate with back end
    }

    public void addVariableWindow() {
        //TODO: not sure how to make it communicate with back end
    }

    public void addUserCommandsWindow() {
        //TODO: not sure how to make it communicate with back end
    }

    public void addPastCommmandsWindow() {
        //TODO: not sure how to make it communicate with back end
    }
    */
}
