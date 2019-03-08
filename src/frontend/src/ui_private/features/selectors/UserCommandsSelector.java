package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state_public.StateManager;
import ui_private.displays.CommandTerminal;

public class UserCommandsSelector extends Selector {
    private static final String TITLE = "User Commands";
    private static final ObservableList USERCOMMANDS = FXCollections.observableArrayList("a","b","c");

    public UserCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return USERCOMMANDS;
    }

    @Override
    protected void handleItemSelected(String item) {
        System.out.println(item);
        myCommandTerminal.setText(item);
    }

    @Override
    public void setCommandTerminal(CommandTerminal terminal) {
        myCommandTerminal = terminal;
    }

    @Override
    protected String getLabelText() {
        return TITLE;
    }
}