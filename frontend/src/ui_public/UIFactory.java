package ui_public;

import ui_private.ControlPanel;
import ui_private.displays.TurtleDisplay;
import ui_private.displays.CommandTerminal;
import ui_private.displays.WindowPanel;
import ui_private.features.Feature;
import ui_private.features.FeatureType;

public class UIFactory {

    private TurtleDisplay myTurtleDisplay;
    private ControlPanel myControlPanel;
    private CommandTerminal myTerminal;
    private WindowPanel myWindowPanel;

    protected UIFactory(TurtleDisplay turtleDisplay, ControlPanel controlPanel, CommandTerminal terminal, WindowPanel windowPanel) {
        myControlPanel = controlPanel;
        myTurtleDisplay = turtleDisplay;
        myTerminal = terminal;
        myWindowPanel = windowPanel;
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
