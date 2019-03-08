package ui_private.features.selectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import state.StateManager;

public class UserCommandsSelector extends Selector {
    private static final ObservableList USERCOMMANDS = FXCollections.observableArrayList("","a","b","c");

    public UserCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return USERCOMMANDS;
    }

    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
    }
}