package ui_public;

import ui_private.ControlPanel;

public class UIFactory {

    private TurtleDisplay myTurtleDisplay;
    private ControlPanel myControlPanel;
    //TODO: include Command/Variable Window Panel that manages Window classes

    protected UIFactory(TurtleDisplay turtleDisplay, ControlPanel controlPanel) {
        myControlPanel = controlPanel;
        myTurtleDisplay = turtleDisplay;
    }

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
}
