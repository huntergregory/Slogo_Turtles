package ui_private.displays;

import ui_private.displays.SidePanel;

public class WindowPanel extends SidePanel {
    private boolean myContainsVariables;
    private boolean myContainsUserCommmands;
    private boolean myContainsPastCommands;

    //Might be a lot of repetition in these methods by the end, look to refactor
    public void addVariableWindow() {
        if (myContainsVariables)
            return;
        //FIXME
        myContainsVariables = true;
    }

    public void addUserCommands() {
        if (myContainsUserCommmands)
            return;
        //FIXME
        myContainsUserCommmands = true;
    }

    public void addPastCommands() {
        if (myContainsPastCommands)
            return;
        //FIXME
        myContainsPastCommands = true;
    }
}
