package ui_private.features.selectors;

import javafx.collections.ObservableList;
import state.StateManager;

public class PastCommandsSelector extends Selector {

    public PastCommandsSelector(StateManager manager) {
        super(manager);
    }

    @Override
    protected ObservableList getItemList() {
        return myStateManager.getCommandHistory().getCommandsProperty().getValue();
    }

    @Override
    protected void handleItemSelected(String item) {
        myCommandTerminal.setText(item);
        myCommandTerminal.sendToParser();
    }
}
