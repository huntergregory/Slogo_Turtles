package ui_public;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.List;

public class WindowPanel extends SidePanel {
    private boolean myContainsVariables;
    private boolean myContainsUserCommmands;
    private boolean myContainsPastCommands;

    public void addVariableWindow() {
        if (myContainsVariables)
            return;

        //FIXME
        myContainsVariables = true;
    }
}
