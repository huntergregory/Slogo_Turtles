package ui_public;

import ui_private.displays.SidePanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.features.Feature;
import ui_private.features.FeatureType;

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
        var feature = getFeature(type);
        if (feature instanceof Selector)
            addSelector(feature);
        else
            addScrollableWindow(feature);
    }

    private Feature getFeature(FeatureType type) {
        try {
            var featureClass = Class.forName(type.getClassName());
            Object feature = featureClass.getDeclaredConstructor().newInstance();
            return (Feature) feature;
        }
        catch (Exception e) {
            System.out.println("Failed to add feature, update the FeatureType enum.");
        }
    }

    private void addSelector() {
        //TODO
    }

    private void addScrollableWindow() {
        //TODO
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
