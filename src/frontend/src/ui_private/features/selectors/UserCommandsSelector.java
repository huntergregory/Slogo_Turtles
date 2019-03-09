package ui_private.features.selectors;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import state.StateManager;

public class UserCommandsSelector extends Selector {

    public UserCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return myStateManager.getCommands().getCommandsList().getValue();
    }

    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
    }
}