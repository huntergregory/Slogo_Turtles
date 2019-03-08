package ui_private.features.selectors;
/*
public class BackgroundColorSelector extends Selector {
    private static final String LABEL_TEXT = "Background Color";

    @Override
    void handleNewColor(Color newColor) {
        myTurtleDisplay.setBackgroundColor(newColor);
    }

    @Override
    public String getLabelText() {
        return LABEL_TEXT;
    }
}
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import state_public.Palette;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class BackgroundColorSelector extends Selector {
    private static final ObservableList BACKGROUNDCOLORS =
            FXCollections.observableArrayList("PINK 1", "BLUE 2", "GREEN 3", "WHITE 4");

    public BackgroundColorSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList<String> getItemList() {
        return BACKGROUNDCOLORS; //FIXME
    }

    @Override
    protected void handleItemSelected(String item) {
        String[] splitted = item.split("\\s+");
        int myIndex = Integer.parseInt(splitted[1]);
        myStateManager.setBackgroundColor(myIndex);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }
}